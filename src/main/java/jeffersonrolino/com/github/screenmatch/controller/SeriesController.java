package jeffersonrolino.com.github.screenmatch.controller;

import jeffersonrolino.com.github.screenmatch.dto.EpisodeDTO;
import jeffersonrolino.com.github.screenmatch.dto.SeriesDTO;
import jeffersonrolino.com.github.screenmatch.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SeriesController {

    @Autowired
    private SeriesService service;

    @GetMapping
    public List<SeriesDTO> getSeries(){
        return service.getAllSeries();
    }

    @GetMapping("/top5")
    public List<SeriesDTO> getTop5Series(){
        return service.getTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<SeriesDTO> getReleases(){
        return service.getReleases();
    }

    @GetMapping("/{id}")
    public SeriesDTO getSeriesById(@PathVariable Long id){
        return service.getSeriesById(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodeDTO> getAllSeasons(@PathVariable Long id) {
        return service.getAllSeasons(id);
    }

    @GetMapping("/{id}/temporadas/{season}")
    public List<EpisodeDTO> getAllSeasonByNumber(@PathVariable Long id, @PathVariable int season){
        return service.getAllSeasonByNumber(id, season);
    }

    @GetMapping("/categoria/{nomeGenero}")
    public List<SeriesDTO> getSeriesByGenre(@PathVariable String nomeGenero){
        return service.getSeriesByGenre(nomeGenero);
    }
}
