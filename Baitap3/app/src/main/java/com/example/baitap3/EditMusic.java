package com.example.baitap3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditMusic extends AppCompatActivity {
    EditText editBaiHat;
    EditText editPhut;
    EditText editCaSi;
    Button btnSave;
    Button btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_music);
        editBaiHat = findViewById(R.id.edBaiHat) ;
        editPhut = findViewById(R.id.edPhut);
        editCaSi = findViewById(R.id.edCaSi);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnedCancel);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            int id = bundle.getInt("id");
            String baihat = bundle.getString("baihat");
            Double phut = bundle.getDouble("phut");
            String casi = bundle.getString("casi");
            editBaiHat.setText(baihat);
            editPhut.setText(phut.toString());
            editCaSi.setText(casi);
            Toast.makeText(this,"edit name"+ baihat ,Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
}
