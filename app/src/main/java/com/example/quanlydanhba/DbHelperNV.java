package com.example.quanlydanhba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelperNV extends SQLiteOpenHelper {
    public DbHelperNV(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE);
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
    public Cursor getAllDonVi() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT " + Constants.dv_id + " AS _id, * FROM " + Constants.TABLE_NAME, null);
    }
}
