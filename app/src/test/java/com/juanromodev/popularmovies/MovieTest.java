package com.juanromodev.popularmovies;

import android.os.Parcel;

import com.juanromodev.popularmovies.model.Movie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class MovieTest {

    @Test
    public void equalsVerifier() {
        EqualsVerifier.forClass(Movie.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }

    @Test
    public void movieIsParcelable() {
        Movie movie = new Movie(0, "", "", "", 0.0, "");

        Parcel parcel = Parcel.obtain();

        movie.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);
        Movie retrievedMovie = Movie.CREATOR.createFromParcel(parcel);

        assertEquals(movie, retrievedMovie);
    }
}