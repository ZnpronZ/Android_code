package com.example.bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUser extends AppCompatActivity {
    EditText etId, etNames, etPhone;
    Button btnAdd, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        etId = findViewById(R.id.tvId);
        etNames = findViewById(R.id.tvNames);
        etPhone = findViewById(R.id.tvPhone);
        btnCancel = findViewById(R.id.btnCancel);
        btnAdd = findViewById(R.id.btnAdd);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            int id = bundle.getInt("Id");
            String name = bundle.getString("Name");
            String phone = bundle.getString("Phone");
            etId.setText(String.valueOf(id));
            etNames.setText(name);
            etPhone.setText(phone);
            btnAdd.setText("Edit");
            Toast.makeText(this, "edit name" + id, Toast.LENGTH_SHORT).show();

        }


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tao intent
                Intent intent = new Intent();
                //tao bundle la doi tuong de chua du lieu
                Bundle bundle = new Bundle();
                //bundle hoat dong nu 1 java map cac phan tu phan biet theo key
                //cac ham put trong do la kieu du lieu
                bundle.putInt("Id", Integer.parseInt(etId.getText().toString()));
                bundle.putString("Name", etNames.getText().toString());
                bundle.putString("Phone", etPhone.getText().toString());
                //co the dat doi tuong len bang ham lutserilizable
                //dat bundle len intent
                intent.putExtras(bundle);
                //tra ve ham set result tham so thu nhat quan li phien
                //tham so thu 2 chua du lieu gui ve
                setResult(200, intent);
                if (btnAdd.getText() == "Edit") {
                    setResult(201, intent);
                    finish();
                }
                finish();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddUser.this, MainActivity.class);
                startActivityForResult(intent, 100);
            }
        });
    }
}