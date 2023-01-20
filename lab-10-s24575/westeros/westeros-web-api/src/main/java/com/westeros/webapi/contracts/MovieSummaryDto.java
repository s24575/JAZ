package com.westeros.webapi.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieSummaryDto {
    @JsonProperty(value = "id")
    private int id;
    @JsonProperty(value = "title")
    private String originalTitle;
    @JsonProperty(value = "homepage")
    private String homepage;
    @JsonProperty(value = "language")
    private String originalLanguage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }
}
