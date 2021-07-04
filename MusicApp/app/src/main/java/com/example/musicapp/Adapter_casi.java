package com.example.musicapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter_casi extends BaseAdapter {
    Context mContext;
    int mLayout;
    List<list_casi> mlist_casi;
    Adapter_casi(Context context,int Layout,List<list_casi> list_casis){
        mContext=context;
        mLayout=Layout;
        mlist_casi=list_casis;
    }
    @Override
    public int getCount() {
        return mlist_casi.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater!=null;
        convertView=inflater.inflate(mLayout,null);
        //anh xa
        ImageView IMG_casi=(ImageView) convertView.findViewById(R.id.IMG_casi);
        IMG_casi.setBackgroundResource(mlist_casi.get(position).hinh_casi);
        TextView txt_tencasi=(TextView) convertView.findViewById(R.id.txt_casi);
        txt_tencasi.setText(mlist_casi.get(position).ten_casi);
        return convertView;
    }
}
