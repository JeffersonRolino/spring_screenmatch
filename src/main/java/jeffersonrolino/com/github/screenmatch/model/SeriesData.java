package jeffersonrolino.com.github.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record SeriesData(@JsonAlias("Title") String title,
                         Integer totalSeasons,
                         @JsonAlias("imdbRating")
                         String review){

}
