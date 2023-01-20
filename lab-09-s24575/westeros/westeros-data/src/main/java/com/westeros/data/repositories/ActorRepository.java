package com.westeros.data.repositories;

import com.westeros.data.model.Actor;
import com.westeros.data.model.Character;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends CrudRepository<Actor, Integer> {
    Actor findByName(String string);
}
