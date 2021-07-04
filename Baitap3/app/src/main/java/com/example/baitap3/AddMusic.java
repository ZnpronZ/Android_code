package com.example.baitap3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddMusic extends AppCompatActivity {
    EditText etId, etBaiHat, etPhut,etCaSi;
    Button btnAdd, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_music);
        etId = findViewById(R.id.tvId);
        etBaiHat = findViewById(R.id.tvBaiHat);
        etPhut = findViewById(R.id.tvPhut);
        etCaSi = findViewById(R.id.tvCaSi);
        btnCancel = findViewById(R.id.btnCancel);
        btnAdd = findViewById(R.id.btnAdd);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            int id = bundle.getInt("Id");
            String baihat = bundle.getString("BaiHat");
            Double phut = bundle.getDouble("Phut");
            String casi = bundle.getString("CaSi");
            etId.setText(String.valueOf(id));
            etBaiHat.setText(baihat);
            etPhut.setText(String.valueOf(phut));
            etCaSi.setText(casi);
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
                bundle.putString("BaiHat", etBaiHat.getText().toString());
                bundle.putDouble("Phut", Double.parseDouble(etPhut.getText().toString()));
                bundle.putString("CaSi", etCaSi.getText().toString());
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

    }
}