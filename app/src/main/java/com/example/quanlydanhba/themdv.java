package com.example.quanlydanhba;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class themdv extends AppCompatActivity {
    ImageView btnThoat,btnLuu;
    private EditText edtTenDV, edtWebDV, edtSDTDV, edtEmailDV, edtDiaChi, edtDVC;
    private int madv;
    private String[] cameraPermission;
    private String[] storagePermission;

    private boolean isAllFieldsFilled = false;

    //khai bao cac bien cua object
    private String tendv,email,website,logo,diachi,sdt,madvcha;

    //permission constant
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 200;
    private static final int IMAGE_FROM_GALLERY_CODE = 300;
    private static final int IMAGE_FROM_CAMERA_CODE = 400;
    // string array of permission

    //Image uri var
    private Uri imageUri;

    //database helper
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_themdv);

        //init db
        dbHelper = new DbHelper(this);

        btnLuu = findViewById(R.id.btnLuu);
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
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }
    private void saveData() {
        tendv = edtTenDV.getText().toString().trim();
        email = edtEmailDV.getText().toString().trim();
        website = edtWebDV.getText().toString().trim();
        diachi = edtDiaChi.getText().toString().trim();
        sdt = edtSDTDV.getText().toString().trim();
        madvcha = edtDVC.getText().toString().trim();
        long id =  dbHelper.insertContact(
                ""+imageUri,
                ""+tendv,
                ""+sdt,
                ""+email,
                ""+website,
                ""+diachi,
                ""+madvcha);
        Toast.makeText(getApplicationContext(), "Inserted thanh cong lien lac voi id "+id, Toast.LENGTH_SHORT).show();

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
            btnLuu.setImageResource(R.drawable.ic_done_green);
        } else {
            btnLuu.setImageResource(R.drawable.ic_done); // Change back to the original icon
        }
    }
}