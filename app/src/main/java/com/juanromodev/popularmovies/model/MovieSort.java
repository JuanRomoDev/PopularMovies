package com.juanromodev.popularmovies.model;

public enum MovieSort {
    POPULAR("popular"), TOP_RATED("top_rated");

    private String path;

    MovieSort(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
