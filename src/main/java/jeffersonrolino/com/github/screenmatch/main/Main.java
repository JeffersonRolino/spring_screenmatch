package jeffersonrolino.com.github.screenmatch.main;

import jeffersonrolino.com.github.screenmatch.model.EpisodeData;
import jeffersonrolino.com.github.screenmatch.model.SeasonData;
import jeffersonrolino.com.github.screenmatch.model.SeriesData;
import jeffersonrolino.com.github.screenmatch.service.DataConverter;
import jeffersonrolino.com.github.screenmatch.service.QueryApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Main {
    private final String apikey;
    private final String API_ADDRESS = "https://www.omdbapi.com/?t=";

    @Autowired
    public Main(@Value("${apikey}") String apikey) {
        this.apikey = apikey;
    }

    public void showMenu(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome da série para busca: ");
        String query = scanner.nextLine().toLowerCase().replace(' ', '+');

        String queryString = API_ADDRESS + query + "&apikey=" + apikey;
        QueryApi queryApi = new QueryApi();
        String json = queryApi.retrieveData(queryString);

        DataConverter dataConverter = new DataConverter();
        SeriesData seriesData = dataConverter.convertData(json, SeriesData.class);

		List<SeasonData> seasons = new ArrayList<>();

		for (int i = 0; i < seriesData.totalSeasons(); i++) {
			String jsonSeasons = queryApi.retrieveData(API_ADDRESS + query + "&Season=" + String.valueOf(i + 1) + "&apikey=" + apikey);
			SeasonData seasonData = dataConverter.convertData(jsonSeasons, SeasonData.class);
			seasons.add(seasonData);
		}

//		seasons.forEach(System.out::println);

//      Printing all Episodes for all seasons
        seasons.forEach(season -> season.episodes().forEach(episode -> System.out.println(episode.title())));

        List<EpisodeData> episodes = seasons.stream()
                .flatMap(season -> season.episodes().stream())
                .collect(Collectors.toList());

        System.out.println("\n Top 5 Episodes");
        episodes.stream()
                .filter(episode -> !episode.review().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(EpisodeData::review).reversed())
                .limit(5)
                .forEach(System.out::println);
    }
}
