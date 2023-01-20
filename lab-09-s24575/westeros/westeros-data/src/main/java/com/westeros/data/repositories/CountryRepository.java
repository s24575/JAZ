package com.westeros.data.repositories;

import com.westeros.data.model.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {
    Country findByName(String string);
}
