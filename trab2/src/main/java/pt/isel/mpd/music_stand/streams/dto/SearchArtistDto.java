package pt.isel.mpd.music_stand.streams.dto;

public class SearchArtistDto {
    private SearchArtistResultsDto results;

    public SearchArtistDto(SearchArtistResultsDto results) {
        this.results = results;
    }

    public SearchArtistResultsDto getResults() { return results; }
}
