
package pt.isel.mpd.music_stand.streams
;

import pt.isel.mpd.music_stand.streams.dto.*;
import pt.isel.mpd.music_stand.streams.model.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pt.isel.mpd.utils.resources.BuildUtils.TODO;
import static pt.isel.mpd.utils.streams.StreamUtils.matchAndCombine;

public class MusicStandService {

    final LastFmWebApi api;

    public MusicStandService(LastFmWebApi api) {
        this.api = api;
    }


    public Stream<Artist> searchArtist(String name, int maxItems) {
        TODO("searchArtist");
        return null;
    }

    public Stream<Album> getAlbums(String artistMbid) {
        TODO("getAlbums");
        return null;
    }

    private Stream<Track> getAlbumTracks(String albumMbid) {
        TODO("getAlbumTracks");
        return null;
    }

    private Stream<Track> getTracks(String artistMbid) {
        TODO("getTracks");
        return null;
    }

    public ArtistDetail getArtistDetail(String artistMbid) {
        TODO("getArtistDetail");
        return null;

    }

    public CountryTop getCountryTop(String country, int limit) {
       TODO("getCountryTop");
       return null;
    }

    private Stream<String> similarArtists(String artist) {
        return  searchArtist(artist, 1)
                .map(a -> a.getDetail())
                .map(d -> d.getSimilarArtists())
                .findFirst()
                .map(l -> l.stream())
                .orElse(Stream.empty());
    }

    public Stream<String> commonArtists(String artist1, String artist2) {
       TODO();
       return null;
    }


    private Artist dtoToArtist(ArtistDto dto) {
        TODO("dtoToArtist");
        return null;
    }

    private Album dtoToAlbum(AlbumDto dto) {
        TODO("dtoToAlbum");
        return null;
    }

    private Track dtoToTrack(TrackDto dto) {
        TODO("dtoToTrack");
        return null;
    }

    private TopTrack dtoToTopTrack(TopTrackDto dto) {
        TODO("dtoToTopTrack");
        return null;
    }


    
}
