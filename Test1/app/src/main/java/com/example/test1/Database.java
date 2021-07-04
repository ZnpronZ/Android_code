package com.example.test1;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="db_vetau";
    private static final String TABLE_NAME ="VeTau";
    private static final String MA ="MA";
    private static final String GADI ="GADI";
    private static final String GADEN ="GADEN";
    private static final String DONGIA ="DONGIA";
    private static final String KHUHOI ="KHUHOI";

    private Context context;

    public Database(Context context) {
        super(context, DATABASE_NAME,null, 1);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE "+TABLE_NAME +" (" +
                MA +" integer primary key  AUTOINCREMENT, "+
                GADI + " TEXT, "+
                GADEN + " TEXT, "+
                DONGIA + " DOUBLE, "+
                KHUHOI +" INT)";
        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public  void xoaToanBo(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from VeTau");
    }


    //Add new a student
    public void addSP(Vetau monhoc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GADI, monhoc.getGadi());
        values.put(GADEN, monhoc.getGaden());
        values.put(DONGIA, monhoc.getDongia());
        if(monhoc.isKhuhoi()==true)
            values.put(KHUHOI, 1);
        else
            values.put(KHUHOI, 0);
        db.insert(TABLE_NAME,null,values);

        db.close();
    }
    public void updateMonHoc(Vetau monhoc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MA, monhoc.getMa());
        values.put(GADI, monhoc.getGadi());
        values.put(GADEN, monhoc.getGaden());
        values.put(DONGIA, monhoc.getDongia());
        if(monhoc.isKhuhoi()==true)
            values.put(KHUHOI, 1);
        else
            values.put(KHUHOI, 0);

        db.update(TABLE_NAME,values,"MA = ?", new String[] {String.valueOf(monhoc.getMa())});

        db.close();
    }
//    public List<ThiSinh> getTheoTen(String ten) {
//        List<ThiSinh> listMonHoc = new ArrayList<ThiSinh>();
//        String selectQuery = "select * from "+TABLE_NAME+" where "+HOTEN+" LIKE'%"+ten+"%'";
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                ThiSinh monhoc = new ThiSinh();
//                monhoc.setSoBaoDanh(cursor.getString(0));
//                monhoc.setHoTen(cursor.getString(1));
//                monhoc.setDiemToan(cursor.getFloat(2));
//                monhoc.setDiemLy(cursor.getFloat(3));
//                monhoc.setDiemHoa(cursor.getFloat(4));
//
//                listMonHoc.add(monhoc);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return listMonHoc;
//    }
//
//

    public void QueryData(String sql){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);
    }


    public List<Vetau> getAll() {
        List<Vetau> listMonHoc = new ArrayList<Vetau>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME  ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Vetau monhoc = new Vetau();
                monhoc.setMa(cursor.getInt(0));
                monhoc.setGadi(cursor.getString(1));
                monhoc.setGaden(cursor.getString(2));
                monhoc.setDongia(cursor.getDouble(3));
                if(cursor.getInt(4)==1)
                    monhoc.setKhuhoi(true);
                else
                    monhoc.setKhuhoi(false);

                listMonHoc.add(monhoc);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listMonHoc;
    }

//    public void remove(String id){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("delete from "+TABLE_NAME+" where "+SBD+ " = '"+id+"'");
//    }
//
//    public void deleteSong(ThiSinh student) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_NAME, SBD + " = ?",
//                new String[] { String.valueOf(student.getSoBaoDanh()) });
//        db.close();
//    }


//    public List<ThiSinh> getIDMonHoc(String ma) {
//        List<ThiSinh> listMonHoc = new ArrayList<ThiSinh>();
//        String selectQuery = "SELECT  * FROM " + TABLE_NAME +" where SOBAODANH = "+ma+"";
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                ThiSinh monhoc = new ThiSinh();
//                monhoc.setSoBaoDanh(cursor.getString(0));
//                monhoc.setHoTen(cursor.getString(1));
//                monhoc.setDiemToan(cursor.getFloat(2));
//                monhoc.setDiemLy(cursor.getFloat(3));
//                monhoc.setDiemHoa(cursor.getFloat(4));
//                listMonHoc.add(monhoc);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return listMonHoc;
//    }
}

