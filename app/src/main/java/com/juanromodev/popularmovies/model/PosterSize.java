package com.juanromodev.popularmovies.model;

public enum PosterSize {
    W92("w92"), W154("w154"), W185("w185"), W342("w342"), W500("500"), W780("780");

    private String path;

    PosterSize(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}