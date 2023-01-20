package com.westeros;

import com.westeros.movies.controllers.UpdaterController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "com.westeros")
public class WesterosMoviesUpdaterApplication {
    private final UpdaterController updaterController;

    public WesterosMoviesUpdaterApplication(UpdaterController updaterController){
        this.updaterController = updaterController;
    }

    public static void main(String[] args) {
        SpringApplication.run(WesterosMoviesUpdaterApplication.class, args);
    }

}
