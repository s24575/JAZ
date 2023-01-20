package com.westeros.webapi.controllers;

import com.westeros.webapi.contracts.ActorCharacterDto;
import com.westeros.webapi.contracts.MovieDto;
import com.westeros.webapi.contracts.MovieSummaryDto;
import com.westeros.webapi.services.IMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class Controller {
    @Autowired
    private final IMoviesService moviesService;

    @Autowired
    public Controller(IMoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @GetMapping
    @ResponseBody
    public List<MovieSummaryDto> getAllMovies(){
        return moviesService.getAllMovies();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void saveMovie(@RequestBody MovieDto movieDto){
        moviesService.saveMovie(movieDto);
    }

    @PutMapping(value="/{id}")
    @ResponseBody
    public void updateMovie(@PathVariable long id, @RequestBody MovieDto movieDto){
        moviesService.updateMovie(id, movieDto);
    }

    @GetMapping(value="/{id}")
    @ResponseBody
    public MovieDto getMovie(@PathVariable long id){
        return moviesService.getMovie(id);
    }

    @DeleteMapping(value="/{id}")
    @ResponseBody
    public void deleteMovie(@PathVariable long id){
        moviesService.deleteMovie(id);
    }

    @GetMapping(value="/{id}/actors")
    @ResponseBody
    public List<ActorCharacterDto> getActors(@PathVariable long id){
        return moviesService.getAllActorCharacter(id);
    }

    @PostMapping(value="/{id}/actors")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void saveActorCharacter(@PathVariable long id, @RequestBody ActorCharacterDto actorCharacterDto){
        moviesService.saveActorCharacter(id, actorCharacterDto);
    }
}
