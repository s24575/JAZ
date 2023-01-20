package com.westeros.moviesclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MoviesClientConfiguration {

    @Bean
    public IMoviesClientSettings getSettings(){
        return new MoviesClientSettings("e5b1db2d7b078410d765634b86c0aba6", "api.themoviedb.org", 3);
    }
}
