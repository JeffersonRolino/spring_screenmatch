package jeffersonrolino.com.github.screenmatch.repository;

import jeffersonrolino.com.github.screenmatch.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeriesRepository extends JpaRepository<Series, Long> {
    Optional<Series> findByTitleContainingIgnoreCase(String title);
}
