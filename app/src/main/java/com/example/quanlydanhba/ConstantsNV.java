package com.example.quanlydanhba;

public class ConstantsNV {
    // database or db name
    public static final String DATABASE_NAME = "qldb";
    //database version
    public static final int DATABASE_VERSION = 1;

    // table name
    public static final String TABLE_NAME_NV = "nhanvien";

    // table column or field name

    public static final String nv_id = "manv";
    public static final String nv_tennv = "tennv";
    public static final String nv_chucvu = "chucvu";
    public static final String nv_email = "email";
    public static final String nv_sdt = "sdt";
    public static final String nv_anhdd = "anhdd";
    public static final String nv_madv = "madv";
    // query for create table
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_NV + "( "
            + nv_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + nv_tennv + " TEXT, "
            + nv_chucvu + " TEXT, "
            + nv_sdt + " TEXT, "
            + nv_email + " TEXT, "
            + nv_anhdd + " TEXT, "
            + nv_madv + " INTEGER,"
            + " FOREIGN KEY (" + nv_madv + ") REFERENCES " + Constants.TABLE_NAME + "(" + Constants.dv_id + ")"
            + ");";


    // Create database helper class for CRUD Query And Database Creation
}
