package jeffersonrolino.com.github.screenmatch.model;

import jakarta.persistence.*;
import jeffersonrolino.com.github.screenmatch.service.QueryChatGPT;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Entity
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer totalSeasons;
    private double review;
    @Enumerated(EnumType.STRING)
    private Category genre;
    private String actors;
    private String poster;
    private String synopses;
    @Transient
    private List<Episode> episodes = new ArrayList<>();

    public Series() {
    }

    public Series(SeriesData seriesData){
        this.title = seriesData.title();
        this.totalSeasons = seriesData.totalSeasons();
        this.review = OptionalDouble.of(Double.parseDouble(seriesData.review())).orElse(0.0);
        this.genre = Category.fromString(seriesData.genre().split(",")[0].trim());
        this.actors = seriesData.actors();
        this.poster = seriesData.poster();
        this.synopses = seriesData.synopses();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(Integer totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public double getReview() {
        return review;
    }

    public void setReview(double review) {
        this.review = review;
    }

    public Category getGenre() {
        return genre;
    }

    public void setGenre(Category genre) {
        this.genre = genre;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSynopses() {
        return synopses;
    }

    public void setSynopses(String synopses) {
        this.synopses = synopses;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    @Override
    public String toString() {
        return  "genre=" + genre +
                ", title='" + title + '\'' +
                ", totalSeasons=" + totalSeasons +
                ", review=" + review +
                ", actors='" + actors + '\'' +
                ", poster='" + poster + '\'' +
                ", synopses='" + synopses + '\'';
    }
}
