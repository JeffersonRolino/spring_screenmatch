package jeffersonrolino.com.github.screenmatch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeriesController {
    @GetMapping("/series")
    public String getSeries(){
        return "Aqui vão ser listadas as Séries...";
    }
}
