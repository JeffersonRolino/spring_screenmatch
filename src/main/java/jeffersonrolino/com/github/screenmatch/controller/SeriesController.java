package jeffersonrolino.com.github.screenmatch.controller;

import jeffersonrolino.com.github.screenmatch.dto.SeriesDTO;
import jeffersonrolino.com.github.screenmatch.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SeriesController {

    @Autowired
    private SeriesRepository repository;

    @GetMapping("/series")
    public List<SeriesDTO> getSeries(){
        return repository.findAll().stream()
                .map(s -> new SeriesDTO(s.getId(), s.getTitle(), s.getTotalSeasons(), s.getReview(), s.getGenre(), s.getActors(), s.getPoster(), s.getSynopses()))
                .collect(Collectors.toList());
    }
}
