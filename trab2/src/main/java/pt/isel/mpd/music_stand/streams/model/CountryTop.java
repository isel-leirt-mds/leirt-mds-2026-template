package pt.isel.mpd.music_stand.streams.model;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import static pt.isel.mpd.utils.resources.BuildUtils.TODO;
import static pt.isel.mpd.utils.resources.BuildUtils.TO_COMPLETE;

public class CountryTop implements Iterable<TopTrack> {
    private final String countryName;

    public CountryTop(String countryName ) {
        this.countryName = countryName;
        TO_COMPLETE("top tracks missed");
    }

    @Override
    public Iterator<TopTrack> iterator() {
        TODO("iterator");
        return null;
    }

    public String getCountryName() {
        return countryName;
    }

    public Stream<TopTrack> getTracks() {
        TODO("getTracks");
        return null;
    }
}
