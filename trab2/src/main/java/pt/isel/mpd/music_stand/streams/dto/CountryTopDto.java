package pt.isel.mpd.music_stand.streams.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import static pt.isel.mpd.utils.resources.BuildUtils.TODO;
import static pt.isel.mpd.utils.resources.BuildUtils.TO_COMPLETE;

public class CountryTopDto {

    public CountryTopDto() {
        TO_COMPLETE("CountryTopDto");
    }


    public List<TopTrackDto> getTracks() {
        TODO("getTracks");
        return null;
    }

    public String getCountry() {
        TODO("getCountry");
        return null;
    }
}
