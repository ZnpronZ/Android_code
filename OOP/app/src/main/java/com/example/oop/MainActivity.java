package com.example.oop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SinhVien sv = new SinhVien();
        sv.HoTen="Nguyen Van A";
        sv.DiaChi= "Ha Noi";
        sv.Nam=2000;

        Toast.makeText(MainActivity.this,sv.HoTen+ " "+sv.DiaChi+" "+sv.Nam,Toast.LENGTH_LONG).show();
    }
}