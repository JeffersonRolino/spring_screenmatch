package jeffersonrolino.com.github.screenmatch.service;

import jeffersonrolino.com.github.screenmatch.dto.SeriesDTO;
import jeffersonrolino.com.github.screenmatch.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeriesService {

    @Autowired
    private SeriesRepository repository;

    public List<SeriesDTO> getAllSeries(){
        return repository.findAll().stream()
                .map(s -> new SeriesDTO(s.getId(), s.getTitle(), s.getTotalSeasons(), s.getReview(), s.getGenre(), s.getActors(), s.getPoster(), s.getSynopses()))
                .collect(Collectors.toList());
    }

}
