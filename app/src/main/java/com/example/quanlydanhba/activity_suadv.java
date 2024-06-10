package com.example.quanlydanhba;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_suadv extends AppCompatActivity {
    EditText tendv, email, sdt, website, diachi,madvcha;
    ImageView imv,btnLuu,btnThoat;
    int madv;
    String newTendv, newLogo,newSdt, newEmail, newWebsite,  newDiachi,  newMadvcha;
    String logo="";
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_suadv);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tendv = findViewById(R.id.edtTenDV);
        email = findViewById(R.id.edtEmailDV);
        sdt = findViewById(R.id.edtSDTDV);
        website = findViewById(R.id.edtWebDV);
        diachi = findViewById(R.id.edtDiaChi);
        madvcha = findViewById(R.id.edtDVC);
        btnLuu = findViewById(R.id.btnLuu);
        btnThoat = findViewById(R.id.btnThoat);

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
                intent = new Intent(activity_suadv.this, activity_xemdv.class);
                intent.putExtra("donviId", id);
                startActivity(intent);
            }
        });
       reload();

    }

    private void reload(){
        int donviId = getIntent().getIntExtra("donviId", -1); // Sửa tên khóa Intent
        id = donviId;
        if (donviId != -1) {
            DbHelper dbHelper = new DbHelper(this);
            Cursor cursor = dbHelper.getDonViById(donviId);

            if (cursor.moveToFirst()) { // Di chuyển Cursor đến vị trí đầu tiên
                // Lấy dữ liệu từ Cursor và hiển thị lên TextView
                // Thay thế bằng ID TextView của bạn
                tendv.setText(cursor.getString(cursor.getColumnIndex(Constants.dv_tendv)));


                email.setText(cursor.getString(cursor.getColumnIndex(Constants.dv_email)));


                sdt.setText(cursor.getString(cursor.getColumnIndex(Constants.dv_sdt)));


                website.setText(cursor.getString(cursor.getColumnIndex(Constants.dv_website)));


                diachi.setText(cursor.getString(cursor.getColumnIndex(Constants.dv_diachi)));

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
            newTendv = tendv.getText().toString();
            newEmail = email.getText().toString();
            newSdt = sdt.getText().toString();
            newWebsite = website.getText().toString();
            newDiachi = diachi.getText().toString();
            newMadvcha = madvcha.getText().toString();
            boolean isUpdated = dbHelper.updateDonViById(id, newTendv,logo,  newSdt,newEmail, newWebsite, newDiachi, newMadvcha);
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