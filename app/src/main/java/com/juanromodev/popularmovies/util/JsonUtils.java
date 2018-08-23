package com.juanromodev.popularmovies.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.juanromodev.popularmovies.model.Movie;
import com.juanromodev.popularmovies.model.MoviePage;

public class JsonUtils {

    public static Movie[] getMoviesFromMoviePageJson(String moviePageJson) {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        MoviePage moviePage = gson.fromJson(moviePageJson, MoviePage.class);

        return moviePage.getResults();
    }
}