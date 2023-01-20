package com.westeros.data.repositories;

import com.westeros.data.model.Genre;
import com.westeros.data.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends CrudRepository<Movie, Long> {
    Movie findByOriginalTitle(String string);
}
