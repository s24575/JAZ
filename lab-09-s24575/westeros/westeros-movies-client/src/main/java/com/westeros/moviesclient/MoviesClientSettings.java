package com.westeros.moviesclient;

import org.springframework.stereotype.Component;

public class MoviesClientSettings implements IMoviesClientSettings {
    private String apiKey;
    private String baseUrl;
    private int apiVersion;

    public MoviesClientSettings(String apiKey, String baseUrl, int apiVersion) {

        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.apiVersion = apiVersion;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    @Override
    public int getApiVersion() {
        return apiVersion;
    }
}
