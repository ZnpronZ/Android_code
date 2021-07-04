package com.example.baitap3;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import java.util.ArrayList;

public class MusicDB extends SQLiteOpenHelper {
    public static final String TableName = "MusicTable";
    public static final String Id = "Id";
    public static final String BaiHat = "BaiHat";
    public static final String Phut = "Phut";
    public static final String CaSi = "CaSi";

    public MusicDB(Context contex, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(contex, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //xoa bang contact da co
        String sqlCreate = "Create table if not exists " + TableName + "(" + BaiHat + "Text,"
                + Id + " Integer Primary key,"  + CaSi + " Text," + Phut + " Double)";
//tao lai sql
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TableName);
        onCreate(db);
    }

    public  void addContact(Music music){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BaiHat,music.getName());
        values.put(Id,music.getId());
        values.put(CaSi,music.getSinger());
        values.put(Phut,music.getTime());

        db.insert(TableName,null,values);
        db.close();
    }
    public  void updateContact(int id,Music music){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BaiHat,music.getName());
        values.put(Id,music.getId());
        values.put(CaSi,music.getSinger());
        values.put(Phut,music.getTime());
        db.update(TableName,values,Id+ "=?",new String[]{String.valueOf(id)});
        db.close();

    }
    public  void deleteContact(int id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Delete From "+ TableName+ " Where ID ="+ id;
        db.execSQL(sql);
        db.close();


    }
    public ArrayList<Music> getAllContact(){
        ArrayList<Music> list = new ArrayList<>();
        String sql = "Select * from "+ TableName;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql,null);
        if(cursor != null){
            while(cursor.moveToNext()){
                Music music = new Music(cursor.getString(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getDouble(3));
                list.add(music);
            }
        }
        return list;
    }
}
