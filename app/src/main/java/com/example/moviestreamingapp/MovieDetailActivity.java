package com.example.moviestreamingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView detailMovieName;
    private Button detailMovieButton;
    private ImageView detailMovieImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        setTitle("Details");
        String movieName = getIntent().getStringExtra("movieName");
        String imageName = getIntent().getStringExtra("imageUrl");
        String fileUrl = getIntent().getStringExtra("fileUrl");
        Log.d("fragmentString", "onCreate: "+movieName+imageName+fileUrl);

        detailMovieButton = findViewById(R.id.detailMovieButton);
        detailMovieName = findViewById(R.id.detailMoviename);
        detailMovieImage = findViewById(R.id.detailMovieImage);

        Glide.with(this).load(imageName).into(detailMovieImage);
        detailMovieName.setText(movieName);

        detailMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieDetailActivity.this,VideoPlayer.class);
                intent.putExtra("url",fileUrl);
                startActivity(intent);
            }
        });

    }
}