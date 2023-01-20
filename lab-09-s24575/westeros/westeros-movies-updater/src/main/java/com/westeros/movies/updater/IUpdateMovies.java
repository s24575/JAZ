package com.westeros.movies.updater;

import com.westeros.data.model.Movie;

import java.time.LocalDate;
import java.util.Optional;

public interface IUpdateMovies {

    void updateByDateRange(LocalDate from, LocalDate to);
    public Optional<Movie> getMovie(int id);
}
