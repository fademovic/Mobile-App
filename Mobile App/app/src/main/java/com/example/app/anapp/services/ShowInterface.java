package com.example.app.anapp.services;

import com.example.app.anapp.models.Show;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ShowInterface {

    @GET("tv/top_rated")
    Call<Show> getTopRatedShows(@Query("api_key") String apiKey);

    @GET("tv/{id}")
    Call<Show> getShowDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("search/tv")
    Call<Show> searchShows(@Query("api_key") String apiKey, @Query("query") String searchText);

}
