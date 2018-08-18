package com.juanromodev.popularmovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public static Movie[] getMoviesFromJson(String moviesJsonStr)
            throws JSONException {

        String results = "results";

        String id = "id";
        String originalTitle = "original_title";
        String overview = "overview";
        String releaseDate = "release_date";
        String voteAverage = "vote_average";
        String posterPath = "poster_path";

        JSONObject responseJson = new JSONObject(moviesJsonStr);

        JSONArray moviesJsonArray = responseJson.getJSONArray(results);

        Movie[] movies = new Movie[moviesJsonArray.length()];

        for (int i = 0; i < moviesJsonArray.length(); i++) {
            JSONObject movieJson = moviesJsonArray.getJSONObject(i);

            Movie movie = new Movie(movieJson.getInt(id),
                                    movieJson.getString(originalTitle),
                                    movieJson.getString(overview),
                                    movieJson.getString(releaseDate),
                                    movieJson.getDouble(voteAverage),
                                    movieJson.getString(posterPath));

            movies[i] = movie;
        }

        return movies;
    }
}