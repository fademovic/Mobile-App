package com.example.app.anapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.app.anapp.fragments.MoviesFragment;
import com.example.app.anapp.fragments.ShowsFragment;

public class MainActivityAdapter extends FragmentPagerAdapter {

    //hold the size of Android Tablayout tabs
    private int numOfTabs;

    public MainActivityAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    //defining Fragments through adapter instead on MainActivity
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MoviesFragment();
            case 1:
                return new ShowsFragment();
            default:
                return null;

        }
    }

    //returning correct number of tabs
    @Override
    public int getCount() {
        return numOfTabs;
    }
}
