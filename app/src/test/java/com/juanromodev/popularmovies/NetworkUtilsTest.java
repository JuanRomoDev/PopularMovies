package com.juanromodev.popularmovies;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class NetworkUtilsTest {

    private static String apiKey;

    @BeforeClass
    public static void setUpClass() {
        apiKey = BuildConfig.TheMovieDb_ApiKey;
    }

    @Test
    public void buildPopularMoviesUrl() {
        String popularMoviesUrl = "https://api.themoviedb.org/3/movie/popular?api_key=" + apiKey;
        String url = NetworkUtils.buildPopularMoviesUrl().toString();
        assertEquals(popularMoviesUrl, url);
    }

    @Test
    public void buildTopRatedMoviesUrl() {
        String topRatedMoviesUrl = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + apiKey;
        String url = NetworkUtils.buildTopRatedMoviesUrl().toString();
        assertEquals(topRatedMoviesUrl, url);
    }

    @Test
    public void buildW342PosterSizeImageUrl() {
        String w342PosterSizeImageUrl = "https://image.tmdb.org/t/p/w342/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg";
        String url = NetworkUtils.buildW342PosterSizeImageUrl("/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg").toString();
        assertEquals(w342PosterSizeImageUrl, url);
    }
}