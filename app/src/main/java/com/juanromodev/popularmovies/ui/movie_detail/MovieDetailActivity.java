package com.juanromodev.popularmovies.ui.movie_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.juanromodev.popularmovies.R;
import com.juanromodev.popularmovies.model.BackdropSize;
import com.juanromodev.popularmovies.model.Movie;
import com.juanromodev.popularmovies.model.PosterSize;
import com.juanromodev.popularmovies.util.MovieUtils;
import com.juanromodev.popularmovies.util.NetworkUtils;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String EXTRA_MOVIE = "com.juanromodev.popularmovies.movie";

    public static Intent newIntent(Context packageContext, Movie movie) {
        Intent intent = new Intent(packageContext, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        // Draw a transparent status bar background
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        final CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(movie.getOriginalTitle());

        final ImageView movieBackdropImageView = findViewById(R.id.movie_backdrop_image_view);
        Picasso.get()
                .load(NetworkUtils.buildImageUri(BackdropSize.W780.getPath(), movie.getBackdropPath()))
                .placeholder(android.R.color.white)
                .into(movieBackdropImageView);

        // CollapsingToolbarLayout has problems configuring its height when a non-toolbar ViewGroup is nested within it,
        // so we do it manually here.
        // Issue still occurring as of version 27.1.1
        final View movieHeaderBackground = findViewById(R.id.header_background);
        final ViewGroup headerViewGroup = findViewById(R.id.header_view_group);
        headerViewGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int measuredHeight = movieBackdropImageView.getMeasuredHeight() + movieHeaderBackground.getMeasuredHeight();
                ViewGroup.LayoutParams lp = collapsingToolbar.getLayoutParams();
                lp.height = measuredHeight;
                collapsingToolbar.setLayoutParams(lp);
                movieBackdropImageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        ImageView moviePosterImageView = findViewById(R.id.movie_poster_image_view);
        Picasso.get()
                .load(NetworkUtils.buildImageUri(PosterSize.W342.getPath(), movie.getPosterPath()))
                .placeholder(android.R.color.white)
                .into(moviePosterImageView);

        TextView movieTitleTextView = findViewById(R.id.movie_title_text_view);
        movieTitleTextView.setText(movie.getOriginalTitle());

        TextView movieReleaseDateTextView = findViewById(R.id.movie_release_date_text_view);
        movieReleaseDateTextView.setText(MovieUtils.formatDate(movie.getReleaseDate(), "MMMM d, yyyy"));

        RatingBar movieVoteAverageRatingBar = findViewById(R.id.movie_vote_average_rating_bar);
        // Convert rating scale from 0-10 to 0-5
        movieVoteAverageRatingBar.setRating((float) (movie.getVoteAverage() / 2));

        TextView movieVoteCountTextView = findViewById(R.id.movie_vote_count_text_view);
        String voteCount = getResources().getQuantityString(R.plurals.vote_count, movie.getVoteCount(), movie.getVoteCount());
        movieVoteCountTextView.setText(voteCount);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment movieDetailFragment = fragmentManager.findFragmentById(R.id.movie_overview_fragment_container);

        if (movieDetailFragment == null) {
            movieDetailFragment = MovieOverviewFragment.newInstance(movie);
            fragmentManager.beginTransaction()
                    .add(R.id.movie_overview_fragment_container, movieDetailFragment)
                    .commit();
        }
    }
}
