package com.example.bai4;

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
    private ArrayList<User> data;
    private LayoutInflater inflater;

    public MainAdapter(Activity activity, ArrayList<User> data) {
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
            v = inflater.inflate(R.layout.contact_layout, null);
            ImageView image = v.findViewById(R.id.ivAva);
            TextView masv = v.findViewById(R.id.tvMaSV);
            masv.setText(data.get(position).getMaSV());
            TextView name = v.findViewById(R.id.tvName);
            name.setText(data.get(position).getName());
            TextView diem = v.findViewById(R.id.tvDiem);
            float TongDiem = data.get(position).getDiemHoa() + data.get(position).getDiemLy()+data.get(position).getDiemToan();
            diem.setText(""+ TongDiem);

        }
        return v;
    }
}
