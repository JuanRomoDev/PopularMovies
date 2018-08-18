package com.juanromodev.popularmovies;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie";

    private static final String PARAM_API_KEY = "api_key";
    private static final String API_KEY = BuildConfig.TheMovieDb_ApiKey;

    private static final String popularPath = "popular";
    private static final String topRatedPath = "top_rated";

    public static URL buildPopularMoviesUrl() {
        return buildMovieUrl(popularPath);
    }

    public static URL buildTopRatedMoviesUrl() {
        return buildMovieUrl(topRatedPath);
    }

    private static URL buildMovieUrl(String sortByPath) {
        Uri uri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                .appendPath(sortByPath)
                .appendQueryParameter(PARAM_API_KEY, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "Built URL: " + url);

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
