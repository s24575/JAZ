package com.westeros.movies.mappers;

import com.westeros.data.model.Character;
import com.westeros.moviesclient.contract.ActorSummaryDto;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper implements IMapEntities<ActorSummaryDto, Character>{


    @Override
    public Character map(ActorSummaryDto actorSummaryDto) {
        return map(actorSummaryDto, new Character());
    }

    @Override
    public Character map(ActorSummaryDto actorSummaryDto, Character character) {
        character.setName(actorSummaryDto.getCharacter());
        return character;
    }
}
