package com.example.baitap1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddContact extends AppCompatActivity {
    EditText etId, etNames, etPhone;
    Button btnAdd, btnCancel;
    CheckBox ckAdd;
    Boolean Status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add);
        etId = findViewById(R.id.editTextID);
        etNames = findViewById(R.id.editTextName);
        etPhone = findViewById(R.id.editTextPhoneNumer);
        btnCancel = findViewById(R.id.btnCal);
        btnAdd = findViewById(R.id.btn_Add);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            int id = bundle.getInt("Id");
            String name = bundle.getString("Name");
            String phone = bundle.getString("Phone");
            Boolean status = bundle.getBoolean("Status");
            etId.setText(String.valueOf(id));
            etNames.setText(name);
            etPhone.setText(phone);

            Status=status;
            if(status)
                ckAdd.setChecked(true);
            else
                ckAdd.setChecked(false);
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
                bundle.putBoolean("Status",Status);
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
                Intent intent = new Intent(AddContact.this, MainActivity.class);
                startActivityForResult(intent, 100);
            }
        });
    }
}