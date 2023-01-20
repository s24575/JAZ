package com.westeros.data.repositories;

import com.westeros.data.model.Company;
import com.westeros.data.model.Language;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Integer> {
    Language findByEnglishName(String string);
}
