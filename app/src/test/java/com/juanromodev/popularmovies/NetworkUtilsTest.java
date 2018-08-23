package com.juanromodev.popularmovies;

import com.juanromodev.popularmovies.model.MovieSort;
import com.juanromodev.popularmovies.model.PosterSize;
import com.juanromodev.popularmovies.util.NetworkUtils;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class NetworkUtilsTest {

    private static String apiKey;

    @BeforeClass
    public static void setUpClass() {
        apiKey = BuildConfig.TheMovieDb_ApiKey;
    }

    @Test
    public void buildMovieUrl() {
        String popularMoviesUrl = "https://api.themoviedb.org/3/movie/popular?api_key=" + apiKey;
        String url = NetworkUtils.buildMovieUrl(MovieSort.POPULAR).toString();
        assertEquals(popularMoviesUrl, url);
    }

    @Test
    public void buildImageUrl() {
        String w342PosterSizeImageUrl = "https://image.tmdb.org/t/p/w342/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg";
        String url = NetworkUtils.buildImageUri(PosterSize.W342, "/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg").toString();
        assertEquals(w342PosterSizeImageUrl, url);
    }
}