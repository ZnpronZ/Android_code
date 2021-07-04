package com.example.baitap1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBContact extends SQLiteOpenHelper {
    public static final String TableName = "ContactTable";
    public static final String Id = "Id";
    public static final String Name = "FullName";
    public static final String Phone = "PhoneNumber";
    public static final String Status = "Status";
    public DBContact(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       //xoa bang contact da co
        String sqlCreate = "Create table if not exists " + TableName + "(" +
                Id + " Integer Primary key AUTOINCREMENT," + Name + " Text," + Phone + " Text, " + Status + " Text)";
//tao lai sql
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TableName);
        onCreate(db);
    }

    public  void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Id,contact.getId());
        values.put(Name,contact.getName());
        values.put(Phone,contact.getPhoneNumber());
        values.put(Status,contact.getStatus());
        db.insert(TableName,null,values);
        db.close();
    }
    public  void updateContact(int id,Contact contact){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Id, contact.getId());
        values.put(Name,contact.getName());
        values.put(Phone,contact.getPhoneNumber());
        values.put(Status,contact.getStatus());
        db.update(TableName,values,Id+ "=?",new String[]{String.valueOf(id)});
        db.close();

    }
    public  void deleteContact(int id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Delete From "+ TableName+ " Where Id ="+ id;
        db.execSQL(sql);
        db.close();


    }
    public ArrayList<Contact> getAllContact(){
        ArrayList<Contact> list = new ArrayList<>();
        String sql = "Select * from "+ TableName;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(cursor.getInt(0));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                if(cursor.getInt(3)==1)
                    contact.setStatus(true);
                else
                    contact.setStatus(false);

                list.add(contact);
            } while (cursor.moveToNext());
        }
        return list;
    }
}
