package com.example.apploading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_download;
    ProgressBar load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_download = (Button)findViewById(R.id.button);
        load=(ProgressBar)findViewById(R.id.progressBar);

        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountDownTimer countDownTimer = new CountDownTimer(10000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int current=load.getProgress();
                        if(current>=load.getMax())
                        {
                            current=0;
                        }
                        load.setProgress(current+10);
                    }

                    @Override
                    public void onFinish() {
                        this.start();//run again

                        Toast.makeText(MainActivity.this,"Time Out",Toast.LENGTH_LONG).show();
                    }
                };
                countDownTimer.start();
            }
        });
    }
}