//package jeffersonrolino.com.github.screenmatch;
//
//import jeffersonrolino.com.github.screenmatch.main.Main;
//import jeffersonrolino.com.github.screenmatch.repository.SeriesRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class ScreenmatchApplicationSemWeb implements CommandLineRunner {
//	@Autowired
//	private SeriesRepository repository;
//
//	public static void main(String[] args) {
//		SpringApplication.run(ScreenmatchApplicationSemWeb.class, args);
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		Main main = new Main(repository);
//		main.showMenu();
//	}
//}
