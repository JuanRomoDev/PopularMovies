package com.juanromodev.popularmovies;

import java.net.URL;

public enum SortMoviesBy {
    POPULAR {
        @Override
        public URL getUrl() {
            return NetworkUtils.buildPopularMoviesUrl();
        }
    },
    TOP_RATED {
        @Override
        public URL getUrl() {
            return NetworkUtils.buildTopRatedMoviesUrl();
        }
    };

    public abstract URL getUrl();
}
