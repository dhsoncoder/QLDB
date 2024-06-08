package com.example.quanlydanhba;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    nhanvienAdapter nhanvienAdapter;
    donviAdapter donviAdapter;

    SQLiteDatabase mydatabase;
    FloatingActionButton btnThem;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        lv=findViewById(R.id.lv);
        donviAdapter = new donviAdapter(this, new ArrayList<>());
        nhanvienAdapter = new nhanvienAdapter(this, new ArrayList<>());
        lv.setAdapter(nhanvienAdapter);
        btnThem = findViewById(R.id.btnThem);
        tabLayout = findViewById(R.id.tabLayout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mydatabase = openOrCreateDatabase("qldb.db", MODE_PRIVATE, null);
        int selectedTabPosition = tabLayout.getSelectedTabPosition();

        if (selectedTabPosition == 0) { // Tab "Đơn vị"
            lv.setAdapter(donviAdapter);
        } else { // Tab "Nhân viên"
            lv.setAdapter(nhanvienAdapter);
        }

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

    }
}