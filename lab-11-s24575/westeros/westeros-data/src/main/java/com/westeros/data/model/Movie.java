package com.westeros.data.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long sourceId;
    private boolean adult;
    private String backdropPath;
    private int budget;
    private String homepage;
    private String originalLanguage;
    private String originalTitle;
    //@Column(columnDefinition = "text")
    private String overview;
    private double popularity;
    private String posterPath;
    private LocalDate releaseDate;
    private int runtime;
    private int voteCount;
    private double voteAverage;

    @ManyToMany(mappedBy = "movies", cascade = CascadeType.ALL)
    private List<Company> productionCompanies = new ArrayList<>();
    @ManyToMany(mappedBy = "movies", cascade = CascadeType.ALL)
    private List<Country> productionCountries = new ArrayList<>();

    @ManyToMany(mappedBy = "movies", cascade = CascadeType.ALL)
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany(mappedBy = "movies", cascade = CascadeType.ALL)
    private List<SpokenLanguage> spokenSpokenLanguages = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<CharacterRole> characterRoles = new ArrayList<>();

    public long getSourceId() {
        return sourceId;
    }

    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
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

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
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

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public List<Company> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<Company> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<Country> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<Country> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return spokenSpokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguage> spokenSpokenLanguages) {
        this.spokenSpokenLanguages = spokenSpokenLanguages;
    }

    public List<CharacterRole> getCharacters() {
        return characterRoles;
    }

    public void setCharacters(List<CharacterRole> characterRole) {
        this.characterRoles = characterRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return sourceId == movie.sourceId && adult == movie.adult && budget == movie.budget && Double.compare(movie.popularity, popularity) == 0 && runtime == movie.runtime && voteCount == movie.voteCount && Double.compare(movie.voteAverage, voteAverage) == 0 && Objects.equals(id, movie.id) && Objects.equals(backdropPath, movie.backdropPath) && Objects.equals(homepage, movie.homepage) && Objects.equals(originalLanguage, movie.originalLanguage) && Objects.equals(originalTitle, movie.originalTitle) && Objects.equals(overview, movie.overview) && Objects.equals(posterPath, movie.posterPath) && Objects.equals(releaseDate, movie.releaseDate) && Objects.equals(productionCompanies, movie.productionCompanies) && Objects.equals(productionCountries, movie.productionCountries) && Objects.equals(genres, movie.genres) && Objects.equals(spokenSpokenLanguages, movie.spokenSpokenLanguages) && Objects.equals(characterRoles, movie.characterRoles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sourceId, adult, backdropPath, budget, homepage, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, runtime, voteCount, voteAverage, productionCompanies, productionCountries, genres, spokenSpokenLanguages, characterRoles);
    }
}
