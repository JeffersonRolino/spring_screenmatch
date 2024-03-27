package jeffersonrolino.com.github.screenmatch.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Table(name="episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer episode;
    private Double review;
    private LocalDate releaseDate;
    private Integer season;
    @ManyToOne
    private Series series;

    public Episode() {
    }

    public Episode(Integer seasonNumber, EpisodeData episodeData) {
        this.season = seasonNumber;
        this.title = episodeData.title();
        this.episode = episodeData.episode();
        try {
            this.review = Double.valueOf(episodeData.review());
        } catch (NumberFormatException exception){
            this.review = 0.0;
        }
        try {
            this.releaseDate = LocalDate.parse(episodeData.releaseDate());
        } catch (DateTimeParseException exception){
            this.releaseDate = null;
        }
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

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    public Double getReview() {
        return review;
    }

    public void setReview(Double review) {
        this.review = review;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return  "title='" + title + '\'' +
                ", episode=" + episode +
                ", season=" + season +
                ", review=" + review +
                ", releaseDate=" + releaseDate;
    }
}
