package com.example.app.anapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app.anapp.R;
import com.example.app.anapp.activities.DetailsActivity;
import com.example.app.anapp.models.PicassoCircleTransformation;
import com.example.app.anapp.models.Show;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import java.util.ArrayList;

public class ShowAdapter extends ArrayAdapter {

    private Context mContext;
    ArrayList<Show> shows = new ArrayList<>();
    int indeks = 1;


    public ShowAdapter(@NonNull Context context, int resource, ArrayList<Show> showsList) {
        super(context, resource, showsList);

        mContext = context;
        shows = showsList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;

        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.custom_list, parent, false);

        final Show currentShow = shows.get(position);

        ImageView showImage = (ImageView) listItem.findViewById(R.id.imageCustom);
        Picasso.get().load(currentShow.getPosterPath()).transform(new PicassoCircleTransformation()).into(showImage);

        TextView showTitle = (TextView) listItem.findViewById(R.id.titleCustom);
        showTitle.setText(currentShow.getName());

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
