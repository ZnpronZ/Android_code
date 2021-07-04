package com.example.baitap2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddUser extends AppCompatActivity {
    EditText etId, etNames, etDes;
    Button btnAdd, btnCancel;
    Switch switchAdd;
    boolean Status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);
        etId = findViewById(R.id.tvId);
        etNames = findViewById(R.id.tvNames);
        etDes = findViewById(R.id.tvAdd_Description);
        btnCancel = findViewById(R.id.btnCancel);
        btnAdd = findViewById(R.id.btnAdd);
        switchAdd=findViewById(R.id.switchAdd);


        switchAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchAdd.isChecked())
                {
                    Status=true;

                }
                else {
                    Status = false;
                }
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
                bundle.putInt("Id", Integer.parseInt(etId.getText().toString()));
                bundle.putString("Name", etNames.getText().toString());
                bundle.putString("Des", etDes.getText().toString());
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

    }

}
