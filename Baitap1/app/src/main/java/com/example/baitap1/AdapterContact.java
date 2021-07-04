package com.example.baitap1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class AdapterContact extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Contact> contactList;

    public AdapterContact(Context context, int layout, List<Contact> thietBiList) {
        this.context = context;
        this.layout = layout;
        this.contactList = thietBiList;
    }

    @Override
    public int getCount() {
        return contactList.size();
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
        TextView txtTen;
        TextView txtPhone;
        CheckBox checkBox;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout,null);
            holder.txtTen=(TextView) convertView.findViewById(R.id.textViewName);
            holder.txtPhone=(TextView) convertView.findViewById(R.id.textViewPhoneNumber);
            holder.checkBox=(CheckBox) convertView.findViewById(R.id.checkBox);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder) convertView.getTag();
        }
        Contact contact = contactList.get(position);
        holder.txtTen.setText(contact.getName());
        holder.txtPhone.setText(contact.getPhoneNumber());
        holder.checkBox.setChecked(contact.getStatus());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.checkBox.isChecked()){
                    contact.setStatus(true);
                }else {
                    contact.setStatus(false);
                }
            }
        });
        return convertView;
    }
}
