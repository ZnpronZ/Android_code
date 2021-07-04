package com.example.actioncall;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
     EditText etPhoneNumber,etMessage;
     Button btnCall,btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etPhoneNumber=findViewById(R.id.etPhoneNum);
        btnCall = findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ etPhoneNumber));
                try {
                    startActivity(intent);
                }catch (ActivityNotFoundException ex)
                {

                }
            }
        });
        etMessage = findViewById(R.id.etMessage);
        btnSend= findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+ etPhoneNumber.getText()));
                intent.putExtra("sms_body",etMessage.getText().toString());
                startActivity(intent);
            }
        });
    }
}