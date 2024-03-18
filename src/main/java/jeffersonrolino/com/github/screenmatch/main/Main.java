package jeffersonrolino.com.github.screenmatch.main;

import jeffersonrolino.com.github.screenmatch.model.SeasonData;
import jeffersonrolino.com.github.screenmatch.model.SeriesData;
import jeffersonrolino.com.github.screenmatch.service.DataConverter;
import jeffersonrolino.com.github.screenmatch.service.QueryApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Main {
    Scanner scanner = new Scanner(System.in);
    QueryApi queryApi = new QueryApi();
    DataConverter dataConverter = new DataConverter();
    private final String API_ADDRESS = "https://www.omdbapi.com/?t=";
    private final String apikey;
    private List<SeriesData> seriesDataList = new ArrayList<>();

    @Autowired
    public Main(@Value("${apikey}") String apikey) {
        this.apikey = apikey;
    }

    public void showMenu() {
        int option = 42;

        while (option != 0){
            String menu = """
                
                1 - Buscar séries
                2 - Buscar episódios
                3 - Listar Séries Buscadas
                                
                0 - Sair
                """;

            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    searchSeries();
                    break;
                case 2:
                    searchEpisodeBySeries();
                    break;
                case 3:
                    showSeries();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida...");
            }
        }
    }

    private void searchSeries(){
        SeriesData seriesData = getSeriesData();
        seriesDataList.add(seriesData);
        System.out.println(seriesData);
    }

    private SeriesData getSeriesData(){
        System.out.println("Digite o nome da série para busca: ");
        String query = scanner.nextLine().toLowerCase().replace(' ', '+');
        String queryString = API_ADDRESS + query + "&apikey=" + apikey;
        String json = queryApi.retrieveData(queryString);
        return dataConverter.convertData(json, SeriesData.class);
    }

    private void searchEpisodeBySeries(){
        SeriesData seriesData = getSeriesData();
        List<SeasonData> seasons = new ArrayList<>();
        for(int i = 0; i < seriesData.totalSeasons(); i++){
            String jsonSeasons = queryApi.retrieveData(API_ADDRESS + seriesData.title().replace(" ", "+") + "&Season=" + String.valueOf(i + 1) + "&apikey=" + apikey);
            SeasonData seasonData = dataConverter.convertData(jsonSeasons, SeasonData.class);
            seasons.add(seasonData);
        }
        seasons.forEach(System.out::println);
    }

    private void showSeries(){
        System.out.println();
        seriesDataList.forEach(System.out::println);
    }
}
