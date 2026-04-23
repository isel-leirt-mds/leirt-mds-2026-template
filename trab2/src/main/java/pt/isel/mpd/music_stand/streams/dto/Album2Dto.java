package pt.isel.mpd.music_stand.streams.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * auxiliary class to fix the JSON problem
 * that returns a track object instead of an array of track objects
 */
public class Album2Dto {

    private  Tracks2Dto tracks;

    public Album2Dto(Tracks2Dto tracks) {
        this.tracks = tracks;
    }

    public List<TrackDto> getTracks() {
        if (tracks == null) return List.of();
        var a=  new ArrayList<TrackDto>();
        a.add(tracks.getTrack());
        return a;
    }

}
