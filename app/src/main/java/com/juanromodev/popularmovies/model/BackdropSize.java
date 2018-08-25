package com.juanromodev.popularmovies.model;

public enum  BackdropSize {
    W300("w300"), W780("w780"), W1280("w1280");

    private String path;

    BackdropSize(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
