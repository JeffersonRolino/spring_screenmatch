package jeffersonrolino.com.github.screenmatch.dto;

import jeffersonrolino.com.github.screenmatch.model.Category;

public record SeriesDTO(Long id,
        String title,
        Integer totalSeasons,
        double review,
        Category genre,
        String actors,
        String poster,
        String synopses) {}
