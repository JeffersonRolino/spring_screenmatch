package jeffersonrolino.com.github.screenmatch;

import jeffersonrolino.com.github.screenmatch.model.EpisodeData;
import jeffersonrolino.com.github.screenmatch.model.SeasonData;
import jeffersonrolino.com.github.screenmatch.model.SeriesData;
import jeffersonrolino.com.github.screenmatch.service.DataConverter;
import jeffersonrolino.com.github.screenmatch.service.QueryApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {
	@Value("${apikey}")
	private String apikey;
	private String apiAddress = "https://www.omdbapi.com/";
	private String searchedMovieOrSeries = "game+of+thrones";


	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String queryString = apiAddress + "?t=" + searchedMovieOrSeries + "&apikey=" + apikey;
		QueryApi queryApi = new QueryApi();
		String json = queryApi.retrieveData(queryString);
		System.out.println(json);

		DataConverter dataConverter = new DataConverter();
		SeriesData seriesData = dataConverter.convertData(json, SeriesData.class);
		System.out.println(seriesData);

		String jsonEpisode = queryApi.retrieveData("https://www.omdbapi.com/?t=game+of+thrones&Season=1&Episode=1&apikey=" + apikey);
		EpisodeData episodeData = dataConverter.convertData(jsonEpisode, EpisodeData.class);
		System.out.println(episodeData);

		List<SeasonData> seasonDataList = new ArrayList<>();

		for (int i = 0; i < seriesData.totalSeasons(); i++) {
			String jsonSeasons = queryApi.retrieveData("https://www.omdbapi.com/?t=game+of+thrones&Season=" + String.valueOf(i + 1) + "&apikey=" + apikey);
			SeasonData seasonData = dataConverter.convertData(jsonSeasons, SeasonData.class);
			seasonDataList.add(seasonData);
		}

		seasonDataList.forEach(System.out::println);
	}
}
