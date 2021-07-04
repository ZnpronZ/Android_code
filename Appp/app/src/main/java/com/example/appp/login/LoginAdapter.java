package com.example.appp.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appp.R;

import java.util.List;

public class LoginAdapter extends BaseAdapter {
    private LoginMain context;
    private int layout;
    private List<Login> loginList;

    public LoginAdapter(LoginMain context, int layout, List<Login> loginList) {
        this.context = context;
        this.layout = layout;
        this.loginList = loginList;
    }

    @Override
    public int getCount() {
        return loginList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView txtUsername,txtPassword;
        ImageView imgDel,imgEdit;
    }
    @Override
    public View getView(int i , View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.txtUsername= (TextView) convertView.findViewById(R.id.textViewUser);
            holder.txtPassword= (TextView) convertView.findViewById(R.id.textViewPass);
            holder.imgEdit= (ImageView) convertView.findViewById(R.id.imageViewEdit);
            holder.imgDel= (ImageView) convertView.findViewById(R.id.imageViewDel);
            convertView.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) convertView.getTag();
        }
        final Login login = loginList.get(i);
        holder.txtUsername.setText(login.getUsername());
        holder.txtPassword.setText(login.getPassword());
        // bắt sự kiện sửa,xóa
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.DialogSuaLogin(login.getUsername(),login.getPassword(),login.getId());
            }
        });
        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.DialogXoaLogin(login.getUsername(),login.getId());
            }
        });
        return convertView;
    }
}
