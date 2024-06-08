package com.example.quanlydanhba;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class donviAdapter extends ArrayAdapter<donvi> {
    public donviAdapter(Context context, List<donvi> contacts){
        super(context, android.R.layout.simple_list_item_1,contacts);
    }
}
