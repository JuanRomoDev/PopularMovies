package com.juanromodev.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Movie[] movies;

    public MovieAdapter(Movie[] movies) {
        this.movies = movies;
    }

    public void setMovies(Movie[] movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.list_item_movie, parent, false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies[position];
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.length;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private ImageView moviePosterIv;

        public MovieViewHolder(View itemView) {
            super(itemView);
            moviePosterIv = itemView.findViewById(R.id.iv_movie_poster);
        }

        public void bind(Movie movie) {
            Picasso.get()
                    .load(NetworkUtils.buildW342PosterSizeImageUrl(movie.getPosterPath()).toString())
                    .placeholder(R.drawable.poster_w342_placeholder)
                    .into(moviePosterIv);
        }
    }
}
