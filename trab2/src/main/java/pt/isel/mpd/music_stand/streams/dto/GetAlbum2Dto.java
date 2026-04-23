package pt.isel.mpd.music_stand.streams.dto;

import java.util.List;

public class GetAlbum2Dto {
    private Album2Dto album;

    public Album2Dto getAlbum() {
        return album;
    }

    public List<TrackDto> getTracks() {
        return album.getTracks();
    }
}
