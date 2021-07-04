package com.example.appp.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appp.models.Movie;
import com.example.appp.adapters.MovieAdapter;
import com.example.appp.adapters.MovieItemClickListener;
import com.example.appp.R;
import com.example.appp.models.Slide;
import com.example.appp.adapters.SliderPagerAdapter;
import com.example.appp.utils.DataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements MovieItemClickListener {
    private List<Slide> lstSlides;
    private ViewPager sliderpager;
    private TabLayout indicator;
    private RecyclerView MovieRV,moviesRvWeek;
    



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        iniViews();
        iniSlider();
        iniPopularMovies();
        iniWeekMovies();

    }

    private void iniWeekMovies() {

        MovieAdapter weekMoviesAdapter = new MovieAdapter(this,DataSource.getWeekMovies(),this);
        moviesRvWeek.setAdapter(weekMoviesAdapter);
        moviesRvWeek.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    private void iniPopularMovies() {
        //Recyclerview Setup
        //ini data
        MovieAdapter movieAdapter = new MovieAdapter(this, DataSource.getPopularMovies(),this);
        MovieRV.setAdapter(movieAdapter);
        MovieRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

    }

    private void iniSlider() {
        //setup timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(),4000,6000);


        //prepare a list of slide
        lstSlides = new ArrayList<>();
        lstSlides.add(new Slide(R.drawable.mummy,"Rise Of The Mummy" ));
        lstSlides.add(new Slide(R.drawable.caycau,"21 Cây Cầu" ));
        lstSlides.add(new Slide(R.drawable.anquy,"Ấn Quỷ" ));
        lstSlides.add(new Slide(R.drawable.zombivli,"Zombivli" ));
        lstSlides.add(new Slide(R.drawable.tomandjerry,"Tom And Jerry" ));


        SliderPagerAdapter adapter = new SliderPagerAdapter(this, lstSlides);
        sliderpager.setAdapter(adapter);
        indicator.setupWithViewPager(sliderpager,true);
    }

    private void iniViews() {
        sliderpager = findViewById(R.id.slider_pager);
        indicator = findViewById(R.id.indicator);
        MovieRV = findViewById(R.id.Rv_movies);
        moviesRvWeek = findViewById(R.id.rv_movie_week);
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        // tai day chung ta se gui thong tin to detail activity
        // chung ta tao chuyen dong giua 2 activity

        Intent intent = new Intent(this,MovieDetailActivity.class);
        // gui thong tin movie den Detail

        intent.putExtra("title", movie.getTitle());
        intent.putExtra("imgURL",movie.getThumbnail());
        intent.putExtra("imgCover",movie.getCoverPhoto());


        startActivity(intent);
       // Toast.makeText(this, "item clicked : " + movie.getTitle(), Toast.LENGTH_SHORT).show();

        // tao nhung hoat dong
       // ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,movieImageView,"sharedName");

        //startActivity(intent,options.toBundle());

       // Toast.makeText(this, "item clicked : " + movie.getTitle(), Toast.LENGTH_SHORT).show();

    }


    class SliderTimer extends TimerTask{
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(sliderpager.getCurrentItem()<lstSlides.size()-1)
                    {
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem()+1);
                    }
                    else {
                        sliderpager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}