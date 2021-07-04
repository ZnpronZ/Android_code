package com.example.baitest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<VeTau> data;
    private LayoutInflater inflater;

    public MainAdapter(Activity activity, ArrayList<VeTau> data) {
        this.activity = activity;
        this.data = data;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public MainAdapter() {
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
            v = inflater.inflate(R.layout.vetau_layout, null);
            TextView gadi = v.findViewById(R.id.tvGaDi);
            gadi.setText(data.get(position).getGaDi());
            TextView gaden = v.findViewById(R.id.tvGaDen);
            gaden.setText(data.get(position).getGaDen());
            TextView gia = v.findViewById(R.id.tvGia);
            gia.setText(String.valueOf(data.get(position).getGia()*2*0.95));
            TextView khuhoi = v.findViewById(R.id.tvKhuHoi);
            if(data.get(position).getKhuHoi())
            {
                khuhoi.setText("Khứ Hồi");
            }
            else
            {
                khuhoi.setText("Một Chiều");
            }
        }
        return v;
    }
}
