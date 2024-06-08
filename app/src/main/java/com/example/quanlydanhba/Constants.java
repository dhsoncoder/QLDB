package com.example.quanlydanhba;

public class Constants {
    // database or db name
    public static final String DATABASE_NAME = "qldb";
    //database version
    public static final int DATABASE_VERSION = 1;

    // table name
    public static final String TABLE_NAME = "donvi";

    // table column or field name
    public static final String dv_id = "madv";
    public static final String dv_logo = "logo";
    public static final String dv_tendv = "tendv";
    public static final String dv_sdt = "sdt";
    public static final String dv_email = "email";
    public static final String dv_website = "website";
    public static final String dv_diachi = "diachi";
    public static final String dv_madvcha = "madvcha";

    // query for create table
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( "
            + dv_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + dv_logo + " TEXT, "
            + dv_tendv + " TEXT, "
            + dv_sdt + " TEXT, "
            + dv_email + " TEXT, "
            + dv_website + " TEXT, "
            + dv_diachi + " TEXT, "
            + dv_madvcha + " INTEGER"
            + " );";


    // Create database helper class for CRUD Query And Database Creation
}
