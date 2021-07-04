package com.example.test1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class VetaAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Vetau> arrayList;

    public VetaAdapter(Context context, int layout, ArrayList<Vetau> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);


        TextView tvGadi_Gaden = (TextView) view.findViewById(R.id.tvGadi_gaden);
        TextView tvKhuhoi = (TextView) view.findViewById(R.id.tvKhuhoi);
        TextView tvGiaTien = (TextView) view.findViewById(R.id.tvGiaTien);


        Vetau nhac = arrayList.get(i);
        tvGadi_Gaden.setText(nhac.getGadi() + "->"+nhac.getGaden());
        if(nhac.isKhuhoi()==true)
            tvKhuhoi.setText("Khứ hồi");
        else
            tvKhuhoi.setText("Một chiều");
        tvGiaTien.setText(nhac.getGiaTien()+"");
        return view;
    }

}
