package com.example.app.anapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Show {

    public static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185/";

    private ArrayList<Show> results;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("overview")
    private String overview;
    @SerializedName("first_air_date")
    private String firstAirDate;
    @SerializedName("id")
    private Integer id;
    @SerializedName("original_name")
    private String originalName;
    @SerializedName("name")
    private String name;
    @SerializedName("vote_average")
    private Double voteAverage;


    public Show(String posterPath, String overview, String firstAirDate, Integer id, String originalName, String name, Double voteAverage) {
        this.posterPath = posterPath;
        this.overview = overview;
        this.firstAirDate = firstAirDate;
        this.id = id;
        this.originalName = originalName;
        this.name = name;
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {

        return IMAGE_BASE_URL + posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public ArrayList<Show> getResults() {
        return results;
    }

    public void setResults(ArrayList<Show> results) {
        this.results = results;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }
}
