package com.example.quanlydanhba;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvDonVi;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;

    DbHelper dbHelper;
    SQLiteDatabase mydatabase;
    FloatingActionButton btnThem;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(this);

        lvDonVi =findViewById(R.id.lv);
        hienThiDanhSachDonVi();

        btnThem = findViewById(R.id.btnThem);
        tabLayout = findViewById(R.id.tabLayout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedTabPosition = tabLayout.getSelectedTabPosition();
                Intent intent;
                if (selectedTabPosition == 0) { // Tab "Đơn vị"
                    intent = new Intent(MainActivity.this, themdv.class);
                } else { // Tab "Nhân viên"
                    intent = new Intent(MainActivity.this, themnv.class);
                }
                startActivity(intent);
            }
        });
        // Bắt sự kiện click vào item trên listview sẽ đổi sang view chi tiet donvi
        lvDonVi.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, xemdv.class);
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);

            int donviId = cursor.getInt(cursor.getColumnIndex(Constants.dv_id));
            intent.putExtra("donviId", donviId);
            startActivity(intent);
        });

        // Bắt sự kiện đổi tab


    }
    private void hienThiDanhSachDonVi() {
        Cursor cursor = dbHelper.getAllDonVi();

        // Sử dụng các hằng số từ lớp Constants
        String[] fromColumns = {Constants.dv_tendv, Constants.dv_email};
        int[] toViews = {R.id.ten_don_vi_textview, R.id.email_don_vi_textview}; // ID của các TextView trong don_vi_item.xml

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.don_vi_item, cursor, fromColumns, toViews, 0);
        lvDonVi.setAdapter(adapter);
    }

    }

