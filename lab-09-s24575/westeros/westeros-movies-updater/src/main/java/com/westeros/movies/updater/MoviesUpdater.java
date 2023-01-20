package com.westeros.movies.updater;


import com.westeros.data.model.*;
import com.westeros.data.model.Character;
import com.westeros.data.repositories.ICatalogData;
import com.westeros.moviesclient.IMoviesClient;
import com.westeros.moviesclient.IMoviesDictionariesClient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MoviesUpdater implements IUpdateMovies{

    private final ICatalogData dbCatalog;
    private final IMoviesClient moviesClient;
    private final IMoviesDictionariesClient moviesDictionariesClient;

    private static boolean hasSetDictionaries = false;

    public MoviesUpdater(ICatalogData dbCatalog, IMoviesClient moviesClient, IMoviesDictionariesClient moviesDictionariesClient) {
        this.dbCatalog = dbCatalog;
        this.moviesClient = moviesClient;
        this.moviesDictionariesClient = moviesDictionariesClient;
    }

    private void setDictionaries(){
        if(!hasSetDictionaries){
            var languages = moviesDictionariesClient.getLanguages();
            for(var language : languages){
                Language db_language = dbCatalog.getLanguages().findByEnglishName(language.getEnglishName());
                if(db_language == null){
                    db_language = new Language();
                    db_language.setEnglishName(language.getEnglishName());
                    db_language.setName(language.getName());
                    dbCatalog.getLanguages().save(db_language);
                }
            }
            var genres = moviesDictionariesClient.getGenres();
            for(var genre : genres){
                Genre db_genre = dbCatalog.getGenres().findByName(genre.getName());
                if(db_genre == null) {
                    db_genre = new Genre();
                    db_genre.setName(genre.getName());
                    db_genre.setSourceId(0);
                    dbCatalog.getGenres().save(db_genre);
                }
            }
            var countries = moviesDictionariesClient.getCountries();
            for(var country : countries){
                Country db_country = dbCatalog.getCountries().findByName(country.getEnglishName());
                if(db_country == null){
                    db_country = new Country();
                    db_country.setName(country.getEnglishName());
                    dbCatalog.getCountries().save(db_country);
                }
            }
            hasSetDictionaries = true;
        }
    }

    @Override
    public void updateByDateRange(LocalDate from, LocalDate to) {
        setDictionaries();

        var result = moviesClient.getByDateRange(from, to);
        var movieSummaries = result.getResults();
        for (int page = 1; page <= result.getTotalPages(); page++){
            movieSummaries.addAll(moviesClient.getByDateRange(from, to, page).getResults());
        }

        for(var movieSummary : movieSummaries){
            System.out.println(movieSummary.getTitle());
        }
        for(var movieSummary : movieSummaries){
            var m = moviesClient.getMovie(movieSummary.getId());
            if(m.getTitle() != null && dbCatalog.getMovies().findByOriginalTitle(m.getTitle()) != null) continue;
            Movie db_movie = new Movie();

            System.out.printf("Movie: %d / %s / %s%n", m.getId(), m.getTitle(), m.getReleaseDate().toString());

            db_movie.setSourceId(0);
            db_movie.setAdult(m.isAdult());
            db_movie.setBackdropPath(m.getBackdropPath());
            db_movie.setBudget(m.getBudget());
            db_movie.setHomepage(m.getHomepage());
            db_movie.setOriginalLanguage(m.getOriginalLanguage());
            db_movie.setOriginalTitle(m.getOriginalTitle());
            db_movie.setOverview(m.getOverview());
            db_movie.setPopularity(m.getPopularity());
            db_movie.setPosterPath(m.getPosterPath());
            db_movie.setReleaseDate(m.getReleaseDate());
            db_movie.setRuntime(m.getRuntime());
            db_movie.setVoteCount(m.getVoteCount());
            db_movie.setVoteAverage(m.getVoteAverage());

            // Companies
            List<Company> companies = new ArrayList<>();
            for(var company : m.getProductionCompanies()){
                Company db_company = dbCatalog.getCompanies().findByName(company.getName());
                if(db_company == null){
                    db_company = new Company();
                    db_company.setSourceId(0);
                    db_company.setLogoPath(company.getLogoPath());
                    db_company.setName(company.getName());
                    db_company.setOriginCountry(company.getOriginCountry());
                    dbCatalog.getCompanies().save(db_company);
                }
                db_company.getMovies().add(db_movie);
                companies.add(db_company);
            }
            db_movie.setProductionCompanies(companies);

            // Countries
            List<Country> countries = new ArrayList<>();
            for(var country : m.getProductionCountries()){
                Country db_country = dbCatalog.getCountries().findByName(country.getName());
                if(db_country == null){
                    System.out.println("Unknown country: " + country.getName());
                    continue;
                }
                db_country.getMovies().add(db_movie);
                countries.add(db_country);
            }
            db_movie.setProductionCountries(countries);

            // Genres
            List<Genre> genres = new ArrayList<>();
            for(var genre : m.getGenres()){
                Genre db_genre = dbCatalog.getGenres().findByName(genre.getName());
                if(db_genre == null){
                    System.out.println("Unknown genre: " + genre.getName());
                    continue;
                }
                db_genre.getMovies().add(db_movie);
                genres.add(db_genre);
            }
            db_movie.setGenres(genres);

            // Languages
            List<Language> languages = new ArrayList<>();
            for(var language : m.getSpokenLanguages()){
                Language db_language = dbCatalog.getLanguages().findByEnglishName(language.getEnglishName());
                if(db_language == null){
                    System.out.println("Unknown language: " + language.getName());
                    continue;
                }
                db_language.getMovies().add(db_movie);
                languages.add(db_language);
            }
            db_movie.setSpokenLanguages(languages);

            // Characters + Actors
            var credits = moviesClient.getCredits(movieSummary.getId());
            List<Character> characters = new ArrayList<>();
            for(var actorSummary : credits.getCast()) {
                var db_character = new Character();
                db_character.setName(actorSummary.getCharacter());
                db_character.setMovie(db_movie);

                var actor = moviesClient.getActorDetails(actorSummary.getId());
                Actor db_actor = dbCatalog.getActors().findByName(actor.getName());
                if (db_actor == null) {
                    db_actor = new Actor();
                    db_actor.setName(actor.getName());
                    db_actor.setProfilePath(actor.getProfilePath());
                    if (!actor.getAlsoKnownAs().isEmpty()) {
                        db_actor.setAlsoKnownAs(actor.getAlsoKnownAs().get(0));
                    }
                    db_actor.setBiography(actor.getBiography());
                    db_actor.setBirthday(actor.getBirthday());
                    db_actor.setDeathday(actor.getDeathday());
                    db_actor.setPopularity(actor.getPopularity());
                }
                db_character.setActor(db_actor);
                db_actor.getCharacters().add(db_character);
                dbCatalog.getActors().save(db_actor);
                dbCatalog.getCharacters().save(db_character);
            }
            db_movie.setCharacters(characters);

            dbCatalog.getMovies().save(db_movie);
        }
        System.out.println("finished");
    }

    @Override
    public Optional<Movie> getMovie(int id){
        return dbCatalog.getMovies().findById(Long.valueOf(id));
    }
}
