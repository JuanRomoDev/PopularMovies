package com.juanromodev.popularmovies;

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

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class MoviesFragment extends Fragment {

    private static final String ARG_SORT_MOVIES_BY = "sortMoviesBy";

    private RecyclerView moviesRecyclerView;
    private MovieAdapter movieAdapter;

    public static Fragment newInstance(SortMoviesBy sortMoviesBy) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SORT_MOVIES_BY, sortMoviesBy);

        Fragment fragment = new MoviesFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        moviesRecyclerView = view.findViewById(R.id.movies_recycler_view);

        int numberOfColumns = getActivity().getResources().getInteger(R.integer.number_of_columns);
        LayoutManager layoutManager = new GridLayoutManager(getActivity(), numberOfColumns);
        moviesRecyclerView.setLayoutManager(layoutManager);

        moviesRecyclerView.setHasFixedSize(true);

        SortMoviesBy sortMoviesBy = (SortMoviesBy) getArguments().getSerializable(ARG_SORT_MOVIES_BY);
        new FetchMovieTask().execute(sortMoviesBy.getUrl());

        return view;
    }

    public class FetchMovieTask extends AsyncTask<URL, Void, Movie[]> {

        @Override
        protected Movie[] doInBackground(URL... urls) {
            URL url = urls[0];

            Movie[] movies = null;
            try {
                String movieJsonResponse = NetworkUtils.getResponseFromHttpUrl(url);

                movies = JsonUtils.getMoviesFromJson(movieJsonResponse);

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return movies;
        }

        @Override
        protected void onPostExecute(Movie[] movies) {
            if (movies != null) {

                if (movieAdapter == null) {
                    movieAdapter = new MovieAdapter(movies);
                    moviesRecyclerView.setAdapter(movieAdapter);
                } else {
                    movieAdapter.setMovies(movies);
                }
            }
        }
    }
}
