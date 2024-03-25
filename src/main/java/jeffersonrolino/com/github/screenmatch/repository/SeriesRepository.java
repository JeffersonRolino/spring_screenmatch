package jeffersonrolino.com.github.screenmatch.repository;

import jeffersonrolino.com.github.screenmatch.model.Category;
import jeffersonrolino.com.github.screenmatch.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeriesRepository extends JpaRepository<Series, Long> {
    Optional<Series> findByTitleContainingIgnoreCase(String title);

    List<Series> findByActorsContainingIgnoreCaseAndReviewGreaterThanEqual(String actorName, Double review);

    List<Series> findTop5ByOrderByReviewDesc();
}
