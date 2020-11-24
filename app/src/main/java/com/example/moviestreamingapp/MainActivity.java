package com.example.moviestreamingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviestreamingapp.adapter.BannerMoviesAdapter;
import com.example.moviestreamingapp.adapter.HomeMovieAdapter;
import com.example.moviestreamingapp.model.BannerMovies;
import com.example.moviestreamingapp.model.HomeMovies;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.tabs.TabLayout;
import com.google.common.io.LineReader;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;

public class MainActivity extends AppCompatActivity {

    String TAG = "HomeMovieCheck";
    Context context;

    List<BannerMovies> homeBannerList;
    List<BannerMovies> movieBannerList;
    List<BannerMovies> tvBannerList;
    List<BannerMovies> kidsBannerList;
    BannerMoviesAdapter bannerMoviesAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;
    private RecyclerView homeMovieRecyclerView;
    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter;

    private HomeMovieAdapter homeMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeMovieRecyclerView = findViewById(R.id.homeMovieRecyclerView);
        firebaseFirestore = FirebaseFirestore.getInstance();

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

        //Trying to implement in class.
//        homeMovieRecyclerView.setHasFixedSize(true);
//        homeMovieRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        homeMovieAdapter = new HomeMovieAdapter(context);
//        homeMovieRecyclerView.setAdapter(homeMovieAdapter.HomeAdapter());
//        homeMovieAdapter.HomeAdapter().startListening();
        HomeMovieAdapter();

    }

    private void HomeMovieAdapter(){
        Query query = firebaseFirestore.collection("HomeMoives");

        FirestoreRecyclerOptions<HomeMovies> recyclerOptions = new FirestoreRecyclerOptions.Builder<HomeMovies>()
                .setQuery(query,HomeMovies.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<HomeMovies, HomeMovieViewHolder>(recyclerOptions){
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
                Glide.with(getApplicationContext()).load(model.getImageUrl()).into(holder.homeMovieItemImage);
            }
        };

        homeMovieRecyclerView.setHasFixedSize(true);
        homeMovieRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        homeMovieRecyclerView.setAdapter(adapter);
    }

    private class HomeMovieViewHolder extends RecyclerView.ViewHolder{

        ImageView homeMovieItemImage;
        TextView homeMovieName, homeMovieCategory;

        public HomeMovieViewHolder(@NonNull View itemView) {
            super(itemView);

            homeMovieItemImage = itemView.findViewById(R.id.homeMovieItemImage);
            homeMovieCategory = itemView.findViewById(R.id.movieTextCategory);
            homeMovieName = itemView.findViewById(R.id.movieTextName);
        }
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

    @Override
    protected void onStart() {
        super.onStart();
//        homeMovieAdapter = new HomeMovieAdapter(context);
//        homeMovieAdapter.HomeAdapter().startListening();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        homeMovieAdapter = new HomeMovieAdapter(context);
//        homeMovieAdapter.HomeAdapter().stopListening();
        adapter.stopListening();
    }
}