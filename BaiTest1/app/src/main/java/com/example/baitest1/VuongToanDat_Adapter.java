package com.example.baitest1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class VuongToanDat_Adapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Contact_Dat> data;
    private LayoutInflater inflater;

    public VuongToanDat_Adapter(Activity activity, ArrayList<Contact_Dat> data) {
        this.activity = activity;
        this.data = data;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public VuongToanDat_Adapter() {
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
            v = inflater.inflate(R.layout.contact_layout, null);
            TextView id = v.findViewById(R.id.tvId);
            id.setText(String.valueOf(data.get(position).getId()));
            TextView name = v.findViewById(R.id.tvName);
            name.setText(data.get(position).getName());
            TextView phone = v.findViewById(R.id.tvPhoneNum);
            phone.setText(data.get(position).getPhone());


        }
        return v;
    }
}
