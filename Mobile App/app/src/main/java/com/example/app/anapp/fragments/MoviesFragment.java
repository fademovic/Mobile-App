package com.example.app.anapp.fragments;

import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app.anapp.R;
import com.example.app.anapp.adapters.MovieAdapter;
import com.example.app.anapp.models.Movie;
import com.example.app.anapp.services.MovieInterface;
import com.example.app.anapp.services.ServiceBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MoviesFragment extends Fragment {

    private final static String API_KEY = "72ff173139c50006a6e36182fa8f8bae";
    ArrayList<Movie> listOfMovies;

    public void callService() {

        MovieInterface serviceBuilder =
                ServiceBuilder.getClient().create(MovieInterface.class);

        Call<Movie> call = serviceBuilder.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<Movie>() {

            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                listOfMovies = response.body().getResults();

                ListView containerInFragment = (ListView) getView().findViewById(R.id.moviesList);
                MovieAdapter movieAdapter = new MovieAdapter(getActivity(), R.layout.custom_list, listOfMovies);
                containerInFragment.setAdapter(movieAdapter);

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getActivity(), "Request Failed", Toast.LENGTH_LONG).show();

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movies, container, false);


        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        callService();
    }
}
