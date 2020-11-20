package com.example.moviestreamingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;

import com.example.moviestreamingapp.adapter.BannerMoviesAdapter;
import com.example.moviestreamingapp.model.BannerMovies;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<BannerMovies> bannerMoviesList;
    BannerMoviesAdapter bannerMoviesAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bannerMoviesList = new ArrayList<>();
        bannerMoviesList.add(new BannerMovies(1,"Master","https://picsum.photos/250?image=9",""));
        bannerMoviesList.add(new BannerMovies(2,"Master","https://image.shutterstock.com/image-illustration/blockchain-technology-futuristic-hud-background-260nw-767232946.jpg",""));
        bannerMoviesList.add(new BannerMovies(3,"Master","https://image.shutterstock.com/image-vector/white-global-communication-banner-colorful-600w-1131019226.jpg",""));




        viewPager = findViewById(R.id.bannerViewPager);
        bannerMoviesAdapter = new BannerMoviesAdapter(this,bannerMoviesList);
        viewPager.setAdapter(bannerMoviesAdapter);

    }
}