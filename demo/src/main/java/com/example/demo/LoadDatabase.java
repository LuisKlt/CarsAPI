package com.example.demo;

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
      log.info("Preloading " + repository.save(new Car("BMW M3 2022", "540hp")));
      log.info("Preloading " + repository.save(new Car("McLaran Senna", "800hp")));
      log.info("Preloading " + repository.save(new Car("Audi RS6", "650hp")));
      log.info("Preloading " + repository.save(new Car("Mustang Mach 1", "500hp")));
      log.info("Preloading " + repository.save(new Car("Nissan GT-R", "450hp")));
    };
  }
}