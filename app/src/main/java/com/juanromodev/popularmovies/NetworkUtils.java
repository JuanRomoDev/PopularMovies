package com.juanromodev.popularmovies;

import android.net.Uri;

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

    private static final String popularPath = "popular";
    private static final String topRatedPath = "top_rated";

    private static final String w92PosterSizePath = "w92";
    private static final String w154PosterSizePath = "w154";
    private static final String w185PosterSizePath = "w185";
    private static final String w342PosterSizePath = "w342";
    private static final String w500PosterSizePath = "w500";
    private static final String w780PosterSizePath = "w780";

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

        return uriToUrl(uri);
    }

    public static URL buildW342PosterSizeImageUrl(String posterImagePath) {
        return buildImageUrl(w342PosterSizePath, posterImagePath);
    }

    private static URL buildImageUrl(String imageSizePath, String imagePath) {
        if (imagePath == null) {
            return null;
        } else if (imagePath.startsWith("/")) {
            imagePath = imagePath.replaceFirst("/", "");
        }

        Uri uri = Uri.parse(IMAGE_BASE_URL).buildUpon()
                .appendPath(imageSizePath)
                .appendPath(imagePath)
                .build();

        return uriToUrl(uri);
    }

    private static URL uriToUrl(Uri uri) {
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

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
