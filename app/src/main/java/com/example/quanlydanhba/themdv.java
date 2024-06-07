package com.example.quanlydanhba;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class themdv extends AppCompatActivity {
    ImageView btnThoat,imageView;
    private EditText edtTenDV, edtWebDV, edtSDTDV, edtEmailDV, edtDiaChi, edtDVC;

    private boolean isAllFieldsFilled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_themdv);
        imageView = findViewById(R.id.imageView);
        btnThoat = findViewById(R.id.btnThoat);
        edtTenDV = findViewById(R.id.edtTenDV);
        edtWebDV = findViewById(R.id.edtWebDV);
        edtSDTDV = findViewById(R.id.edtSDTDV);
        edtEmailDV = findViewById(R.id.edtEmailDV);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtDVC = findViewById(R.id.edtDVC);

        // Set a TextWatcher for each EditText
        edtTenDV.addTextChangedListener(watcher);
        edtWebDV.addTextChangedListener(watcher);
        edtSDTDV.addTextChangedListener(watcher);
        edtEmailDV.addTextChangedListener(watcher);
        edtDiaChi.addTextChangedListener(watcher);
        edtDVC.addTextChangedListener(watcher);

        // Initial check for all EditText fields
        checkFieldsForEmptyValues();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // TextWatcher to listen for text changes in EditText fields


        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(themdv.this, MainActivity.class);
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
        String tenDV = edtTenDV.getText().toString().trim();
        String webDV = edtWebDV.getText().toString().trim();
        String sdtDV = edtSDTDV.getText().toString().trim();
        String emailDV = edtEmailDV.getText().toString().trim();
        String diaChi = edtDiaChi.getText().toString().trim();
        String maDVC = edtDVC.getText().toString().trim();

        // Check if all EditText fields are filled
        isAllFieldsFilled = !tenDV.isEmpty() && !webDV.isEmpty() && !sdtDV.isEmpty() && !emailDV.isEmpty() && !diaChi.isEmpty() && !maDVC.isEmpty();

        // If all fields are filled, change the icon of imageView to ic_done_green
        if (isAllFieldsFilled) {
            imageView.setImageResource(R.drawable.ic_done_green);
        } else {
            imageView.setImageResource(R.drawable.ic_done); // Change back to the original icon
        }
    }
}