package jeffersonrolino.com.github.screenmatch.model;

import java.util.OptionalDouble;

public class Series {
    private String title;
    private Integer totalSeasons;
    private double review;
    private Category genre;
    private String actors;
    private String poster;
    private String synopses;

    public Series(SeriesData seriesData){
        this.title = seriesData.title();
        this.totalSeasons = seriesData.totalSeasons();
        this.review = OptionalDouble.of(Double.parseDouble(seriesData.review())).orElse(0.0);
        this.genre = Category.fromString(seriesData.genre().split(",")[0].trim());
        this.actors = seriesData.actors();
        this.poster = seriesData.poster();
        this.synopses = seriesData.synopses();
    }
}
