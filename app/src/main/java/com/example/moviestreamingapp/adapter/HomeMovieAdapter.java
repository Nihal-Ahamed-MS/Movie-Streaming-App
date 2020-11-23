package com.example.moviestreamingapp.adapter;

import android.content.Context;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviestreamingapp.R;
import com.example.moviestreamingapp.model.HomeMovies;

import java.util.List;

public class HomeMovieAdapter extends RecyclerView.Adapter<HomeMovieAdapter.HomeViewHolder> {

    Context context;
    List<HomeMovies> homeMovies;

    public HomeMovieAdapter(Context context, List<HomeMovies> homeMovies) {
        this.context = context;
        this.homeMovies = homeMovies;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeViewHolder(LayoutInflater.from(context).inflate(R.layout.home_movie_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return homeMovies.size();
    }


    public static class HomeViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.homeMovieItemImage);
        }
    }
}
