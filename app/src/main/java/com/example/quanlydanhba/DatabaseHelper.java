package com.example.quanlydanhba;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "qldb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng donvi và nhanvien nếu chưa tồn tại
        String createDonViTable = "CREATE TABLE donvi (madv INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, tendv TEXT NOT NULL, email TEXT NOT NULL, website TEXT, logo TEXT, diachi TEXT NOT NULL, sdt TEXT NOT NULL, madvcha INTEGER NOT NULL)";

        db.execSQL(createDonViTable);

        String createNhanVienTable ="CREATE TABLE nhanvien (manv INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, hoten TEXT NOT NULL, chucvu TEXT, email TEXT NOT NULL, sdt TEXT NOT NULL, anhdd TEXT NOT NULL, madv INTEGER NOT NULL, FOREIGN KEY (madv) REFERENCES donvi(madv))";

        db.execSQL(createNhanVienTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xử lý nâng cấp database nếu cần
    }
}