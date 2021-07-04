package com.example.baitap3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MusicAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Music> data;
    private LayoutInflater inflater;

    public MusicAdapter(Activity activity,  ArrayList<Music> data) {
        this.activity = activity;
        this.data = data;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public MusicAdapter() {
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = inflater.inflate(R.layout.music_layout, null);
            TextView name = v.findViewById(R.id.tvName);
            name.setText(data.get(position).getName());
            TextView min = v.findViewById(R.id.tvMin);
            min.setText(String.valueOf(data.get(position).getTime()));
            TextView singer = v.findViewById(R.id.tvSinger);
            singer.setText(data.get(position).getSinger());

        }
        return v;
    }
}
