package com.example.moviestreamingapp.adapter;

import android.content.Context;
import android.media.Image;
import android.text.Layout;
import android.util.Log;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.List;

public class HomeMovieAdapter {

    Context context;
    String TAG = "HomeMovieCheck";

    List<HomeMovies> homeMovies;
    HomeMovies homeMoviesObject;
    FirebaseFirestore firebaseFirestore;


    public HomeMovieAdapter(Context context) {
        this.context = context;
    }

    public FirestoreRecyclerAdapter HomeAdapter(){
        firebaseFirestore = FirebaseFirestore.getInstance();
        Query query = firebaseFirestore.collection("HomeMoives");

        FirestoreRecyclerOptions<HomeMovies> recyclerOptions = new FirestoreRecyclerOptions.Builder<HomeMovies>()
                .setQuery(query,HomeMovies.class)
                .build();

        FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<HomeMovies, HomeMovieViewHolder>(recyclerOptions){
            @NonNull
            @Override
            public HomeMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_movie_row,parent,false);
                return new HomeMovieViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull HomeMovieViewHolder holder, int position, @NonNull HomeMovies model) {
                holder.homeMovieName.setText(model.getMovieName());
                Log.d(TAG, "onBindViewHolder: "+model.getMovieName());
                holder.homeMovieCategory.setText(model.getId().toString());
                Glide.with(context).load(model.getImageUrl()).into(holder.homeMovieItemImage);
            }
        };
        return adapter;
    }


    public class HomeMovieViewHolder extends RecyclerView.ViewHolder{

        ImageView homeMovieItemImage;
        TextView homeMovieName, homeMovieCategory;

        public HomeMovieViewHolder(@NonNull View itemView) {
            super(itemView);

            homeMovieItemImage = itemView.findViewById(R.id.homeMovieItemImage);
            homeMovieCategory = itemView.findViewById(R.id.movieTextCategory);
            homeMovieName = itemView.findViewById(R.id.movieTextName);
        }
    }
}
