package com.juanromodev.popularmovies.ui.movies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juanromodev.popularmovies.R;
import com.juanromodev.popularmovies.model.Movie;
import com.juanromodev.popularmovies.model.MovieSort;
import com.juanromodev.popularmovies.ui.movie_detail.MovieDetailActivity;
import com.juanromodev.popularmovies.util.JsonUtils;
import com.juanromodev.popularmovies.util.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MoviesFragment extends Fragment
        implements MovieAdapter.OnMovieClickListener {

    private static final String ARG_MOVIE_SORT = "movieSort";

    private RecyclerView movieRecyclerView;
    private MovieAdapter movieAdapter;

    public static Fragment newInstance(MovieSort movieSort) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_MOVIE_SORT, movieSort);

        Fragment fragment = new MoviesFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        movieRecyclerView = view.findViewById(R.id.movie_recycler_view);

        int numberOfColumns = getActivity().getResources()
                .getInteger(R.integer.number_of_columns);
        LayoutManager layoutManager = new GridLayoutManager(getActivity(), numberOfColumns);
        movieRecyclerView.setLayoutManager(layoutManager);

        movieRecyclerView.setHasFixedSize(true);

        movieAdapter = new MovieAdapter(this);
        movieRecyclerView.setAdapter(movieAdapter);

        MovieSort movieSort = (MovieSort) getArguments().getSerializable(ARG_MOVIE_SORT);
        new FetchMovieTask().execute(NetworkUtils.buildMovieUrl(movieSort));

        return view;
    }

    @Override
    public void onMovieClick(Movie movie) {
        Intent intent = MovieDetailActivity.newIntent(getActivity(), movie);
        startActivity(intent);
    }

    public class FetchMovieTask extends AsyncTask<URL, Void, Movie[]> {

        @Override
        protected Movie[] doInBackground(URL... urls) {
            URL url = urls[0];

            Movie[] movies = null;
            try {
                String moviePageJsonResponse = NetworkUtils.getResponseFromHttpUrl(url);

                movies = JsonUtils.getMoviesFromMoviePageJson(moviePageJsonResponse);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return movies;
        }

        @Override
        protected void onPostExecute(Movie[] movies) {
            if (movies != null) {
                movieAdapter.setMovies(movies);
            }
        }
    }
}
