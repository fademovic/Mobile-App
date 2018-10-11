package com.example.app.anapp.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.example.app.anapp.models.Show;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends ArrayAdapter {
    ArrayList<Movie> movies = new ArrayList<>();
    ArrayList<Show> shows = new ArrayList<>();
    int indeks = 1;
    boolean isShow = false;
    private Context mContext;

    public SearchAdapter(@NonNull Context context, int resource, ArrayList<Movie> moviesList) {
        super(context, resource, moviesList);

        mContext = context;
        movies = moviesList;
    }


    public SearchAdapter(@NonNull Context context, int resource, ArrayList<Show> showsList, boolean isShowSent) {
        super(context, resource, showsList);

        mContext = context;
        shows = showsList;
        isShow = isShowSent;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.custom_list, parent, false);

        ImageView imageNew = (ImageView) listItem.findViewById(R.id.imageCustom);
        final TextView titleNew = (TextView) listItem.findViewById(R.id.titleCustom);

        if (isShow) {
            final Show currentShow = shows.get(position);

            Picasso.get().load(currentShow.getPosterPath()).transform(new PicassoCircleTransformation()).into(imageNew);
            titleNew.setText(currentShow.getName());

            listItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //start DetailsActivity
                    Intent intent = new Intent(mContext, DetailsActivity.class);
                    intent.putExtra("title", currentShow.getName());
                    intent.putExtra("image", currentShow.getPosterPath());
                    intent.putExtra("overview", currentShow.getOverview());
                    mContext.startActivity(intent);
                }
            });

        } else {

            final Movie currentMovie = movies.get(position);

            Picasso.get().load(currentMovie.getPosterPath()).transform(new PicassoCircleTransformation()).into(imageNew);
            titleNew.setText(currentMovie.getTitle());

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
        }

        indeks++;

        return listItem;

    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
