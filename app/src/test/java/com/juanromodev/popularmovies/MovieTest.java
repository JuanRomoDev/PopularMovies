package com.juanromodev.popularmovies;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import nl.jqno.equalsverifier.EqualsVerifier;

@RunWith(RobolectricTestRunner.class)
public class MovieTest {

    @Test
    public void equalsVerifier() {
        EqualsVerifier.forClass(Movie.class)
                .verify();
    }
}
