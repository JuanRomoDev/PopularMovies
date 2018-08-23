package com.juanromodev.popularmovies.util;

import android.net.Uri;

import com.juanromodev.popularmovies.BuildConfig;
import com.juanromodev.popularmovies.model.MovieSort;
import com.juanromodev.popularmovies.model.PosterSize;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private static final String MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie";
    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p";

    private static final String PARAM_API_KEY = "api_key";
    private static final String API_KEY = BuildConfig.TheMovieDb_ApiKey;

    public static URL buildMovieUrl(MovieSort movieSort) {
        Uri uri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                .appendPath(movieSort.getPath())
                .appendQueryParameter(PARAM_API_KEY, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static Uri buildImageUri(PosterSize posterSize, String imagePath) {
        if (imagePath == null) {
            return null;
        } else if (imagePath.startsWith("/")) {
            imagePath = imagePath.replaceFirst("/", "");
        }

        return Uri.parse(IMAGE_BASE_URL).buildUpon()
                .appendPath(posterSize.getPath())
                .appendPath(imagePath)
                .build();
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
