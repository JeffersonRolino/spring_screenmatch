package jeffersonrolino.com.github.screenmatch.service;

import jeffersonrolino.com.github.screenmatch.dto.SeriesDTO;
import jeffersonrolino.com.github.screenmatch.model.Series;
import jeffersonrolino.com.github.screenmatch.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeriesService {

    @Autowired
    private SeriesRepository repository;

    public List<SeriesDTO> getAllSeries(){
        return convertSeriesToSeriesDTO(repository.findAll());
    }

    public List<SeriesDTO> getTop5Series() {
        return convertSeriesToSeriesDTO(repository.findTop5ByOrderByReviewDesc());

    }

    public List<SeriesDTO> getReleases() {
        return convertSeriesToSeriesDTO(repository.getNewerEpisodes());
    }

    private List<SeriesDTO> convertSeriesToSeriesDTO(List<Series> series){
        return series.stream()
                .map(s -> new SeriesDTO(s.getId(), s.getTitle(), s.getTotalSeasons(), s.getReview(), s.getGenre(), s.getActors(), s.getPoster(), s.getSynopses()))
                .collect(Collectors.toList());
    }

    public SeriesDTO getSeriesById(Long id) {
        Optional<Series> serie = repository.findById(id);
        if(serie.isPresent()){
            Series s = serie.get();
            return new SeriesDTO(s.getId(), s.getTitle(), s.getTotalSeasons(), s.getReview(), s.getGenre(), s.getActors(), s.getPoster(), s.getSynopses());
        }
        return null;
    }
}
