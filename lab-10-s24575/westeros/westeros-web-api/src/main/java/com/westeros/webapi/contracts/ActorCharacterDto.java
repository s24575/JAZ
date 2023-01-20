package com.westeros.webapi.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActorCharacterDto {
    @JsonProperty(value = "characterName")
    private String characterName;
    @JsonProperty(value = "actorName")
    private String actorName;

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }
}
