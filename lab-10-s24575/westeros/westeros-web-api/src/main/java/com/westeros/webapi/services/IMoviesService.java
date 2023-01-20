package com.westeros.webapi.services;

import com.westeros.webapi.contracts.ActorCharacterDto;
import com.westeros.webapi.contracts.MovieDto;
import com.westeros.webapi.contracts.MovieSummaryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMoviesService {
    List<MovieSummaryDto> getAllMovies();
    void saveMovie(MovieDto movieDto);
    void updateMovie(long movieId, MovieDto movieDto);
    MovieDto getMovie(long movieId);
    void deleteMovie(long movieId);
    List<ActorCharacterDto> getAllActorCharacter(long movieId);
    void saveActorCharacter(long movieId, ActorCharacterDto actorCharacterDto);
}
