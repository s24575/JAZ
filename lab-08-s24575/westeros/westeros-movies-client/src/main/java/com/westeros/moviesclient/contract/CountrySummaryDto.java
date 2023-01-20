package com.westeros.moviesclient.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountrySummaryDto {

    String name;
    @JsonProperty("iso_3166_1")
    String isoCode;

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
