package com.juanromodev.popularmovies.ui.movies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.juanromodev.popularmovies.R;
import com.juanromodev.popularmovies.model.Movie;
import com.juanromodev.popularmovies.model.PosterSize;
import com.juanromodev.popularmovies.util.NetworkUtils;
import com.squareup.picasso.Picasso;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private OnMovieClickListener movieClickListener;

    private Movie[] movies;

    public interface OnMovieClickListener {
        void onMovieClick(Movie movie);
    }

    public MovieAdapter(OnMovieClickListener movieClickListener) {
        this.movieClickListener = movieClickListener;
    }

    public void setMovies(Movie[] movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder viewHolder, int position) {
        Movie movie = movies[position];
        viewHolder.bindMovie(movie);
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.length;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Movie movie;

        private ImageView moviePosterImageView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            moviePosterImageView = itemView.findViewById(R.id.movie_poster_image_view);
        }

        public void bindMovie(Movie movie) {
            this.movie = movie;

            Picasso.get()
                    .load(NetworkUtils.buildImageUri(PosterSize.W342.getPath(), movie.getPosterPath()))
                    .placeholder(android.R.color.white)
                    .into(moviePosterImageView);
        }

        @Override
        public void onClick(View view) {
            movieClickListener.onMovieClick(movie);
        }
    }
}
