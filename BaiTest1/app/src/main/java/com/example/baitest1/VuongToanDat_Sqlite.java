package com.example.baitest1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class VuongToanDat_Sqlite extends SQLiteOpenHelper {
    public static final String TableName = "Contact_Dat";
    public static final String Id = "Id";
    public static final String Name = "FullName";
    public static final String Phone = "PhoneNumber";

    public VuongToanDat_Sqlite(Context contex, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(contex, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       //xoa bang contact da co
        String sqlCreate = "Create table if not exists " + TableName + "(" +
                Id + " Integer Primary key," + Name + " Text," + Phone + " Text)";
//tao lai sql
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TableName);
        onCreate(db);
    }

    public  void addContact(Contact_Dat user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Id,user.getId());
        values.put(Name,user.getName());
        values.put(Phone,user.getPhone());
        db.insert(TableName,null,values);
        db.close();
    }
    public  void updateContact(int id,Contact_Dat user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Id, user.getId());
        values.put(Name,user.getName());
        values.put(Phone,user.getPhone());
        db.update(TableName,values,Id+ "=?",new String[]{String.valueOf(id)});
        db.close();

    }
    public  void deleteContact(int id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Delete From "+ TableName+ " Where ID ="+ id;
        db.execSQL(sql);
        db.close();


    }
    public ArrayList<Contact_Dat> getAllContact(){
        ArrayList<Contact_Dat> list = new ArrayList<>();
        String sql = "Select * from "+ TableName+ " order by "+ Name +" desc";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql,null);
        if(cursor != null){
            while(cursor.moveToNext()){
                Contact_Dat user = new Contact_Dat(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2));
                list.add(user);
            }
        }
        return list;
    }
}
