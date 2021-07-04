package com.example.baitest1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvContact;
    private EditText etSearch;
    Button btnAdd;
    ArrayList<String> Contacts;
    ArrayAdapter<String> lvAddapter;
    ArrayList<Contact_Dat> listUser;
    VuongToanDat_Adapter adapters;
    int selectedid = -1;
    VuongToanDat_Sqlite mysqlitedb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvContact = findViewById(R.id.lvContact);
        etSearch = findViewById(R.id.etSearch);
        btnAdd =findViewById(R.id.btn_add);
        registerForContextMenu(lvContact);


        mysqlitedb = new VuongToanDat_Sqlite(this,"ContactDB",null,1);
//        mysqlitedb.addContact(new Contact_Dat(1,"Nhung", "9999"));
//        mysqlitedb.addContact(new Contact_Dat(2,"Dung", "9999"));
//        mysqlitedb.addContact(new Contact_Dat(3,"Dai", "9999"));
//        mysqlitedb.addContact(new Contact_Dat(4,"Dat", "9999"));
        listUser = mysqlitedb.getAllContact();
        adapters = new VuongToanDat_Adapter(this, listUser);
        //lvAddapter=new ArrayAdapter<>( this,
        //      R.layout.support_simple_spinner_dropdown_item,Contacts);
        lvContact.setAdapter(adapters);
        lvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()  {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedid = position;

                return false;
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //lau du lieu contact gui ve
        Bundle bundle = data.getExtras();
        int id = bundle.getInt("Id");
        String name = bundle.getString("Name");
        String phone = bundle.getString("Phone");
        if (requestCode == 100 && resultCode == 200) {
            //dat vao data
            listUser.add(new Contact_Dat(id, name, phone));
            mysqlitedb.addContact(new Contact_Dat(id,name,phone));
        }
        if (resultCode == 201) {
            Toast.makeText(MainActivity.this, "finish edit" + id + name, Toast.LENGTH_SHORT).show();
            listUser.set(selectedid, new Contact_Dat(id, name, phone));
            adapters.notifyDataSetChanged();
            lvContact.setAdapter(adapters);
            Contact_Dat p = new Contact_Dat(id, name, phone);
            mysqlitedb.updateContact(id,p);
        }
        //cap nhat adapter
        adapters.notifyDataSetChanged();
    }
}