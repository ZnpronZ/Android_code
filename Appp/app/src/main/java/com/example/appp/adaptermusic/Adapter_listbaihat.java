package com.example.appp.adaptermusic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appp.R;
import com.example.appp.listmusic.list_baihat;

import java.util.ArrayList;
import java.util.List;

public class Adapter_listbaihat extends BaseAdapter {
    Context mContext;
    int mLayout;
    List<list_baihat> mlist_baihats;
    public Adapter_listbaihat(Context context, int Layout, ArrayList<list_baihat> list_baihats){
        mContext=context;
        mLayout=Layout;
        mlist_baihats=list_baihats;
    }
    @Override
    public int getCount() {
        return mlist_baihats.size();
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
        TextView txt_tenbaihat=(TextView) convertView.findViewById(R.id.ten_bai_hat);
        txt_tenbaihat.setText(mlist_baihats.get(position).tenbaihat);
        TextView txt_tgbaihat=(TextView) convertView.findViewById(R.id.sum_time);
        txt_tgbaihat.setText(mlist_baihats.get(position).tgbai);
        return convertView;
    }
}
