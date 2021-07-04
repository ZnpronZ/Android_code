package com.example.test1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class UpdateActivity extends AppCompatActivity {
    EditText txtID, txtGaDi, txtGaDen, txtGiaTien;
    Button btnUpdate, btnBack;
    RadioButton rdoKhuHoi, rdoMotChieu;


    Database db= new Database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actibity_update);
        map();
        Intent intent = getIntent();
        final int ma = intent.getIntExtra("ID", 0);
        String gaden = intent.getStringExtra("GADEN");
        String gadi = intent.getStringExtra("GADI");
        double giatien = intent.getDoubleExtra("GIATIEN", 1.1);
        boolean khuhoi = intent.getBooleanExtra("KHUHOI", true);
        txtGiaTien.setText(giatien+"");
        txtGaDen.setText(gaden);
        txtGaDi.setText(gadi);
        if(khuhoi==true)
            rdoKhuHoi.setChecked(true);
        else
            rdoMotChieu.setChecked(false);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean kh;
                if(rdoKhuHoi.isChecked())
                    kh=true;
                else
                    kh=false;
                Vetau vt = new Vetau(ma, txtGaDi.getText().toString(), txtGaDen.getText().toString(), Double.parseDouble(txtGiaTien.getText().toString()), kh);
                db.updateMonHoc(vt);
               Intent it = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(it);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(it);
            }
        });
    }
    private void map(){

        txtGaDen = (EditText)findViewById(R.id.tvGaDen);

        txtGaDi = (EditText)findViewById(R.id.tvGadi);
        txtGiaTien = (EditText)findViewById(R.id.tvDonGia);
        btnUpdate = (Button) findViewById(R.id.btnAdd);
        btnBack = (Button)findViewById(R.id.btnBack);
        rdoKhuHoi=(RadioButton)findViewById(R.id.rdoKhuHoi);
        rdoMotChieu=(RadioButton)findViewById(R.id.rdoMotChieu);



    }
}
