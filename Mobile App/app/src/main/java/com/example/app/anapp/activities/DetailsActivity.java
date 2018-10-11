package com.example.app.anapp.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app.anapp.R;
import com.example.app.anapp.models.PicassoCircleTransformation;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //setting Toolbar as ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.detailedScreenToolbar);
        setSupportActionBar(toolbar);

        //Adding navigation to action bar
        toolbar.setNavigationIcon(R.drawable.ic_back_icon);
        //Title empty
        getSupportActionBar().setTitle("");

        //navigation click
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsActivity.this.finish();
            }
        });


        TextView title = findViewById(R.id.title);
        TextView overview = findViewById(R.id.overview);
        ImageView image = findViewById(R.id.image);

        //get data from MainActivity when item of listview is clicked using intent
        Intent intent = getIntent();
        String itemTitle = intent.getStringExtra("title");
        String itemOverview = intent.getStringExtra("overview");
        String itemImage = intent.getStringExtra("image");

        //set text in textview
        title.setText(itemTitle);
        overview.setText(itemOverview);
        Picasso.get().load(itemImage).fit().into(image);


    }
}
