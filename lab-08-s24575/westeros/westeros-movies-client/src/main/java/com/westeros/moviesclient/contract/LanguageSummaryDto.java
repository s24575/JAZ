package com.westeros.moviesclient.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LanguageSummaryDto {

    @JsonProperty("english_name")
    private String englishName;
    private String name;
    @JsonProperty("iso_code")
    private String isoCode;

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }
}
