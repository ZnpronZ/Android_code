package com.example.baitap2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;

import java.util.ArrayList;
import java.util.List;

public class    MainAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<User> data;

    public MainAdapter(Context context, int layout, List<User> thietBiList) {
        this.context = context;
        this.layout = layout;
        this.data = thietBiList;
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
    public class ViewHolder{
        TextView txtTenThietBi;
        TextView txtMota;
        Switch switchCheck;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout,null);
            holder.txtMota=(TextView) convertView.findViewById(R.id.tvDescription);
            holder.txtTenThietBi=(TextView) convertView.findViewById(R.id.tvName);
            holder.switchCheck=(Switch)convertView.findViewById(R.id.switch1);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder) convertView.getTag();
        }
        User thietBi= data.get(position);
        holder.txtTenThietBi.setText(thietBi.getName());
        holder.txtMota.setText(thietBi.getDescription());
        holder.switchCheck.setChecked(thietBi.isStatus());
        holder.switchCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.switchCheck.isChecked()){
                    thietBi.setStatus(true);
                }else {
                    thietBi.setStatus(false);
                }
            }
        });
        return convertView;
    }


}
