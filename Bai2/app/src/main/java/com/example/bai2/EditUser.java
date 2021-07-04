package com.example.bai2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditUser extends AppCompatActivity {
   EditText editName;
   EditText editPhone;
   Button btnSave;
   Button btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        editName = findViewById(R.id.edName) ;
        editPhone = findViewById(R.id.edPhone);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnedCancel);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            int id = bundle.getInt("id");
            String name = bundle.getString("name");
            String phone = bundle.getString("phone");
            editName.setText(name);
            editPhone.setText(phone);
            Toast.makeText(this,"edit name"+name ,Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
}