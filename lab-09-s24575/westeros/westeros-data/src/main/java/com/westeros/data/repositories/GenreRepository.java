package com.westeros.data.repositories;

import com.westeros.data.model.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer> {
    Genre findByName(String string);
}
