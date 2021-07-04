package com.example.bai2_cuavinh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdapterThietBi extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ThietBi> thietBiList;

    public AdapterThietBi(Context context, int layout, List<ThietBi> thietBiList) {
        this.context = context;
        this.layout = layout;
        this.thietBiList = thietBiList;
    }

    @Override
    public int getCount() {
        return thietBiList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
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
            holder.txtMota=(TextView) convertView.findViewById(R.id.textViewMoTa);
            holder.txtTenThietBi=(TextView) convertView.findViewById(R.id.textViewTenthietbi);
            holder.switchCheck=(Switch)convertView.findViewById(R.id.switchStatus);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder) convertView.getTag();
        }
        ThietBi thietBi=thietBiList.get(position);
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
