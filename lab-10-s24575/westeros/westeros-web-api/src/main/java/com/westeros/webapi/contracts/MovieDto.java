package com.westeros.webapi.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class MovieDto extends MovieSummaryDto {
    @JsonProperty(value = "adult")
    private boolean adult;
    @JsonProperty(value = "budget")
    private int budget;
    @JsonProperty(value = "overview")
    private String overview;
    @JsonProperty(value = "release_date")
    private LocalDate releaseDate;
    @JsonProperty(value = "runtime")
    private int runtime;

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }
}
