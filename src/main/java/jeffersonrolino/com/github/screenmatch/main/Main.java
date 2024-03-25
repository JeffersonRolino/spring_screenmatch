package jeffersonrolino.com.github.screenmatch.main;

import io.github.cdimascio.dotenv.Dotenv;
import jeffersonrolino.com.github.screenmatch.model.Episode;
import jeffersonrolino.com.github.screenmatch.model.SeasonData;
import jeffersonrolino.com.github.screenmatch.model.Series;
import jeffersonrolino.com.github.screenmatch.model.SeriesData;
import jeffersonrolino.com.github.screenmatch.repository.SeriesRepository;
import jeffersonrolino.com.github.screenmatch.service.DataConverter;
import jeffersonrolino.com.github.screenmatch.service.QueryApi;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    Scanner scanner = new Scanner(System.in);
    QueryApi queryApi = new QueryApi();
    DataConverter dataConverter = new DataConverter();
    Dotenv dotenv = Dotenv.load();
    private final String API_ADDRESS = "https://www.omdbapi.com/?t=";
    private final String apikey = dotenv.get("API_KEY");
    private List<SeriesData> seriesDataList = new ArrayList<>();
    private List<Series> seriesList = new ArrayList<>();
    private SeriesRepository repository;

    public Main(SeriesRepository seriesRepository) {
        this.repository = seriesRepository;
    }

    public void showMenu() {
        int option = 42;

        while (option != 0){
            String menu = """
                
                1 - Buscar séries
                2 - Buscar episódios
                3 - Listar Séries Buscadas
                4 - Buscar Série por Título
                5 - Buscar Séries por ator
                6 - Top 5 Séries
                7 - Buscar séries por categoria
                                
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
                case 4:
                    findSeriesByTitle();
                    break;
                case 5:
                    findSeriesByActor();
                    break;
                case 6:
                    findTop5Series();
                    break;
                case 7:
                    findSeriesByCategory();
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
        Series serie = new Series(seriesData);
//        seriesDataList.add(seriesData);
        repository.save(serie);
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
        showSeries();
        System.out.println("Escolha uma série pelo nome: ");
        String seriesName = scanner.nextLine();

        Optional<Series> series = repository.findByTitleContainingIgnoreCase(seriesName);

        if(series.isPresent()){
            var foundSeries = series.get();
            List<SeasonData> seasons = new ArrayList<>();
            for(int i = 0; i < foundSeries.getTotalSeasons(); i++){
                String jsonSeasons = queryApi.retrieveData(API_ADDRESS + foundSeries.getTitle().replace(" ", "+") + "&Season=" + String.valueOf(i + 1) + "&apikey=" + apikey);
                SeasonData seasonData = dataConverter.convertData(jsonSeasons, SeasonData.class);
                seasons.add(seasonData);
            }
            seasons.forEach(System.out::println);

            List<Episode> episodesList = seasons.stream()
                    .flatMap(d -> d.episodes().stream()
                            .map(e -> new Episode(d.number(), e)))
                    .collect(Collectors.toList());

            foundSeries.setEpisodes(episodesList);
            repository.save(foundSeries);
        }
        else {
            System.out.println("Série não encontrada...");
        }
    }

    private void showSeries(){
        seriesList = repository.findAll();
        seriesList.stream()
                .sorted(Comparator.comparing(Series::getGenre))
                .forEach(System.out::println);
    }

    private void findSeriesByTitle() {
        System.out.println("Escolha uma série pelo nome: ");
        String seriesName = scanner.nextLine();

        Optional<Series> searchedSeries = repository.findByTitleContainingIgnoreCase(seriesName);

        if (searchedSeries.isPresent()){
            System.out.println("\nDados da Série: ");
            System.out.println(searchedSeries.get());
        }
        else {
            System.out.println("Série não encontrada...");
        }
    }

    private void findSeriesByActor() {
        System.out.println("Qual o nome do Ator ou Atriz?");
        String actorName = scanner.nextLine();
        System.out.println("Avaliações à partir de que valor?");
        double review = scanner.nextDouble();

        List<Series> searchedSeries = repository.findByActorsContainingIgnoreCaseAndReviewGreaterThanEqual(actorName, review);

        System.out.println("\nSéries em que " + actorName + " trabalhou: ");
        searchedSeries.forEach(s -> System.out.println(s.getTitle() + ": " + s.getReview()));
    }

    private void findTop5Series() {
        List<Series> topSeries = repository.findTop5ByOrderByReviewDesc();
        topSeries.forEach(s -> System.out.println(s.getTitle() + ": " + s.getReview()));
    }
}
