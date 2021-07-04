package com.example.appp.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appp.R;
import com.example.appp.adapters.CastAdapter;
import com.example.appp.models.Cast;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {
    private ImageView MovieThumbnailImg, MovieCoverImg;
    private TextView tv_title, tv_description;
    private FloatingActionButton play_fab;
    private RecyclerView RvCast;
    private CastAdapter castAdapter;
    private FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        floatingActionButton=findViewById(R.id.play_fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MovieDetailActivity.this,Video.class);
                startActivity(intent);

            }
        });

        // lay du lieu
        iniViews();
        //cai dat danh sách cast
        setupRvCast();


    }

    void iniViews()
    { // lay du lieu

        //play_fab = findViewById(R.id.play_fab);
        RvCast = findViewById(R.id.cass);
        String movieTitle = getIntent().getExtras().getString("title");
        int imageResourceId = getIntent().getExtras().getInt("imgURL");
        int imagecover = getIntent().getExtras().getInt("imgCover");
        MovieThumbnailImg = findViewById(R.id.detail_movie_img);
        Glide.with(this).load(imageResourceId).into(MovieThumbnailImg);
        MovieThumbnailImg.setImageResource(imageResourceId);
        MovieCoverImg = findViewById(R.id.detail_movie_cover);
        Glide.with(this).load(imagecover).into(MovieCoverImg);
        tv_title = findViewById(R.id.detail_movie_title);
        tv_title.setText(movieTitle);
        getSupportActionBar().setTitle(movieTitle);
        tv_description = findViewById(R.id.detail_movie_dec);

        // cai dat animation
       // MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        //play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));

    }
    void setupRvCast()
    {
        List<Cast> mdata = new ArrayList<>();
        mdata.add(new Cast("name",R.drawable.alexanderskarsgard));
        mdata.add(new Cast("name",R.drawable.milliebobbybrown));
        mdata.add(new Cast("name",R.drawable.rebeccahall));
        mdata.add(new Cast("name",R.drawable.shunoguri));
        mdata.add(new Cast("name",R.drawable.henry));

        castAdapter = new CastAdapter(this,mdata);
        RvCast.setAdapter(castAdapter);
        RvCast.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

    }

}