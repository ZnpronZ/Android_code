package com.example.appp.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.appp.R;
import com.example.appp.login.LoginMain;
import com.example.appp.mainmusic.MainMusicActivity;

public class Home extends AppCompatActivity {
    private Button btnHome , btnMusic,btnMovie,btnLogin;
    private FloatingActionButton flMusic,flMovie, flLogin;
    ImageView imgOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //btnHome = findViewById(R.id.btnhome);
        //flMovie = findViewById(R.id.fl_movie);
        //flMusic = findViewById(R.id.fl_music);
        //flLogin = findViewById(R.id.fl_login);
        //btnLogin = findViewById(R.id.btn_login);
        btnMovie = findViewById(R.id.btn_movie);
        btnMusic = findViewById(R.id.btn_music);
        imgOption = findViewById(R.id.imageViewOption);

        btnMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, MainMusicActivity.class);
                startActivity(intent);
            }
        });
        imgOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, LoginMain.class);
                startActivity(intent);
            }
        });
    }

}