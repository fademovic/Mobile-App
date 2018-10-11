package com.example.app.anapp.adapters;

import android.content.Intent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app.anapp.R;
import com.example.app.anapp.activities.DetailsActivity;
import com.example.app.anapp.models.Movie;
import com.example.app.anapp.models.PicassoCircleTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter {

    private Context mContext;
    ArrayList<Movie> movies = new ArrayList<>();
    int indeks = 1;


    public MovieAdapter(@NonNull Context context, int resource, ArrayList<Movie> moviesList) {
        super(context, resource, moviesList);

        mContext = context;
        movies = moviesList;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.custom_list, parent, false);


        final Movie currentMovie = movies.get(position);

        ImageView movieImage = (ImageView) listItem.findViewById(R.id.imageCustom);
        Picasso.get().load(currentMovie.getPosterPath()).transform(new PicassoCircleTransformation()).into(movieImage);

        final TextView movieTitle = (TextView) listItem.findViewById(R.id.titleCustom);
        movieTitle.setText(currentMovie.getTitle());

        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start DetailsActivity
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("title", currentMovie.getTitle());
                intent.putExtra("image", currentMovie.getPosterPath());
                intent.putExtra("overview", currentMovie.getOverview());
                mContext.startActivity(intent);
            }
        });

        indeks++;

        return listItem;

    }

    //limits adapter to show only 10 items
    @Override
    public int getCount() {
        if (indeks > 10)
            return 10;
        else
            return super.getCount();
    }

}
