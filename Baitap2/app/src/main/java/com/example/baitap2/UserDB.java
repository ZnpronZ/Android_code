package com.example.baitap2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserDB extends SQLiteOpenHelper {
    public static final String TableName = "ContactTable";
    public static final String Id = "Id";
    public static final String Name = "FullName";
    public static final String Description = "Description";
    public static final String imgUrl="imgUrl";
    public static final String Status = "Status";
    public UserDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       //xoa bang contact da co
        String sqlCreate = "Create table if not exists " + TableName + "(" +
                Id + " Integer Primary key AUTOINCREMENT," + Name + " Text," + Description + " Text, " + imgUrl + " Text, " + Status + " Text)";
//tao lai sql
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TableName);
        onCreate(db);
    }

    public  void addContact(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Id,user.getId());
        values.put(Name,user.getName());
        values.put(Description,user.getDescription());
        values.put(imgUrl,user.getImgUrl());
        values.put(Status,user.isStatus());
        db.insert(TableName,null,values);
        db.close();
    }
    public  void updateContact(int id,User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Id, user.getId());
        values.put(Name,user.getName());
        values.put(Description,user.getDescription());
        values.put(imgUrl,user.getImgUrl());
        values.put(Status,user.isStatus());
        db.update(TableName,values,Id+ "=?",new String[]{String.valueOf(id)});
        db.close();

    }
    public  void deleteContact(int id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Delete From "+ TableName+ " Where Id ="+ id;
        db.execSQL(sql);
        db.close();


    }
    public ArrayList<User> getAllContact(){
        ArrayList<User> list = new ArrayList<>();
        String sql = "Select * from "+ TableName;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setDescription(cursor.getString(2));
                user.setImgUrl(cursor.getString(3));
                if(cursor.getInt(4)==1)
                    user.setStatus(true);
                else
                    user.setStatus(false);

                list.add(user);
            } while (cursor.moveToNext());
        }
        return list;
    }
}
