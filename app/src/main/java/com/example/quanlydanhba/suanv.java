package com.example.quanlydanhba;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class suanv extends AppCompatActivity {
    ImageView btnThoat,btnLuu;
    EditText edtTenNV, edtChucVu, edtSDT, edtEmail,edtDonVi;
    Spinner spinnerDonVi;
    List<String> donViNames;
    List<Integer> donViIds;
    CardView btnThemAnh;
    private boolean isAllFieldsFilled = false;
    private DbHelper dbHelper;
    int madv;
    String newTenNV,newSdt, newEmail, newChucVu;
    String logo="";
    int id,newDonVi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_suanv);


        btnThoat = findViewById(R.id.btnThoat);
        btnLuu = findViewById(R.id.btnLuu);
        edtTenNV = findViewById(R.id.edtTenNV);
        edtChucVu = findViewById(R.id.edtChucVu);
        edtSDT = findViewById(R.id.edtSDT);
        edtEmail = findViewById(R.id.edtEmail);
        spinnerDonVi = findViewById(R.id.spinner);
        btnThemAnh = findViewById(R.id.btnThemAnh);
        dbHelper = new DbHelper(this);
        Cursor cursor = dbHelper.getDonViSpiner();
        donViNames = new ArrayList<>();
        donViIds = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(Constants.dv_tendv)); // Lấy tên đơn vị
                donViNames.add(name);
                int id = cursor.getInt(cursor.getColumnIndex(Constants.dv_id)); // Lấy mã đơn vị
                donViIds.add(id);
            } while (cursor.moveToNext());
        }else{
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        dbHelper.close();
        // Tạo ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, donViNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Gán ArrayAdapter cho Spinner
        spinnerDonVi.setAdapter(adapter);
        // Set a TextWatcher for each EditText

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(suanv.this, activity_xemnv.class);
                intent.putExtra("nhanvienId", id);
                startActivity(intent);
            }
        });
        reload();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void reload(){
        int nhanvienId = getIntent().getIntExtra("nhanvienId", -1); // Sửa tên khóa Intent
        id = nhanvienId;
        if (nhanvienId != -1) {
            DbHelper dbHelper = new DbHelper(this);
            Cursor cursor = dbHelper.getNhanVienById(nhanvienId);

            if (cursor.moveToFirst()) { // Di chuyển Cursor đến vị trí đầu tiên
                // Lấy dữ liệu từ Cursor và hiển thị lên TextView
                // Thay thế bằng ID TextView của bạn
                edtTenNV.setText(cursor.getString(cursor.getColumnIndex(Constants.nv_tennv)));
                edtChucVu.setText(cursor.getString(cursor.getColumnIndex(Constants.nv_chucvu)));
                int donvi = cursor.getInt(cursor.getColumnIndex("donvi_id"));
                edtSDT.setText(cursor.getString(cursor.getColumnIndex(Constants.nv_sdt)));
                edtEmail.setText(cursor.getString(cursor.getColumnIndex(Constants.nv_email)));
                spinnerDonVi.setSelection(cursor.getInt(donvi));

                // Thay thế bằng ID TextView của bạn
                // ... Hiển thị các thông tin khác tương tự
            }

            cursor.close(); // Đóng Cursor sau khi sử dụng
            dbHelper.close();
        } else {
            // Xử lý trường hợp không nhận được donviId
        }
    }
    private void saveData() {
        if (id != -1) {
            DbHelper dbHelper = new DbHelper(this);
            Cursor cursor = dbHelper.getDonViById(id);
            newTenNV = edtTenNV.getText().toString();
            newEmail = edtEmail.getText().toString();
            newSdt = edtSDT.getText().toString();
            newChucVu = edtChucVu.getText().toString();
            newDonVi = donViIds.get(spinnerDonVi.getSelectedItemPosition());
            boolean isUpdated = dbHelper.updateNV(id, newTenNV,newChucVu, newEmail,newSdt, "", newDonVi);
            if (isUpdated) {
                Toast.makeText(this, "Cập nhat thành oong", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Cập nhat that bai", Toast.LENGTH_SHORT).show();
            }
            cursor.close();


            // Đóng Cursor sau khi sử dụng
            dbHelper.close();}
        else {
            // Xử lý trường hợp không nhận được donviId
        }
    }
}
