package com.juanromodev.popularmovies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager movieViewPager = findViewById(R.id.movie_view_pager);
        PagerAdapter moviePagerAdapter =
                new MoviePagerAdapter(getSupportFragmentManager());
        movieViewPager.setAdapter(moviePagerAdapter);

        TabLayout tabs = findViewById(R.id.movie_tabs);
        tabs.setupWithViewPager(movieViewPager);
    }

    public class MoviePagerAdapter extends FragmentPagerAdapter {

        public MoviePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return MoviesFragment.newInstance(SortMoviesBy.POPULAR);
                case 1:
                    return MoviesFragment.newInstance(SortMoviesBy.TOP_RATED);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.popular_tab);
                case 1:
                    return getString(R.string.top_rated_tab);
                default:
                    return null;
            }
        }
    }
}
