package com.example.appp.mainmusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.appp.R;
import com.example.appp.adaptermusic.Adapter_casi;
import com.example.appp.listmusic.list_casi;

import java.util.ArrayList;
import java.util.List;

public class MainMusicActivity extends AppCompatActivity {
    ListView listView_casi;
    ArrayList<com.example.appp.listmusic.list_casi> list_casi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_music);

        listView_casi = (ListView) findViewById(R.id.list_casi);
        list_casi = new ArrayList<>();
        list_casi.add(new list_casi("Khắc Việt", R.drawable.khacviet));
        list_casi.add(new list_casi("Lê Bảo Bình", R.drawable.lebaobinh));
        list_casi.add(new list_casi("The Men", R.drawable.themen));
        list_casi.add(new list_casi("Tuấn Hưng", R.drawable.tuanhung));
        Adapter_casi adapter_casi = new Adapter_casi(MainMusicActivity.this, R.layout.list_ca_si, list_casi);
        listView_casi.setAdapter(adapter_casi);
        listView_casi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainMusicActivity.this, Activity_bai_hat.class);
                intent.putExtra("position", position);
                intent.putExtra("tencasi", list_casi.get(position).ten_casi);
                startActivity(intent);
            }


        });
    }
}