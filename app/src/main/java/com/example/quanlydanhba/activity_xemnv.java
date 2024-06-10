package com.example.quanlydanhba;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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
    int id;
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
        reload();
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
                    intent.putExtra("nhanvienId", id);
                    startActivity(intent);
                }
            });
        }
        private void reload(){
            int nhanvienId = getIntent().getIntExtra("nhanvienId", -1);
            id = nhanvienId;
            // -1 là giá trị mặc định nếu không tìm thấy
            if (nhanvienId != -1) {
                // Tiếp tục xử lý với donviId
                DbHelper dbHelper = new DbHelper(this);
                Cursor cursor = dbHelper.getNhanVienById(nhanvienId); // Tạo hàm getNhanVienById trong DbHelper

                if (cursor.moveToFirst()) {
                    String tennv = cursor.getString(cursor.getColumnIndex(Constants.nv_tennv));
                    String chucvu = cursor.getString(cursor.getColumnIndex(Constants.nv_chucvu));
                    String donvi = cursor.getString(cursor.getColumnIndex("donvi_ten"));
                    String anhdd = cursor.getString(cursor.getColumnIndex(Constants.nv_anhdd));
                    String sdt = cursor.getString(cursor.getColumnIndex(Constants.nv_sdt));
                    String email = cursor.getString(cursor.getColumnIndex(Constants.nv_email));

                    // ... Lấy các thông tin khác
                    TextView tvSDT = findViewById(R.id.tvsdt);
                    tvSDT.setText(sdt); // Thay R.id.tvSDT bằng ID thực tế

                    TextView tvTenNV = findViewById(R.id.tvTenNV); // Thay R.id.tvTenNV bằng ID thực tế
                    tvTenNV.setText(tennv);

                    TextView tvChucVu = findViewById(R.id.tvChucVu); // Thay R.id.tvChucVu bằng ID thực tế
                    tvChucVu.setText(chucvu);

                    TextView tvDV = findViewById(R.id.tvDV); // Thay R.id.tvChucVu bằng ID thực tế
                    tvDV.setText(donvi);
                    TextView tvEmail = findViewById(R.id.tvEmail); // Thay R.id.tvChucVu bằng ID thực tế
                    tvEmail.setText(email);

                    ImageView imgAnhNV = findViewById(R.id.imgHinhAnh);
                    imgAnhNV.setImageURI(Uri.parse(anhdd));
                    // ... Hiển thị các thông tin khác
                } else {
                    // Xử lý trường hợp không nhận được donviId (ví dụ: hiển thị thông báo lỗi)
                }
        }
    }
}