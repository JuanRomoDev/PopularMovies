package com.juanromodev.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Movie implements Parcelable {

    private int id;
    private String originalTitle;
    private String overview;
    private String releaseDate;
    private double voteAverage;
    private int voteCount;
    private String posterPath;
    private String backdropPath;

    public Movie(int id, String originalTitle, String overview, String releaseDate,
                 double voteAverage, int voteCount, String posterPath, String backdropPath) {
        this.id = id;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
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

    public int getVoteCount() {
        return voteCount;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
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
               voteCount == otherMovie.voteCount &&
               Objects.equals(posterPath, otherMovie.posterPath) &&
               Objects.equals(backdropPath, otherMovie.backdropPath);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id, originalTitle, overview, releaseDate,
                            voteAverage, voteCount, posterPath, backdropPath);
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
        out.writeInt(voteCount);
        out.writeString(posterPath);
        out.writeString(backdropPath);
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
        voteCount = in.readInt();
        posterPath = in.readString();
        backdropPath = in.readString();
    }
}
