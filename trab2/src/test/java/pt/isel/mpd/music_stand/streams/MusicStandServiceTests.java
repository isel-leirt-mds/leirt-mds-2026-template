/*
 * GNU General Public License v3.0
 *
 * Copyright (c) 2019, Miguel Gamboa (gamboa.pt)
 *
 *   All rights granted under this License are granted for the term of
 * copyright on the Program, and are irrevocable provided the stated
 * conditions are met.  This License explicitly affirms your unlimited
 * permission to run the unmodified Program.  The output from running a
 * covered work is covered by this License only if the output, given its
 * content, constitutes a covered work.  This License acknowledges your
 * rights of fair use or other equivalent, as provided by copyright law.
 *
 *   You may make, run and propagate covered works that you do not
 * convey, without conditions so long as your license otherwise remains
 * in force.  You may convey covered works to others for the sole purpose
 * of having them make modifications exclusively for you, or provide you
 * with facilities for running those works, provided that you comply with
 * the terms of this License in conveying all material for which you do
 * not control copyright.  Those thus making or running the covered works
 * for you must do so exclusively on your behalf, under your direction
 * and control, on terms that prohibit them from making any copies of
 * your copyrighted material outside their relationship with you.
 *
 *   Conveying under any other circumstances is permitted solely under
 * the conditions stated below.  Sublicensing is not allowed; section 10
 * makes it unnecessary.
 *
 */

package pt.isel.mpd.music_stand.streams;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.mpd.utils.requests.CountRequest;
import pt.isel.mpd.utils.requests.HttpRequest;
import pt.isel.mpd.utils.requests.Request;
import pt.isel.mpd.music_stand.streams.model.Album;
import pt.isel.mpd.music_stand.streams.model.Artist;
import pt.isel.mpd.music_stand.streams.model.TopTrack;
import pt.isel.mpd.music_stand.streams.model.Track;
import pt.isel.mpd.utils.streams.StreamUtils;

import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.lang.IO.println;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pt.isel.mpd.utils.streams.StreamUtils.*;


public class MusicStandServiceTests {
    private static Logger logger =
        LoggerFactory.getLogger(MusicStandServiceTests.class);

    @Test
    public void search_David_Bowie_and_count_albums() {
        logger.info("start search_David_Bowie_and_count_albums");
        var countRequest = new CountRequest(new HttpRequest());

        MusicStandService service = new MusicStandService(
            new LastFmWebApi(countRequest));
        Stream<Artist> artists = service.searchArtist("david", 50);
        assertEquals(0, countRequest.getCount());

        List<Album> davidBowieAlbums =
            artists
            .dropWhile(a -> !a.getName().equalsIgnoreCase("David Bowie"))
            .findFirst()
            .map( db -> db.getAlbums().limit(50).toList())
            .orElse(List.of());

        assertEquals(4, countRequest.getCount());
        assertEquals(50, davidBowieAlbums.size());

        for(var a : davidBowieAlbums.stream().sorted(Comparator.comparing(Album::getName)).toList()) {
            println(a.getName());
        }

        logger.info("end start search_David_Bowie_and_count_albums");
    }

    @Test
    public void searchHyperAndCountAllResults() {
        var countRequest = new CountRequest(new HttpRequest());
        MusicStandService service = new MusicStandService(
            new LastFmWebApi(countRequest));
        Stream<Artist> artists = service.searchArtist("hyper", 20);
        assertEquals(0, countRequest.getCount());
        assertEquals(20, artists.count());
        assertEquals(2, countRequest.getCount());
        artists = service.searchArtist("hyper", 20);
        Artist last = findLast(artists).get();
        assertEquals("Hyper Hippo Entertainment", last.getName());
        assertEquals(4, countRequest.getCount());
    }


    @Test
    public void getFirstAlbumOfMuse() {
        var countRequest = new CountRequest(new HttpRequest());
        var service = new MusicStandService(new LastFmWebApi(countRequest));
        Stream<Artist> artists = service.searchArtist("muse", 10);
        assertEquals(0, countRequest.getCount());
        Artist muse = artists.findFirst().get();
        assertEquals(1, countRequest.getCount());
        Stream<Album> albums = muse.getAlbums();
        assertEquals(1, countRequest.getCount());
        Album first = albums.findFirst().get();
        assertEquals(2,countRequest.getCount());
        assertEquals("Black Holes and Revelations", first.getName());

    }


    @Test
    public void get30TracksOfBillieEilish() {
        var countRequest = new CountRequest(new HttpRequest());
        MusicStandService service =
            new MusicStandService(new LastFmWebApi(countRequest));
        Artist billie = service.searchArtist("billie+eilish", 10).findFirst().get();
        var tracks = billie.getTracks().limit(30).toList();
        assertEquals(30, tracks.size());
        assertEquals(5, countRequest.getCount());

    }

    @Test
    public void getRosaliaAlbums() {
        var countRequest = new CountRequest(new HttpRequest());
        MusicStandService service =
                new MusicStandService(new LastFmWebApi(countRequest));
        Stream<Artist> artists = service.searchArtist("Rosalia", 10);
        assertEquals(0, countRequest.getCount());

        Artist rosalia = artists.findFirst().get();
        assertEquals(1, countRequest.getCount());

        Stream<Album> albums =
                rosalia.getAlbums().limit(6)
                .filter(a -> a.getTracks().count() > 2);
        assertEquals(1, countRequest.getCount());

        albums.forEach(System.out::println);
        assertEquals(11, countRequest.getCount());
    }
    
    @Test
    public void get42thTrackOfMuse() {
        var countRequest = new CountRequest(new HttpRequest());
        var service = new MusicStandService(new LastFmWebApi(countRequest));
        Stream<Track> tracks =
               service.searchArtist("muse", 10)
                       .findFirst().get()
                       .getTracks();
        assertEquals(1, countRequest.getCount()); // 1 for artist + 0 for tracks because it fetches lazily

        Track track = tracks
                .skip(42)
                .findFirst()
                .get(); // + 1 to getAlbums + 4 to get tracks of first 4 albums.

        assertEquals(6, countRequest.getCount());
        assertEquals("MK Ultra", track.getName());
    }


    @Test
    public void getBrunoMarsLastAlbum() {
        CountRequest countRequest = new CountRequest(new HttpRequest());
        MusicStandService service = new MusicStandService(new LastFmWebApi(countRequest));

        var artist = service.searchArtist("Bruno+Mars", 10).findFirst().get();
        assertEquals("Bruno Mars", artist.getName());

        var album = artist.getAlbums().skip(6).findFirst().get() ;

        assertEquals("The Romantic", album.getName());

    }

    @Test
    public void getPortugalTop50Tracks() {
        Request req = new HttpRequest();
        var service  = new MusicStandService(new LastFmWebApi(req));

        var geoTop = service.getCountryTop("portugal", 50);
        assertEquals("portugal",  geoTop.getCountryName());
        assertEquals("Olivia Rodrigo",
                geoTop.getTracks()
                .findFirst()
                .map(t -> t.getArtistName())
                .orElse("None"));

    }


    @Test
    public void searchPinkFloydAndCountAllResultsWithCache() {
        var countRequest = new CountRequest(new HttpRequest());
        MusicStandService service = new MusicStandService(
                new LastFmWebApi(countRequest));
        Supplier<Stream<Artist>> artists =
                cache(service.searchArtist("Pink+Floyd", 30));
        assertEquals(0, countRequest.getCount());
        assertEquals(30, artists.get().count()); //JM expected was 700
        assertEquals(3, countRequest.getCount());
        Artist last = artists.get().reduce((a1,a2) -> a2).get();
        assertEquals("Pink Floyd & Syd Barrett",  last.getName());
        assertEquals(3, countRequest.getCount());
    }




    @Test
    public void get_artists_common_to_Stacey_Kent_and_Diana_Krall() {
        CountRequest countRequest = new CountRequest(new HttpRequest());
        MusicStandService service = new MusicStandService(new LastFmWebApi(countRequest));

        var similars =
                service.commonArtists("Stacey+Kent","Diana+Krall" )
                        .collect(toList());

        System.out.println(similars);
        assertEquals(3, similars.size());
        assertEquals("Jane Monheit", similars.get(0));
    }

    @Test
    public void getCommonTrackOnPortugalAndSpainTop50() {
        // TO IMPLEMENT
    }

}
