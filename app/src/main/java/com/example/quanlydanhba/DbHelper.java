package com.example.quanlydanhba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context ) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE);
        db.execSQL(Constants.CREATE_TABLE_NV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long insertContact(String image,String name,String phone,String email,String website,String diachi,String madvcha){

        //get writable database to write data on db
        SQLiteDatabase db = this.getWritableDatabase();

        // create ContentValue class object to save data
        ContentValues contentValues = new ContentValues();

        // id will save automatically as we write query
        contentValues.put(Constants.dv_logo,image);
        contentValues.put(Constants.dv_tendv,name);
        contentValues.put(Constants.dv_sdt,phone);
        contentValues.put(Constants.dv_email,email);
        contentValues.put(Constants.dv_website,website);
        contentValues.put(Constants.dv_diachi,diachi);
        contentValues.put(Constants.dv_madvcha,madvcha);

        //insert data in row, It will return id of record
        long id = db.insert(Constants.TABLE_NAME,null,contentValues);

        // close db
        db.close();

        //return id
        return id;

    }
    public long insertNV(String image,String name,String phone,String email,String chucvu,String madv){

        //get writable database to write data on db
        SQLiteDatabase db = this.getWritableDatabase();

        // create ContentValue class object to save data
        ContentValues contentValues = new ContentValues();

        // id will save automatically as we write query
        contentValues.put(Constants.nv_anhdd,image);
        contentValues.put(Constants.nv_tennv,name);
        contentValues.put(Constants.nv_sdt,phone);
        contentValues.put(Constants.nv_email,email);
        contentValues.put(Constants.nv_chucvu,chucvu);
        contentValues.put(Constants.nv_madv,madv);

        //insert data in row, It will return id of record
        long id = db.insert("nhanvien",null,contentValues);

        // close db
        db.close();

        //return id
        return id;

    }
    public boolean updateNV(int manv, String tennv, String chucvu, String email, String sdt, String anhdd, int madv) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.nv_tennv, tennv);
        values.put(Constants.nv_chucvu, chucvu);
        values.put(Constants.nv_email, email);
        values.put(Constants.nv_sdt, sdt);
        values.put(Constants.nv_anhdd, anhdd);
        values.put(Constants.nv_madv, madv);

        int rowsAffected = db.update(Constants.TABLE_NAME_NV, values, Constants.nv_id + " = ?", new String[]{String.valueOf(manv)});
        db.close();
        return rowsAffected > 0; // Trả về true nếu cập nhật thành công
    }
    public boolean updateDV(int madv, String tendv, String motadv) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.dv_tendv, tendv);


        int rowsAffected = db.update(Constants.TABLE_NAME, values, Constants.dv_id + " = ?", new String[]{String.valueOf(madv)});
        db.close();
        return rowsAffected > 0; // Trả về true nếu cập nhật thành công
    }
    public Cursor getDonViSpiner(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT " + Constants.dv_id + " , " + Constants.dv_tendv + " FROM " + Constants.TABLE_NAME, null);
    }
    public Cursor getAllDonVi() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT " + Constants.dv_id + " AS _id, * FROM " + Constants.TABLE_NAME, null);
    }
    public Cursor getAllNhanVien() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT " + Constants.nv_id + " AS _id, * FROM " + Constants.TABLE_NAME_NV, null);
    }
    public Cursor getNhanVienById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + Constants.TABLE_NAME_NV + " WHERE " + Constants.nv_id + " = ?", new String[]{String.valueOf(id)});
    }
    public Cursor getDonViById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + Constants.TABLE_NAME + " WHERE " + Constants.dv_id + " = ?", new String[]{String.valueOf(id)});
    }
}
