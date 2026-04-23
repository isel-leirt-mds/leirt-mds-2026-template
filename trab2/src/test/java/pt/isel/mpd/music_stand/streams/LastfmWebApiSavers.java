package pt.isel.mpd.music_stand.streams;

import org.junit.jupiter.api.Test;
import pt.isel.mpd.utils.requests.*;

import pt.isel.mpd.music_stand.streams.dto.AlbumDto;
import pt.isel.mpd.music_stand.streams.dto.ArtistDetailDto;
import pt.isel.mpd.music_stand.streams.dto.ArtistDto;
import pt.isel.mpd.music_stand.streams.dto.TrackDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LastfmWebApiSavers {
    @Test
    public void searchForArtistsNamedDavid(){
        var req = new SaverRequest(new HttpRequest());
        LastFmWebApi api = new LastFmWebApi(req);
        var artists  =
                api.searchArtist("David", 1);

        assertEquals("David Bowie", artists.get(2).getName());
        assertEquals(25, artists.size());
    }
    
    @Test
    public void getStingInfo() {
        var req = new SaverRequest(new HttpRequest());
        LastFmWebApi api = new LastFmWebApi(req);
        List<ArtistDto> artists = api.searchArtist("Sting", 1);
        ArtistDto sting = artists.get(0);
        assertEquals("Sting", sting.getName());
        ArtistDetailDto stingDetail = api.getArtistDetail(sting.getMbid());
        assertEquals("The Police",
                stingDetail.getSimilarArtists().get(0).getName() );
        // check if cache is ok
        var api2 = new LastFmWebApi(new FileRequest());
        artists = api2.searchArtist("Sting", 1);
        sting = artists.get(0);
        assertEquals("Sting", sting.getName());
        stingDetail = api2.getArtistDetail(sting.getMbid());
        assertEquals("The Police",
                stingDetail.getSimilarArtists().get(0).getName() );
    }

}
