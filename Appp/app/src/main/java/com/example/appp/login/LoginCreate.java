package com.example.appp.login;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appp.R;
import com.example.appp.ui.Home;

public class LoginCreate extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btSubmit;

    DatabaseLogin database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database= new DatabaseLogin(this,"DBLogin",null,1);
        etUsername =findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btSubmit = findViewById(R.id.bt_submit);


        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = etUsername.getText().toString();
                String pass = etPassword.getText().toString();
                Cursor cursor = database.GetData("select * from Login where Username='"+user+"' and Password='"+pass+"'");
                 if(cursor.getCount()>0)
                 {

                     Intent intent = new Intent(LoginCreate.this, Home.class);
                     startActivity(intent);

                 }
                 else
                 {
                     Toast.makeText(getApplicationContext(),"Invalid Username & Password",Toast.LENGTH_LONG).show();
                 }



            }
        });
    }

}