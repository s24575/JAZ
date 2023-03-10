package com.westeros.movies.mappers;

import com.westeros.data.model.*;
import com.westeros.data.model.Character;
import com.westeros.moviesclient.contract.ActorDto;
import com.westeros.moviesclient.contract.ActorSummaryDto;
import com.westeros.moviesclient.contract.CompanySummaryDto;
import com.westeros.moviesclient.contract.MovieDto;
import com.westeros.moviesclient.contract.dictionaries.CountryDto;
import com.westeros.moviesclient.contract.dictionaries.GenreSummaryDto;
import com.westeros.moviesclient.contract.dictionaries.LanguageSummaryDto;

public interface ICatalogMappers {

    IMapEntities<ActorDto, Actor> forActor();
    IMapEntities<ActorSummaryDto, Character> forCharacter();
    IMapEntities<CompanySummaryDto, Company> forCompany();
    IMapEntities<CountryDto, Country> forCountry();
    IMapEntities<GenreSummaryDto, Genre> forGenre();
    IMapEntities<LanguageSummaryDto, Language> forLanguage();
    IMapEntities<MovieDto, Movie> forMovie();

}
