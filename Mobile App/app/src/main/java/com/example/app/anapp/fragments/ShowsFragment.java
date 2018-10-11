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
import com.example.app.anapp.adapters.ShowAdapter;
import com.example.app.anapp.models.Show;
import com.example.app.anapp.services.ServiceBuilder;
import com.example.app.anapp.services.ShowInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShowsFragment extends Fragment {

    private final static String API_KEY = "72ff173139c50006a6e36182fa8f8bae";
    ArrayList<Show> listOfShows;

    public void callService() {

        ShowInterface serviceBuilder =
                ServiceBuilder.getClient().create(ShowInterface.class);

        Call<Show> call = serviceBuilder.getTopRatedShows(API_KEY);
        call.enqueue(new Callback<Show>() {

            @Override
            public void onResponse(Call<Show> call, Response<Show> response) {
                listOfShows = response.body().getResults();

                ListView containerInFragment = (ListView) getView().findViewById(R.id.showsList);
                ShowAdapter movieAdapter = new ShowAdapter(getActivity(), R.layout.custom_list, listOfShows);
                containerInFragment.setAdapter(movieAdapter);

            }

            @Override
            public void onFailure(Call<Show> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getActivity(), "Request Failed", Toast.LENGTH_LONG).show();

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shows, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        callService();
    }

}
