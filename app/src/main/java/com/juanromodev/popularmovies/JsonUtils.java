package com.juanromodev.popularmovies;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {

    public static Movie[] getMoviesFromMoviePageJsonString(String moviePageJsonStr) {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        MoviePage moviePage = gson.fromJson(moviePageJsonStr, MoviePage.class);

        return moviePage.getResults();
    }
}