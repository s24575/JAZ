package com.westeros.movies.controllers;

import com.westeros.data.model.Movie;
import com.westeros.movies.updater.IUpdateMovies;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/updater")
public class UpdaterController {
    private IUpdateMovies moviesUpdater;
    public UpdaterController(IUpdateMovies moviesUpdater){
        this.moviesUpdater = moviesUpdater;
    }
    @GetMapping(path="/start")
    public void getMovies(@RequestParam LocalDate from, @RequestParam LocalDate to){
        moviesUpdater.updateByDateRange(from, to);
    }

    @GetMapping(path="/id/{id}")
    public Optional<Movie> getMovie(@PathVariable int id){
        return moviesUpdater.getMovie(id);
    }
}
