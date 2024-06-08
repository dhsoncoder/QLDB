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
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;
    ContactAdapter contactAdapter;
    SQLiteDatabase mydatabase;
    FloatingActionButton btnThem;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        lv=findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(myadapter);;
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
        // Mở database
        mydatabase = openOrCreateDatabase("qldb.db", MODE_PRIVATE, null);

        try {
            String sql ="CREATE TABLE donvi (madv INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, tendv TEXT NOT NULL, email TEXT NOT NULL, website TEXT, logo TEXT, diachi TEXT NOT NULL, sdt TEXT NOT NULL, madvcha INTEGER NOT NULL)";
            String sql1 = "CREATE TABLE nhanvien (manv INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, hoten TEXT NOT NULL, chucvu TEXT, email TEXT NOT NULL, sdt TEXT NOT NULL, anhdd TEXT NOT NULL, madv INTEGER NOT NULL, FOREIGN KEY (madv) REFERENCES donvi(madv))";
            mydatabase.execSQL(sql);
            mydatabase.execSQL(sql1);
            Log.d("DBTEST", "Table created");
        }catch (Exception e){

            Log.e("Error", "Table da ton tai");
        }
        reload("donvi");
        // Bắt sự kiện đổi tab

    }
    private void loadContacts(int tabPosition) {
        String tableName = (tabPosition == 0) ? "donvi" : "nhanvien"; // Tên bảng trong database
        String query;
        if (tabPosition == 0) {
            // Truy vấn tên đơn vị
            query = "SELECT tendv FROM " + tableName + " ORDER BY tendv ASC";
        } else {
            // Truy vấn tên nhân viên và tên đơn vị
            query = "SELECT hoten, tendv FROM nhanvien nv " +
                    "JOIN donvi dv ON nv.madv = dv.madv " +
                    "ORDER BY hoten ASC";
        }
        Cursor cursor = mydatabase.rawQuery(query, null);

        List<Contact> contacts = new ArrayList<>();
        if (tabPosition == 0) {
            // Tạo đối tượng Contact cho đơn vị
            while (cursor.moveToNext()) {
                String tenDonVi = cursor.getString(cursor.getColumnIndexOrThrow("tendv"));
                contacts.add(new Contact(tenDonVi));
            }
        } else {
            // Tạo đối tượng Contact cho nhân viên
            while (cursor.moveToNext()) {
                String tenNhanVien = cursor.getString(cursor.getColumnIndexOrThrow("hoten"));
                String tenDonVi = cursor.getString(cursor.getColumnIndexOrThrow("tendv"));
                contacts.add(new Contact(tenNhanVien, tenDonVi));
            }
        }
        cursor.close();

        contactAdapter.clear();
        contactAdapter.addAll(contacts);
        contactAdapter.notifyDataSetChanged();
    }
    public  void reload(String tablename){
        mylist.clear();
        Cursor c = mydatabase.query(tablename,null,null,null,null,null,null);
        c.moveToNext();
        String data = "";
        while(!c.isAfterLast()){
            data = c.getString(0)+ " - "+ c.getString(1)+" - "+c.getString(2);
            c.moveToNext();
            mylist.add(data);
            Log.d("testt",data);

        }

        c.close();
        myadapter.notifyDataSetChanged();
    }
}