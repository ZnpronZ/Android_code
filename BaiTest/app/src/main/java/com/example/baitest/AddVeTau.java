package com.example.baitest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddVeTau extends AppCompatActivity {
    EditText etGia, etGaDen, etGaDi;
    RadioButton RB_KH,RB_MC;
    Button btnAdd, btnCancel;
    Boolean status;
// dành cho edit
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vetau);
        etGaDi = findViewById(R.id.textviewGaDi);
        etGaDen = findViewById(R.id.textviewGaDen);
        etGia = findViewById(R.id.textviewGia);
        RB_MC= findViewById(R.id.radioButton_MotChieu);
        RB_KH=findViewById(R.id.radioButton_KhuHoi);
        btnCancel = findViewById(R.id.btnCancel);
        btnAdd = findViewById(R.id.btnAdd);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String gadi = bundle.getString("GaDi");
            String gaden = bundle.getString("GaDen");
            Float gia = bundle.getFloat("Gia");
            Boolean khuhoi =bundle.getBoolean("KhuHoi");
            etGaDi.setText(gadi);
            etGaDen.setText(gaden);
            etGia.setText(String.valueOf(gia));
            status=khuhoi;
            if(khuhoi)
            {
                        RB_KH.setChecked(true);
                        RB_MC.setChecked(false);
            }
            else
            {
                        RB_MC.setChecked(true);
                        RB_KH.setChecked(false);
            }
            btnAdd.setText("Edit");

        }


        RB_MC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status=false;
                RB_KH.setChecked(false);
            }
        });
        RB_KH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status=true;
                RB_MC.setChecked(false);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tao intent
                Intent intent = new Intent();
                //tao bundle la doi tuong de chua du lieu
                Bundle bundle = new Bundle();
                //bundle hoat dong nu 1 java map cac phan tu phan biet theo key
                //cac ham put trong do la kieu du lieu
                bundle.putString("GaDi", etGaDi.getText().toString());
                bundle.putString("GaDen", etGaDen.getText().toString());
                bundle.putFloat("Gia", Float.parseFloat(etGia.getText().toString()));
                bundle.putBoolean("KhuHoi",status);
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
