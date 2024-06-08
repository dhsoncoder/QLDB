package com.example.quanlydanhba;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class nhanvienAdapter extends ArrayAdapter<nhanvien> {
    public nhanvienAdapter(Context context, List<nhanvien> contacts){
        super(context, android.R.layout.simple_list_item_1,contacts);
    }
}
