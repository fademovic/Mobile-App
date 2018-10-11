package com.example.app.anapp.activities;

import android.drm.DrmStore;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.app.anapp.R;
import com.example.app.anapp.adapters.MainActivityAdapter;
import com.example.app.anapp.adapters.MovieAdapter;
import com.example.app.anapp.adapters.SearchAdapter;
import com.example.app.anapp.adapters.ShowAdapter;
import com.example.app.anapp.fragments.MoviesFragment;
import com.example.app.anapp.fragments.ShowsFragment;
import com.example.app.anapp.models.Movie;
import com.example.app.anapp.models.Show;
import com.example.app.anapp.services.MovieInterface;
import com.example.app.anapp.services.ServiceBuilder;
import com.example.app.anapp.services.ShowInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    FragmentManager manager = getSupportFragmentManager();
    private Toolbar toolbar;
    private MainActivityAdapter mainActivityAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabItem movies;
    private TabItem shows;
    private final static String API_KEY = "72ff173139c50006a6e36182fa8f8bae";
    ArrayList<Movie> listOfMovies;
    ArrayList<Show> listOfShows;
    int tabClicked = 1;

    public void searchMoviesService(String searchText) {
        if (listOfMovies != null) {
            listOfMovies.clear();
        }

        MovieInterface serviceBuilder =
                ServiceBuilder.getClient().create(MovieInterface.class);

        Call<Movie> call = serviceBuilder.searchMovies(API_KEY, searchText);
        call.enqueue(new Callback<Movie>() {

            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {

                listOfMovies = response.body().getResults();

                ListView containerMovies = findViewById(R.id.searchResults);
                SearchAdapter movieSearchAdapter = new SearchAdapter(MainActivity.this, R.layout.custom_list, listOfMovies);
                containerMovies.setAdapter(movieSearchAdapter);

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(MainActivity.this, "Request Failed", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void searchShowsService(String searchText) {

        if (listOfShows != null) {
            listOfShows.clear();
        }

        ShowInterface serviceBuilder =
                ServiceBuilder.getClient().create(ShowInterface.class);

        Call<Show> call = serviceBuilder.searchShows(API_KEY, searchText);
        call.enqueue(new Callback<Show>() {

            @Override
            public void onResponse(Call<Show> call, Response<Show> response) {
                listOfShows = response.body().getResults();

                ListView containerShows = findViewById(R.id.searchResults);
                SearchAdapter showSearchAdapter = new SearchAdapter(MainActivity.this, R.layout.custom_list, listOfShows, true);
                containerShows.setAdapter(showSearchAdapter);

            }

            @Override
            public void onFailure(Call<Show> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(MainActivity.this, "Request Failed", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void searchToolbar() {

        //setting Toolbar as ActionBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Adding title to action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("An App");

    }

    //Inflating menu item
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);


        //using android.widget.SearchView solved null exception
        final SearchView searchView = (SearchView) searchItem.getActionView();

        //adding navigation icon
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.setNavigationIcon(R.drawable.ic_back_icon);
            }
        });

        //removing navigation icon and collapsing searchView on navigation button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigation icon removed
                toolbar.setNavigationIcon(null);
                //searchView closed
                searchView.onActionViewCollapsed();
                //Showing tabs in tabLayout
                tabLayout = findViewById(R.id.tabLayout);
                tabLayout.setVisibility(View.VISIBLE);
                //Removing searchListView
                ListView searchContainerClear = findViewById(R.id.searchResults);
                searchContainerClear.setAdapter(null);
                searchContainerClear.setVisibility(View.GONE);

            }
        });

        //removing navigation icon and collapsing searchView on close button
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                toolbar.setNavigationIcon(null);
                searchView.onActionViewCollapsed();
                tabLayout = findViewById(R.id.tabLayout);
                tabLayout.setVisibility(View.VISIBLE);
                ListView searchContainerClear = findViewById(R.id.searchResults);
                searchContainerClear.setAdapter(null);
                searchContainerClear.setVisibility(View.GONE);
                return true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() >= 3 && tabClicked == 0) {
                    //Showing searchListView
                    ListView searchContainerClear = findViewById(R.id.searchResults);
                    searchContainerClear.setVisibility(View.VISIBLE);
                    //Hiding tabs in tablayout
                    tabLayout = findViewById(R.id.tabLayout);
                    tabLayout.setVisibility(View.GONE);

                    searchMoviesService(newText);
                }
                if (newText.length() >= 3 && tabClicked == 1) {

                    ListView searchContainerClear = findViewById(R.id.searchResults);
                    searchContainerClear.setVisibility(View.VISIBLE);

                    //Hiding tabs in tablayout
                    tabLayout = findViewById(R.id.tabLayout);
                    tabLayout.setVisibility(View.GONE);

                    searchShowsService(newText);
                }
                if (newText.length() < 3) {
                    ListView searchContainerClear = findViewById(R.id.searchResults);
                    searchContainerClear.setAdapter(null);
                    searchContainerClear.setVisibility(View.GONE);
                    tabLayout = findViewById(R.id.tabLayout);
                    tabLayout.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });

        return true;
    }

    public void tabToolbar() {
        //TABS
        tabLayout = findViewById(R.id.tabLayout);
        movies = findViewById(R.id.moviesTab);
        shows = findViewById(R.id.showsTab);

        mainActivityAdapter = new MainActivityAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        //TAB SWIPE
        //Using MainActivityAdapter as adapter for ViewPager
        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(mainActivityAdapter);
        //syncing tab indicators with swipe
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //Setting default Tab
        tabLayout.setScrollPosition(1, 0f, true);
        viewPager.setCurrentItem(1);

        //TAB CLICK
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    tabLayout.setScrollPosition(0, 0f, true);
                    viewPager.setCurrentItem(0);
                    tabClicked = 0;

                } else if (tab.getPosition() == 1) {
                    tabLayout.setScrollPosition(1, 0f, true);
                    viewPager.setCurrentItem(1);
                    tabClicked = 1;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchToolbar();
        tabToolbar();

        //SearchListView not visible on start
        ListView searchContainerClear = findViewById(R.id.searchResults);
        searchContainerClear.setVisibility(View.GONE);

    }

}
