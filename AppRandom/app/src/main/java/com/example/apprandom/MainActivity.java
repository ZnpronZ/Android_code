 package com.example.apprandom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

 public class MainActivity extends AppCompatActivity {
     TextView txtRandom;
     Button btnRandom;
     EditText edtNum1,edtNum2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     AnhXa();
     btnRandom.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String str1= edtNum1.getText().toString().trim();
             String str2= edtNum2.getText().toString().trim();
             if(str1.length()==0 || str2.length()==0)
                 Toast.makeText(MainActivity.this,"Vui lòng nhập đủ số",Toast.LENGTH_LONG).show();
             else {
                 int min = Integer.parseInt(str1);
                 int max = Integer.parseInt(str2);
                 Random random = new Random();
                 int number = random.nextInt(max - min + 1) + min;
                 txtRandom.setText(String.valueOf(number));
             }
         }
     });


    }
    private  void AnhXa()
    {
        txtRandom = (TextView)findViewById(R.id.textViewNumber);
        btnRandom=(Button)findViewById(R.id.buttonRandom);
        edtNum1=(EditText)findViewById(R.id.editTextTextNumber1);
        edtNum2=(EditText)findViewById(R.id.editTextTextNumber2);
    }
}