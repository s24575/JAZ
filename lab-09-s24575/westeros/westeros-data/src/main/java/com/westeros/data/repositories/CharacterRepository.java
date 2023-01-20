package com.westeros.data.repositories;

import com.westeros.data.model.Character;
import com.westeros.data.model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends CrudRepository<Character, Long> {
    Character findByName(String string);
}
