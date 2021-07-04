package com.example.bai2_cuavinh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivityAdd extends AppCompatActivity {
    Button btnOK;
    EditText txtID,txtName,txtMota;
    Switch aSwitchThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add);
        btnOK=(Button) findViewById(R.id.buttonok);
        txtID=(EditText) findViewById(R.id.idThem);
        txtMota=(EditText)findViewById(R.id.motaThem);
        txtName=(EditText) findViewById(R.id.nameThem);
        aSwitchThem=(Switch)findViewById(R.id.switchThem);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivityAdd.this,MainActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("Id", Integer.parseInt(txtID.getText().toString()));
                bundle.putString("Name",txtName.getText().toString());
                bundle.putString("MoTa",txtMota.getText().toString());
                bundle.putBoolean("status",aSwitchThem.isChecked());
                intent.putExtras(bundle);
                setResult(200,intent);
                finish();
            }
        });
    }
}