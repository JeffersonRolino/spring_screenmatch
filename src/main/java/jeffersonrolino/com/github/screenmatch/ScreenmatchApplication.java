package jeffersonrolino.com.github.screenmatch;

import jeffersonrolino.com.github.screenmatch.main.Main;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	@Value("${apikey}")
	String apikey;

	@Value("${openAIkey}")
	String openAIkey;


	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(apikey, openAIkey);
		main.showMenu();
	}
}
