package com.westeros;

import com.westeros.moviesclient.IMoviesClient;
import com.westeros.moviesclient.IMoviesClientSettings;
import com.westeros.moviesclient.MoviesClient;
import com.westeros.moviesclient.MoviesClientSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class WesterosMoviesClientConfiguration {
    @Bean
    @Scope("prototype")
    public MoviesClientSettings moviesClientSettings(){
        return new MoviesClientSettings("e5b1db2d7b078410d765634b86c0aba6", "http://api.themoviedb.org", 3);
    }

    @Bean
    @Scope("prototype")
    public IMoviesClient client(IMoviesClientSettings moviesClientSettings){
        return new MoviesClient(moviesClientSettings);
    }

}
