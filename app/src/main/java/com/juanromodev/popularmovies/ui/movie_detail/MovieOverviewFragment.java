package com.juanromodev.popularmovies.ui.movie_detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.juanromodev.popularmovies.R;
import com.juanromodev.popularmovies.model.Movie;

public class MovieOverviewFragment extends Fragment {

    private static final String ARG_MOVIE = "movie";

    private Movie movie;

    public static Fragment newInstance(Movie movie) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_MOVIE, movie);

        Fragment fragment = new MovieOverviewFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movie = getArguments().getParcelable(ARG_MOVIE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_overview, container, false);

        TextView movieOverviewTextView = view.findViewById(R.id.movie_overview_text_view);
        movieOverviewTextView.setText(movie.getOverview());

        return view;
    }
}
