package com.example.baitap2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvContact;
    private EditText etSearch;

    ArrayList<User> listUser;
    MainAdapter adapters;
    Button btndel,btnAdd;
    Boolean status;
    UserDB mysqlitedb;
    int selectedid = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd= findViewById(R.id.btnAdd);
        btndel = findViewById(R.id.btnDel);
        lvContact = findViewById(R.id.lvContact);
        etSearch = findViewById(R.id.etSearch);


//        listUser = new ArrayList<>();
//        listUser.add(new User(1, "Bóng đèn", "Điện 220v", "",true));
//        listUser.add(new User(2, "Bình nóng", "Công suất 1000w", "",true));
//        listUser.add(new User(3, "Điều hòa", "Có inverter", "",false));

        mysqlitedb = new UserDB(this,"UserDB",null,1);
        mysqlitedb.addContact(new User(1, "Bóng đèn", "Điện 220v", "",true));
        mysqlitedb.addContact(new User(2, "Bình nóng", "Công suất 1000w", "",true));
        mysqlitedb.addContact(new User(3, "Điều hòa", "Có inverter", "",false));

        listUser= mysqlitedb.getAllContact();
        adapters=new MainAdapter(this,R.layout.contact_layout,listUser);
        lvContact.setAdapter(adapters);





        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Toast.makeText(MainActivity.this,String.valueOf(adapters.getCount()),Toast.LENGTH_LONG).show();
                for (int i=0;i<adapters.getCount();i++)
                {
                    if(!listUser.get(i).isStatus())
                    {
                        listUser.remove(i);
                        adapters.notifyDataSetChanged();
                        lvContact.setAdapter(adapters);
                    }
                }
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddUser.class);
                startActivityForResult(intent, 100);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //lau du lieu contact gui ve
        Bundle bundle = data.getExtras();
        int id = bundle.getInt("Id");
        String name = bundle.getString("Name");
        String des = bundle.getString("Des");
        Boolean status = bundle.getBoolean("Status");
        if (requestCode == 100 && resultCode == 200) {
            //dat vao data
            listUser.add(new User(id, name, des, "",status));

        }
        if (resultCode == 201) {
            Toast.makeText(MainActivity.this, "finish edit" + id + name, Toast.LENGTH_SHORT).show();
            listUser.set(selectedid, new User(id, name, des, "",status));
            adapters.notifyDataSetChanged();
            lvContact.setAdapter(adapters);

        }
        //cap nhat adapter
        adapters.notifyDataSetChanged();
    }


}