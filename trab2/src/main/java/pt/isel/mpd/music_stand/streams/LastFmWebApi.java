
package pt.isel.mpd.music_stand.streams;

import com.google.gson.Gson;
import pt.isel.mpd.utils.requests.Request;
import pt.isel.mpd.utils.resources.ResourceUtils;
import pt.isel.mpd.music_stand.streams.dto.*;

import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.List;

import static pt.isel.mpd.utils.resources.BuildUtils.TODO;
import static pt.isel.mpd.utils.resources.BuildUtils.TO_COMPLETE;

public class LastFmWebApi {
    public static final int PAGE_SIZE = 30;

    private static String LASTFM_API_KEY = getApiKeyFromResources();
    private static final String LASTFM_SERVICE = "http://ws.audioscrobbler.com/2.0/";


    private static final String LASTFM_SEARCH_ARTIST = LASTFM_SERVICE
                        + "?method=artist.search&format=json&artist=%s&page=%d&api_key="
                        + LASTFM_API_KEY;

    private static final String LASTFM_GET_ALBUMS = LASTFM_SERVICE
                    + "?method=artist.gettopalbums&format=json&mbid=%s&page=%d&api_key="
                    + LASTFM_API_KEY;

    private static final String LASTFM_GET_ALBUM_INFO = LASTFM_SERVICE
                    + "?method=album.getinfo&format=json&mbid=%s&api_key="
                    + LASTFM_API_KEY;

     private static final String LASTFM_ARTIST_INFO = LASTFM_SERVICE
            + "?method=artist.getinfo&format=json&mbid=%s&api_key="
            + LASTFM_API_KEY;

    /**
     * Retrieve API-KEY from resources
     * @return
     */
    private static String getApiKeyFromResources() {
        try(var reader = ResourceUtils.openResource("lastfm_web_api_key.txt")) {
            return reader.readLine();
        }
        catch(IOException e) {
            throw new IllegalStateException(
                "YOU MUST GET a KEY from https://www.last.fm/api and place it in src/main/resources/lastfm_web_api_key.txt"
            );
        }
    }


    private final Request request;
    protected final Gson gson;

    public LastFmWebApi(Request request) {
        this.request = request;
        this.gson = new Gson();
    }

    public List<ArtistDto> searchArtist(String name, int page) {
        String path = String.format(LASTFM_SEARCH_ARTIST, name, page);
        try(Reader reader= request.get(path)) {
            SearchArtistDto searchResult = gson.fromJson(reader, SearchArtistDto.class);

            TO_COMPLETE("in searchArtist the list returned must not contain empty or null mbids");
            return  searchResult.getResults().getArtistMatches();
        }
        catch(IOException e) {
            throw new UncheckedIOException(e);
        }
       
    }

    public ArtistDetailDto getArtistDetail(String artistMbid) {
        String path = String.format(LASTFM_ARTIST_INFO, artistMbid);

        try(Reader reader= request.get(path)) {
            ArtistDetailQueryDto result =
                    gson.fromJson(reader, ArtistDetailQueryDto.class);
            return result.getInfo();
        }
        catch(IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    public List<AlbumDto> getAlbums(String artistMbid, int page) {
        String path = String.format(LASTFM_GET_ALBUMS, artistMbid, page);
        
        try(Reader reader= request.get(path)) {
            GetAlbumsDto albumsQuery = gson.fromJson(reader, GetAlbumsDto.class);
            TO_COMPLETE("in getAlbums the list returned must not contain empty or null mbids");
            return albumsQuery.getAlbums();

        }
        catch(IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private List<TrackDto> getAlbumTracks2(String albumMbid){
        String path = String.format(LASTFM_GET_ALBUM_INFO, albumMbid);

        try(Reader reader= request.get(path))  {
            var tracksDto =  gson.fromJson(reader, GetAlbum2Dto.class).getTracks();
            if (tracksDto == null) return List.of();
            return tracksDto;
        }
        catch(IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public List<TrackDto> getAlbumTracks(String albumMbid){
        String path = String.format(LASTFM_GET_ALBUM_INFO, albumMbid);
        
        try(Reader reader= request.get(path))  {
            AlbumDto album = gson.fromJson(reader, GetAlbumDto.class).getAlbum();
            return album.getTracks();
        }
        catch(IOException e) {
            throw new UncheckedIOException(e);
        }
        catch(Exception e) {
            // this error occurs because JSON sometimes give a track object instead of an
            // array of tracks, so we retry the query using an auxiliary Album2Dto
            // Just a n hack, but it works
            var tracks =  getAlbumTracks2(albumMbid);
            return tracks;
        }
    }

    public CountryTopDto getCountryTop(String country, int page) {
       TODO();
       return null;
    }
}
