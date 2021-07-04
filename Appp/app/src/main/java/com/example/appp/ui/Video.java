package com.example.appp.ui;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.appp.R;

import java.util.Random;

public class Video extends AppCompatActivity {
    Random random = new Random();
    int i =random.nextInt(4);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        VideoView videoView=findViewById(R.id.video_view);
        String videoPath="";
        if( i==0)
            videoPath= "android.resource://"+getPackageName()+"/"+R.raw.video;
        if( i==1)
            videoPath= "android.resource://"+getPackageName()+"/"+R.raw.video1;
        if( i==2)
            videoPath= "android.resource://"+getPackageName()+"/"+R.raw.video2;
        if( i==3)
            videoPath= "android.resource://"+getPackageName()+"/"+R.raw.video4;
        Uri uri=Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        MediaController mediaController=new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

    }
}