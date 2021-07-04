package com.example.java_basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String hoten="znpronz";
        int nam= 2021 ,x=10 ,a=1 ;
        if(x<=9)
        Log.d("Dat",hoten +nam);
        else
            Log.d("Dat","sai");

        while (a<=x)
        {
            a++;
            Log.d("dat","1"+a);
        }
        //tao mảng
        ArrayList<String> arrayName = new ArrayList<>();
        //thêm phần tử
        arrayName.add("znpronz");
        arrayName.add("gg");
        arrayName.add("zz");
        //lấy kích thước mảng
        Log.d("znpronz","size: "+arrayName.size());
        //bỏ
       // arrayName.remove(1);
        // truy cập vào 1 phần tử
        Log.d("znpronz","phần tử: "+arrayName.get(1));
        //Duyệt mảng
        for(int i=0;i< arrayName.size();i++)
        {
            Log.d("znpronz","phần tử: "+arrayName.get(i));

        }
        for(String ten : arrayName)
        {
            Log.d("znpronz",ten);

        }
        thongbao("dat");
    }
    private  void thongbao(String ten)
    {
        Log.d("znz","Hello world "+ten);

    }
}