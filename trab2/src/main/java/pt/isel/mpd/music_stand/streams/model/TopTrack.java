package pt.isel.mpd.music_stand.streams.model;

public class TopTrack {
    private String name;

    private int duration;
    private String artistMbid;
    private int rank;
    private String artistName;

    public TopTrack(String name,
                    int duration,
                    int rank,
                    String artistName,
                    String artistMbid)  {
        this.name = name; this.duration = duration;
        this.rank = rank;
        this.artistMbid = artistMbid;
        this.artistName = artistName;
    }

    public String getName() { return name; }
    public String getArtistName() { return artistName; }
    public int getDuration() { return duration; }
    public int getRank() { return rank; }
    public String getArtistMbid() { return artistMbid; }

    @Override
    public String toString() {
        return "{"
                + "name=" + '"' + getName() + '"'
                + ", artist=" + '"' + getArtistName()+ '"'
                + ", duration=" +  getDuration()
                + ", rank=" + getRank()
                + "}";
    }
}
