package com.juanromodev.popularmovies;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class NetworkUtilsTest {

    private static String popularMoviesUrl;
    private static String topRatedMoviesUrl;

    @BeforeClass
    public static void setUpClass() {
        String apiKey = BuildConfig.TheMovieDb_ApiKey;

        popularMoviesUrl = "https://api.themoviedb.org/3/movie/popular?api_key=" + apiKey;
        topRatedMoviesUrl = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + apiKey;
    }

    @Test
    public void buildCorrectPopularMoviesUrl() {
        String url = NetworkUtils.buildPopularMoviesUrl().toString();
        assertEquals(popularMoviesUrl, url);
    }

    @Test
    public void buildCorrectTopRatedMoviesUrl() {
        String url = NetworkUtils.buildTopRatedMoviesUrl().toString();
        assertEquals(topRatedMoviesUrl, url);
    }
}