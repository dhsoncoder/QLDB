package com.example.quanlydanhba;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_xemnv extends AppCompatActivity {
    ImageView btnThoat, btnSua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xemnv);
        btnThoat = findViewById(R.id.btnThoat);
        btnSua = findViewById(R.id.btnSua);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        int nhanvienId = getIntent().getIntExtra("nhanvienId", -1); // -1 là giá trị mặc định nếu không tìm thấy
        if (nhanvienId != -1) {
            // Tiếp tục xử lý với donviId
            DbHelper dbHelper = new DbHelper(this);
            Cursor cursor = dbHelper.getNhanVienById(nhanvienId); // Tạo hàm getNhanVienById trong DbHelper

            if (cursor.moveToFirst()) {
                String tennv = cursor.getString(cursor.getColumnIndex(Constants.nv_tennv));
                String chucvu = cursor.getString(cursor.getColumnIndex(Constants.nv_chucvu));
                String donvi = cursor.getString(cursor.getColumnIndex(Constants.nv_madv));

                // ... Lấy các thông tin khác

                TextView tvTenNV = findViewById(R.id.tvTenNV); // Thay R.id.tvTenNV bằng ID thực tế
                tvTenNV.setText(tennv);

                TextView tvChucVu = findViewById(R.id.tvChucVu); // Thay R.id.tvChucVu bằng ID thực tế
                tvChucVu.setText(chucvu);


                TextView tvDV = findViewById(R.id.tvDV); // Thay R.id.tvChucVu bằng ID thực tế
                tvDV.setText(donvi);
                // ... Hiển thị các thông tin khác
            } else {
                // Xử lý trường hợp không nhận được donviId (ví dụ: hiển thị thông báo lỗi)
            }
            btnThoat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    intent = new Intent(activity_xemnv.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            btnSua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    intent = new Intent(activity_xemnv.this, suanv.class);
                    startActivity(intent);
                }
            });
        }
    }
}