package pt.isel.mpd.music_stand.streams.dto;

import com.google.gson.annotations.SerializedName;

import static pt.isel.mpd.utils.resources.BuildUtils.TODO;
import static pt.isel.mpd.utils.resources.BuildUtils.TO_COMPLETE;

public class TopTrackDto {


    public TopTrackDto(String name,  int duration, ArtistDto artist) {
        TO_COMPLETE("TopTrackDto");
    }

    public String getName() {
        TODO("getName");
        return null;
    }

    public String getArtistName() {
        TODO("getArtistName");
        return null;
    }
    public int getDuration() {
        TODO("getDuration");
        return 0;
    }

    public int getRank()  {
        TODO("getRank");
        return 0;
    }

    public String getArtistMbid() {
        TODO("getArtistMbid");
        return null;
    }

    public String toString() {
        return "{"
                + "name=" + '"' + getName() + '"'
                + ", artist=" + '"' + getArtistName()+ '"'
                + ", duration=" +  getDuration()
                + ", rank=" + getRank()
                + "}";
    }

}
