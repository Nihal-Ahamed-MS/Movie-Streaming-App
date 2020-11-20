package com.example.moviestreamingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.moviestreamingapp.adapter.BannerMoviesAdapter;
import com.example.moviestreamingapp.model.BannerMovies;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    List<BannerMovies> homeBannerList;
    List<BannerMovies> movieBannerList;
    List<BannerMovies> tvBannerList;
    List<BannerMovies> kidsBannerList;
    BannerMoviesAdapter bannerMoviesAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeBannerList = new ArrayList<>();
        homeBannerList.add(new BannerMovies(1,"Master","https://picsum.photos/250?image=9",""));
        homeBannerList.add(new BannerMovies(2,"Master","https://image.shutterstock.com/image-illustration/blockchain-technology-futuristic-hud-background-260nw-767232946.jpg",""));
        homeBannerList.add(new BannerMovies(3,"Master","https://image.shutterstock.com/image-vector/white-global-communication-banner-colorful-600w-1131019226.jpg",""));

        tvBannerList = new ArrayList<>();
        tvBannerList.add(new BannerMovies(1,"Master","https://picsum.photos/250?image=9",""));
        tvBannerList.add(new BannerMovies(2,"Master","https://image.shutterstock.com/image-illustration/blockchain-technology-futuristic-hud-background-260nw-767232946.jpg",""));
        tvBannerList.add(new BannerMovies(3,"Master","https://image.shutterstock.com/image-vector/white-global-communication-banner-colorful-600w-1131019226.jpg",""));

        movieBannerList = new ArrayList<>();
        movieBannerList.add(new BannerMovies(1,"Master","https://picsum.photos/250?image=9",""));
        movieBannerList.add(new BannerMovies(2,"Master","https://image.shutterstock.com/image-illustration/blockchain-technology-futuristic-hud-background-260nw-767232946.jpg",""));
        movieBannerList.add(new BannerMovies(3,"Master","https://image.shutterstock.com/image-vector/white-global-communication-banner-colorful-600w-1131019226.jpg",""));

        kidsBannerList = new ArrayList<>();
        kidsBannerList.add(new BannerMovies(1,"Master","https://picsum.photos/250?image=9",""));
        kidsBannerList.add(new BannerMovies(2,"Master","https://image.shutterstock.com/image-illustration/blockchain-technology-futuristic-hud-background-260nw-767232946.jpg",""));
        kidsBannerList.add(new BannerMovies(3,"Master","https://image.shutterstock.com/image-vector/white-global-communication-banner-colorful-600w-1131019226.jpg",""));


        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 1:
                        setAdapterForBannerList(homeBannerList);
                        return;
                    case 2:
                        setAdapterForBannerList(movieBannerList);
                        return;
                    case 3:
                        setAdapterForBannerList(tvBannerList);
                        return;
                    case 4:
                        setAdapterForBannerList(kidsBannerList);
                        return;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        
    }
    
    private void setAdapterForBannerList(List<BannerMovies> bannerMoviesList){
        viewPager = findViewById(R.id.bannerViewPager);
        bannerMoviesAdapter = new BannerMoviesAdapter(this,bannerMoviesList);
        viewPager.setAdapter(bannerMoviesAdapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new AutoSlider(bannerMoviesList),4000,6000);

    }

    private class AutoSlider extends TimerTask{
        private List<BannerMovies> currentBannerMovieList;

        public AutoSlider(List<BannerMovies> currentBannerMovieList) {
            this.currentBannerMovieList = currentBannerMovieList;
        }

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem()<currentBannerMovieList.size()-1){
                        viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                    }
                    else{
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}