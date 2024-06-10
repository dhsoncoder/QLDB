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

public class activity_xemdv extends AppCompatActivity {
    ImageView btnThoat, btnSua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xemdv);
        btnThoat = findViewById(R.id.btnThoat);
        btnSua = findViewById(R.id.btnSua);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        int donviId = getIntent().getIntExtra("manv", -1); // Sửa tên khóa Intent
        if (donviId != -1) {
            DbHelper dbHelper = new DbHelper(this);
            Cursor cursor = dbHelper.getDonViById(donviId);

            if (cursor.moveToFirst()) { // Di chuyển Cursor đến vị trí đầu tiên
                // Lấy dữ liệu từ Cursor và hiển thị lên TextView
                TextView tenDonViTextView = findViewById(R.id.tvDV); // Thay thế bằng ID TextView của bạn
                tenDonViTextView.setText(cursor.getString(cursor.getColumnIndex(Constants.dv_tendv)));

                TextView email = findViewById(R.id.tvEmail);
                email.setText(cursor.getString(cursor.getColumnIndex(Constants.dv_email)));

                TextView dienThoai = findViewById(R.id.tvsdt);
                dienThoai.setText(cursor.getString(cursor.getColumnIndex(Constants.dv_sdt)));

                TextView website = findViewById(R.id.tvWebsite);
                website.setText(cursor.getString(cursor.getColumnIndex(Constants.dv_website)));

                TextView diaChi = findViewById(R.id.tvDC);
                diaChi.setText(cursor.getString(cursor.getColumnIndex(Constants.dv_diachi)));

                // Thay thế bằng ID TextView của bạn
                // ... Hiển thị các thông tin khác tương tự
            }

            cursor.close(); // Đóng Cursor sau khi sử dụng
            dbHelper.close();
        } else {
            // Xử lý trường hợp không nhận được donviId
        }

        btnThoat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    intent = new Intent(activity_xemdv.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            btnSua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    intent = new Intent(activity_xemdv.this, suanv.class);
                    startActivity(intent);
                }
            });
        }
    }

