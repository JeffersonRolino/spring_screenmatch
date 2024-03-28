package jeffersonrolino.com.github.screenmatch.repository;

import jeffersonrolino.com.github.screenmatch.model.Category;
import jeffersonrolino.com.github.screenmatch.model.Episode;
import jeffersonrolino.com.github.screenmatch.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SeriesRepository extends JpaRepository<Series, Long> {
    Optional<Series> findByTitleContainingIgnoreCase(String title);

    List<Series> findByActorsContainingIgnoreCaseAndReviewGreaterThanEqual(String actorName, Double review);

    List<Series> findTop5ByOrderByReviewDesc();

    List<Series> findByGenre(Category category);

    List<Series> findByTotalSeasonsLessThanEqualAndReviewGreaterThanEqual(int totalSeasons, Double review);

    @Query(value = "select s from Series s WHERE s.totalSeasons <= :totalSeasons AND s.review >= :review")
    List<Series> seriesBySeasonAndReview(int totalSeasons, Double review);

    @Query("SELECT e FROM Series s JOIN s.episodes e WHERE e.title LIKE %:episodeExcerpt%")
    List<Episode> episodesByExcerpt(String episodeExcerpt);

    @Query("SELECT e FROM Series s JOIN s.episodes e WHERE s = :series ORDER BY e.review DESC LIMIT 5")
    List<Episode> top5EpisodesBySeries(Series series);

    @Query("SELECT e FROM Series s JOIN s.episodes e WHERE s = :series AND YEAR(e.releaseDate) >= :releasedYear")
    List<Episode> episodesBySeriesAndYear(Series series, int releasedYear);

    List<Series> findTop5ByOrderByEpisodesReleaseDateDesc();
}
