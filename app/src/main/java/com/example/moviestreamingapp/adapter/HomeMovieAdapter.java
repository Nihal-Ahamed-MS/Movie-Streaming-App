package com.example.moviestreamingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviestreamingapp.R;
import com.example.moviestreamingapp.model.HomeMovies;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class HomeMovieAdapter extends FirestoreRecyclerAdapter<HomeMovies, HomeMovieAdapter.HomeMovieViewHolder>{


    private Context context;

    private OnHomeMovieClick onHomeMovieClick;

    public HomeMovieAdapter(@NonNull FirestoreRecyclerOptions<HomeMovies> options, Context context,OnHomeMovieClick onHomeMovieClick) {
        super(options);
        this.context = context;
        this.onHomeMovieClick = onHomeMovieClick;
    }

    @Override
    protected void onBindViewHolder(@NonNull HomeMovieViewHolder holder, int position, @NonNull HomeMovies model) {
        holder.homeMovieName.setText(model.getMovieName());

        Glide.with(context).load(model.getImageUrl()).into(holder.homeMovieItemImage);
    }

    @NonNull
    @Override
    public HomeMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_movie_row,parent,false);
        return new HomeMovieViewHolder(view);
    }

    public class HomeMovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView homeMovieItemImage;
        TextView homeMovieName, homeMovieCategory;

        public HomeMovieViewHolder(@NonNull View itemView) {
            super(itemView);

            homeMovieItemImage = itemView.findViewById(R.id.homeMovieItemImage);
            homeMovieCategory = itemView.findViewById(R.id.movieTextCategory);
            homeMovieName = itemView.findViewById(R.id.movieTextName);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onHomeMovieClick.MovieClick(getItem(getAdapterPosition()),getAdapterPosition());
        }
    }

    public interface OnHomeMovieClick{
        void MovieClick(HomeMovies snapshot, int positionOfCurrentMovie);
    }
}