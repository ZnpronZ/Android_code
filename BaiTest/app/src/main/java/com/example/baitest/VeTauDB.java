package com.example.baitest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class VeTauDB extends SQLiteOpenHelper {
    public static final String TableName = "VeTauTable";
    public static final String Id = "Id";
    public static final String  GaDi = "GaDi";
    public static final String GaDen = "GaDen";
    public static final String Gia = "DonGia";
    public static final String KhuHoi = "KhuHoi";
    public VeTauDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //xoa bang contact da co
        String sqlCreate = "Create table if not exists " + TableName + "(" +
                Id + " Integer Primary key AUTOINCREMENT," + GaDi + " Text," + GaDen + " Text," + Gia + " Float," + KhuHoi + " Text)";
//tao lai sql
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TableName);
        onCreate(db);
    }
    public  void addVeTau(VeTau vetau){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Id,vetau.getId());
        values.put(GaDi,vetau.getGaDi());
        values.put(GaDen,vetau.getGaDen());
        values.put(Gia,vetau.getGia());
        values.put(KhuHoi,vetau.getKhuHoi());
        db.insert(TableName,null,values);
        db.close();
    }
    public  void updateVeTau(int id,VeTau vetau){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Id,vetau.getId());
        values.put(GaDi,vetau.getGaDi());
        values.put(GaDen,vetau.getGaDen());
        values.put(Gia,vetau.getGia());
        values.put(KhuHoi,vetau.getKhuHoi());
        db.update(TableName,values,Id+ "=?",new String[]{String.valueOf(id)});
        db.close();

    }
    public  void deleteVeTau(int id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Delete From "+ TableName+ " Where ID ="+ id;
        db.execSQL(sql);
        db.close();


    }
    public ArrayList<VeTau> getAllVeTau(){
        ArrayList<VeTau> list = new ArrayList<>();
        String sql = "Select * from "+ TableName + " order by "+ Gia +" desc";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()) {
            do {
                VeTau vetau = new VeTau();
                vetau.setId(cursor.getInt(0));
                vetau.setGaDi(cursor.getString(1));
                vetau.setGaDen(cursor.getString(2));
                vetau.setGia(cursor.getFloat(3));
                if(cursor.getInt(4)==1)
                    vetau.setKhuHoi(true);
                else
                    vetau.setKhuHoi(false);

                list.add(vetau);
            } while (cursor.moveToNext());
        }
        return list;
    }
}
