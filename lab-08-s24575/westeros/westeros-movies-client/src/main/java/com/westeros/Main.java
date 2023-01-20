package com.westeros;

import com.westeros.moviesclient.IMoviesClient;
import com.westeros.moviesclient.IMoviesClientSettings;
import com.westeros.moviesclient.MoviesClient;
import com.westeros.moviesclient.MoviesClientSettings;
import com.westeros.moviesclient.contract.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args){

        IMoviesClientSettings settings = new MoviesClientSettings("e5b1db2d7b078410d765634b86c0aba6", "http://api.themoviedb.org", 3);
        IMoviesClient moviesClient = new MoviesClient(settings);
        CheckItOut(moviesClient);
    }

    public static void CheckItOut(IMoviesClient moviesClient) {
        var from = LocalDate.now().minusMonths(1);
        var to = LocalDate.now();

        /**
         * pobieram pierwszą strone wyników filmów które zostały wyprodukowane w zeszłym miesiącu
         */
        PagedResultDto result = moviesClient.getByDateRange(from, to);
        var movies = result.getResults();

        /**
         * pobieram wszystkie strony wyników
         */
        for (int page = 1; page <= result.getTotalPages(); page++){
            movies.addAll(moviesClient.getByDateRange(from, to, page).getResults());
        }
        var detailedMovies = new ArrayList<MovieDto>();
        var allCredits = new ArrayList<CreditsDto>();

        for (var movie : movies)
        {
            /**
             * dla każdego filmu pobieram jego szczegóły
             */
            MovieDto detailedMovie = moviesClient.getMovie(movie.getId());
            detailedMovies.add(detailedMovie);

            /**
             * dla każdego filmu pobieram informacje o zespole i aktorach, który tworzył dany film
             */
            CreditsDto credits = moviesClient.getCredits(movie.getId());
            allCredits.add(credits);

            /**
             * dla każdego aktora pobieram jego szczegółowe informacje
             */
            for (var actorSummary : credits.getCast()) {
                ActorDto detailedActor = moviesClient.getActorDetails(actorSummary.getId());
            }
        }
    }
}
