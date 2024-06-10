package com.example.quanlydanhba;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
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

public class themnv extends AppCompatActivity {
    ImageView btnThoat,btnLuu;
    EditText edtTenNV, edtChucVu, edtSDT, edtEmail,edtDonVi;
    Spinner spinnerDonVi;
    List<String> donViNames;
    List<Integer> donViIds;
    CardView btnThemAnh;
    private boolean isAllFieldsFilled = false;
    private String tenNV, chucVu, sdt, email,urlanh;
    int donVi;
    private DbHelper dbHelper;
    private static final int REQUEST_CODE_PICK_IMAGE = 1;
    private ImageView imgHinhAnh;
    private Uri selectedImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_themnv);

        dbHelper = new DbHelper(this);

        btnThoat = findViewById(R.id.btnThoat);
        btnLuu = findViewById(R.id.btnLuu);
        edtTenNV = findViewById(R.id.edtTenNV);
        edtChucVu = findViewById(R.id.edtChucVu);
        edtSDT = findViewById(R.id.edtSDT);
        edtEmail = findViewById(R.id.edtEmail);
        spinnerDonVi = findViewById(R.id.spinner);
        btnThemAnh = findViewById(R.id.btnThemAnh);
        // Set a TextWatcher for each EditText
        edtTenNV.addTextChangedListener(watcher);
        edtChucVu.addTextChangedListener(watcher);
        edtSDT.addTextChangedListener(watcher);
        edtEmail.addTextChangedListener(watcher);
        imgHinhAnh = findViewById(R.id.imgHinhAnh);

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

        // Initial check for all EditText fields
        checkFieldsForEmptyValues();
        // TextWatcher to listen for text changes in EditText fields


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(themnv.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        btnThemAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to open image gallery
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.getData(); // Get the URI of the selected image
            if (selectedImageUri != null) {
                urlanh = selectedImageUri.getPath();
                // Set the selected image to the ImageView
                imgHinhAnh.setImageURI(selectedImageUri);

            }
        }
    }

    private void saveData() {
        tenNV = edtTenNV.getText().toString().trim();
        email = edtEmail.getText().toString().trim();
        sdt = edtSDT.getText().toString().trim();
        chucVu = edtChucVu.getText().toString().trim();
        int donVi = donViIds.get(spinnerDonVi.getSelectedItemPosition());
        String imageUriString = selectedImageUri != null ? selectedImageUri.toString() : "";
        long id = dbHelper.insertNV(
                "" + imageUriString, // Use the selected image URI here
                "" + tenNV,
                "" + sdt,
                "" + email,
                "" + chucVu,
                "" + donVi);

        Toast.makeText(getApplicationContext(), "Inserted "+id, Toast.LENGTH_SHORT).show();

    }
    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            checkFieldsForEmptyValues();
        }
    };
    // Method to check if all EditText fields are filled
    private void checkFieldsForEmptyValues() {
        String tenNV = edtTenNV.getText().toString().trim();
        String chucVu = edtChucVu.getText().toString().trim();
        String sdt = edtSDT.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();

        // Check if all EditText fields are filled
        isAllFieldsFilled = !tenNV.isEmpty() && !chucVu.isEmpty() && !sdt.isEmpty() && !email.isEmpty();

        // If all fields are filled, change the icon of btnLuu to ic_done_green
        if (isAllFieldsFilled) {
            btnLuu.setImageResource(R.drawable.ic_done_green);
        } else {
            btnLuu.setImageResource(R.drawable.ic_done); // Change back to the original icon
        }
    }
    private String getRealPathFromURI(Uri contentUri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String imagePath = cursor.getString(column_index);
            cursor.close();
            return imagePath;
        }
        return null;
    }
}