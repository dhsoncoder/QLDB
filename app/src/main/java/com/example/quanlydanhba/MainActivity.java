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
import android.widget.SearchView;
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
    ListView lvDonVi, lvNhanVien;
    DbHelper dbHelper;
    FloatingActionButton btnThem;
    TabLayout tabLayout;
    private SearchView searchView;
    private ArrayAdapter<String> adapter; // Adapter cho ListView
    private ArrayList<String> dataList;
    private ArrayList<Integer> idList; // Add a list to store the IDs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(this);

        searchView = findViewById(R.id.searchView);
        lvNhanVien = findViewById(R.id.lv);

        btnThem = findViewById(R.id.btnThem);
        tabLayout = findViewById(R.id.tabLayout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        hienThiDanhSachDonVi();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText);
                return true;
            }
        });

        btnThem.setOnClickListener(v -> {
            int selectedTabPosition = tabLayout.getSelectedTabPosition();
            Intent intent;
            if (selectedTabPosition == 0) { // Tab "Đơn vị"
                intent = new Intent(MainActivity.this, themdv.class);
            } else { // Tab "Nhân viên"
                intent = new Intent(MainActivity.this, themnv.class);
            }
            startActivity(intent);
        });

        lvNhanVien.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent;
            int selectedTabPosition = tabLayout.getSelectedTabPosition();
            if (selectedTabPosition == 0) {
                intent = new Intent(MainActivity.this, activity_xemdv.class);
                if (dataList != null && position < idList.size()) {
                    intent.putExtra("donviId", idList.get(position));
                }
            } else {
                intent = new Intent(MainActivity.this, activity_xemnv.class);
                if (dataList != null && position < idList.size()) {
                    intent.putExtra("nhanvienId", idList.get(position));
                }
            }
            startActivity(intent);
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int selectedTabPosition = tab.getPosition();
                if (selectedTabPosition == 0) {
                    hienThiDanhSachDonVi();
                } else {
                    hienThiDanhSachNhanVien();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void performSearch(String query) {
        if (dataList != null) {
            ArrayList<String> filteredList = new ArrayList<>();
            for (String item : dataList) {
                if (item.toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filteredList);
            lvNhanVien.setAdapter(adapter);
        }
    }

    private void hienThiDanhSachDonVi() {
        Cursor cursor = dbHelper.getAllDonVi();
        dataList = new ArrayList<>();
        idList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String tenDonVi = cursor.getString(cursor.getColumnIndex(Constants.dv_tendv));
                int id = cursor.getInt(cursor.getColumnIndex(Constants.dv_id));
                dataList.add(tenDonVi);
                idList.add(id);
            } while (cursor.moveToNext());
        }
        cursor.close();
        dbHelper.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        lvNhanVien.setAdapter(adapter);
    }

    private void hienThiDanhSachNhanVien() {
        Cursor cursor = dbHelper.getAllNhanVien();
        dataList = new ArrayList<>();
        idList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String tenNhanVien = cursor.getString(cursor.getColumnIndex(Constants.nv_tennv));
                int id = cursor.getInt(cursor.getColumnIndex(Constants.nv_id));
                dataList.add(tenNhanVien);
                idList.add(id);
            } while (cursor.moveToNext());
        }
        cursor.close();
        dbHelper.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        lvNhanVien.setAdapter(adapter);
    }
}
