package com.example.quanlydanhba;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class themnv extends AppCompatActivity {
    ImageView btnThoat,btnLuu;
    EditText edtTenNV, edtChucVu, edtSDT, edtEmail;
    Spinner spinner;
    private boolean isAllFieldsFilled = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_themnv);
        btnThoat = findViewById(R.id.btnThoat);
        btnLuu = findViewById(R.id.btnLuu);
        edtTenNV = findViewById(R.id.edtTenNV);
        edtChucVu = findViewById(R.id.edtChucVu);
        edtSDT = findViewById(R.id.edtSDT);
        edtEmail = findViewById(R.id.edtEmail);
        spinner = findViewById(R.id.spinner);

        // Set a TextWatcher for each EditText
        edtTenNV.addTextChangedListener(watcher);
        edtChucVu.addTextChangedListener(watcher);
        edtSDT.addTextChangedListener(watcher);
        edtEmail.addTextChangedListener(watcher);


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
}