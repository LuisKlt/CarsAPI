package payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(CarRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new Car("BMW", "M3 Competition", 2022, 510, 1.430)));
			log.info("Preloading " + repository.save(new Car("McLaren", "Senna", 2020, 750, 1.200)));
		};
	}
}
