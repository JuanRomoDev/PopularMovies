package com.juanromodev.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Movie implements Parcelable {

    private final int id;
    private final String originalTitle;
    private final String overview;
    private final String releaseDate;
    private final double voteAverage;
    private final String posterPath;

    public Movie(int id, String originalTitle, String overview, String releaseDate,
                 double voteAverage, String posterPath) {
        this.id = id;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Movie)) {
            return false;
        }

        Movie otherMovie = (Movie) obj;

        return id == otherMovie.id &&
               Objects.equals(originalTitle, otherMovie.originalTitle) &&
               Objects.equals(overview, otherMovie.overview) &&
               Objects.equals(releaseDate, otherMovie.releaseDate) &&
               Double.compare(voteAverage, otherMovie.voteAverage) == 0 &&
               Objects.equals(posterPath, otherMovie.posterPath);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id, originalTitle, overview, releaseDate, voteAverage, posterPath);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(originalTitle);
        out.writeString(overview);
        out.writeString(releaseDate);
        out.writeDouble(voteAverage);
        out.writeString(posterPath);
    }

    public static final Parcelable.Creator<Movie> CREATOR
            = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    private Movie(Parcel in) {
        id = in.readInt();
        originalTitle = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
        voteAverage = in.readDouble();
        posterPath = in.readString();
    }
}
