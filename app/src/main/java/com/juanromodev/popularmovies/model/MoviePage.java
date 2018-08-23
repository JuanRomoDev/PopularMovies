package com.juanromodev.popularmovies.model;

public class MoviePage {

    private Movie[] results;

    public MoviePage(Movie[] results) {
        this.results = results;
    }

    public Movie[] getResults() {
        return results;
    }
}
