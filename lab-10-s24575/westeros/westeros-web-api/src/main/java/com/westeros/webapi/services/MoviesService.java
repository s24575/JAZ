package com.westeros.webapi.services;

import com.westeros.data.model.Actor;
import com.westeros.data.model.Character;
import com.westeros.data.model.Movie;
import com.westeros.data.repositories.ICatalogData;
import com.westeros.webapi.contracts.ActorCharacterDto;
import com.westeros.webapi.contracts.MovieDto;
import com.westeros.webapi.contracts.MovieSummaryDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MoviesService implements IMoviesService {
    @Autowired
    private final ICatalogData dbCatalog;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    public MoviesService(ICatalogData dbCatalog, ModelMapper modelMapper){
        this.dbCatalog = dbCatalog;
        this.modelMapper = modelMapper;
    }

    public List<MovieSummaryDto> getAllMovies(){
        System.out.println("GET ALL MOVIES");

        List<MovieSummaryDto> movieSummaries = new ArrayList<>();

        var movies = dbCatalog.getMovies().findAll();
        for (var movie : movies) {
            var movieSummary = modelMapper.map(movie, MovieSummaryDto.class);
            movieSummaries.add(movieSummary);
        }

        return movieSummaries;
    }

    public void saveMovie(MovieDto movieDto){
        System.out.println("SAVE MOVIE");
        var movie = modelMapper.map(movieDto, Movie.class);
        dbCatalog.getMovies().save(movie);
    }

    public void updateMovie(long movieId, MovieDto movieDto){
        System.out.println("UPDATE MOVIE");
        var movie = dbCatalog.getMovies().findById(movieId);
        if(movie.isPresent()){
            modelMapper.map(movieDto, movie.get());
            dbCatalog.getMovies().save(movie.get());
        }
    }

    public MovieDto getMovie(long movieId){
        System.out.println("GET MOVIE");
        var movie = dbCatalog.getMovies().findById(movieId);
        return movie.map(value -> modelMapper.map(value, MovieDto.class)).orElse(null);
    }

    public void deleteMovie(long movieId){
        System.out.println("DELETE MOVIE");
        var movie = dbCatalog.getMovies().findById(movieId);
        movie.ifPresent(value -> dbCatalog.getMovies().delete(value));
    }

    public List<ActorCharacterDto> getAllActorCharacter(long movieId){
        System.out.println("GET ACTORS/CHARACTERS");

        List<ActorCharacterDto> actors = new ArrayList<>();

        var movie = dbCatalog.getMovies().findById(movieId);
        if(movie.isPresent()){
            var characters = movie.get().getCharacters();
            for(var character : characters){
                var actor = new ActorCharacterDto();
                actor.setCharacterName(character.getName());
                actor.setActorName(character.getActor().getName());
                actors.add(actor);
            }
            return actors;
        }
        return null;
    }

    public void saveActorCharacter(long movieId, ActorCharacterDto actorCharacterDto){
        System.out.println("SAVE ACTOR/CHARACTER");

        var movie = dbCatalog.getMovies().findById(movieId);
        if(movie.isPresent()){
            Actor actor = new Actor();
            actor.setName(actorCharacterDto.getActorName());

            Character character = new Character();
            character.setName(actorCharacterDto.getCharacterName());

            actor.getCharacters().add(character);
            character.setActor(actor);
            character.setMovie(movie.get());

            dbCatalog.getActors().save(actor);
            dbCatalog.getCharacter().save(character);
            movie.get().getCharacters().add(character);
        }
    }
}
