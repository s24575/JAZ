package com.westeros.movies.mappers;

import com.westeros.data.model.Language;
import com.westeros.moviesclient.contract.dictionaries.LanguageSummaryDto;
import org.springframework.stereotype.Component;

@Component
public class LanguageMapper implements IMapEntities<LanguageSummaryDto, Language>{
    @Override
    public Language map(LanguageSummaryDto languageSummaryDto) {
        return map(languageSummaryDto, new Language());
    }

    @Override
    public Language map(LanguageSummaryDto languageSummaryDto, Language language) {
        language.setName(languageSummaryDto.getName());
        return language;
    }
}
