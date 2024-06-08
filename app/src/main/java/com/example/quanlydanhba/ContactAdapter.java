package com.example.quanlydanhba;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    public ContactAdapter(Context context, List<Contact> contacts) {
        super(context, android.R.layout.simple_list_item_1, contacts);
    }

    // Bạn có thể override các phương thức khác để tùy chỉnh hiển thị
}