package com.westeros.moviesclient;

import com.westeros.moviesclient.contract.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

public class MoviesClient implements IMoviesClient {

    RestTemplate restClient;
    String baseUrl;
    String apiKey;
    int version;

    public MoviesClient(IMoviesClientSettings settings) {
        restClient = new RestTemplate();
        this.baseUrl=settings.getBaseUrl();
        this.apiKey= settings.getApiKey();
        this.version= settings.getApiVersion();
    }

    @Override
    public PagedResultDto getByDateRange(LocalDate from, LocalDate to) {
        String url = baseUrl+"/"+version+"/discover/movie?primary_release_date.gte="+from.toString()+"&primary_release_date.lte="+to.toString()+"&api_key="+apiKey;
        var response = restClient.getForEntity(url, PagedResultDto.class).getBody();
        return response;
    }

    @Override
    public PagedResultDto getByDateRange(LocalDate from, LocalDate to, int page) {
        String url = baseUrl+"/"+version+"/discover/movie?primary_release_date.gte="+from.toString()+"&primary_release_date.lte="+to.toString()+"&api_key="+apiKey+"&page="+page;
        var response = restClient.getForEntity(url, PagedResultDto.class).getBody();
        return response;
    }

    @Override
    public MovieDto getMovie(int id) {
        String url = baseUrl+"/"+version+"/movie/"+id+"?api_key="+apiKey;
        var response = restClient.getForEntity(url, MovieDto.class).getBody();
        return response;
    }

    @Override
    public CreditsDto getCredits(int id) {
        String url = baseUrl+"/"+version+"/movie/"+id+"/credits?api_key="+apiKey;
        var response = restClient.getForEntity(url, CreditsDto.class).getBody();
        return response;
    }

    @Override
    public ActorDto getActorDetails(int id) {
        String url = baseUrl+"/"+version+"/person/"+id+"?api_key="+apiKey;
        var response = restClient.getForEntity(url, ActorDto.class).getBody();
        return response;
    }
}
