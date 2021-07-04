package com.example.baitest2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    ArrayList<String> Contacts;
    ArrayAdapter<String> lvAddapter;
    ArrayList<Contact_181201649> listUser;
    VuongToanDat_Adapter adapters;
    int selectedid = -1;
    VuongToanDat_Sqlite mysqlitedb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvContact = findViewById(R.id.lvContact);
        etSearch = findViewById(R.id.etSearch);
        registerForContextMenu(lvContact);


        mysqlitedb = new VuongToanDat_Sqlite(this,"ContactDB",null,1);
//        mysqlitedb.addContact(new Contact_181201649(1,"Nhung", "9999"));
//        mysqlitedb.addContact(new Contact_181201649(2,"Dung", "9999"));
//        mysqlitedb.addContact(new Contact_181201649(3,"Dai", "9999"));
//        mysqlitedb.addContact(new Contact_181201649(4,"Hung", "9999"));
//        mysqlitedb.addContact(new Contact_181201649(5,"Dat", "9999"));
//        mysqlitedb.addContact(new Contact_181201649(6,"Hanh", "9999"));
//        mysqlitedb.addContact(new Contact_181201649(7,"Hanh", "9999"));
//        mysqlitedb.addContact(new Contact_181201649(8,"Hanh", "9999"));
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


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.contextmenu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Contact_181201649 names = ((Contact_181201649) lvContact.getAdapter().getItem(selectedid));
        switch (item.getItemId()) {

            case R.id.itemdelete:
                AlertDialog.Builder dialogXacNhan=new AlertDialog.Builder(MainActivity.this);
                dialogXacNhan.setMessage("Vương Toàn Đạt want to delete ?");
                dialogXacNhan.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Delete" + names.getName(), Toast.LENGTH_SHORT).show();

                        Contact_181201649 user = listUser.get(selectedid);
                        mysqlitedb.deleteContact(user.getId());
                        listUser.remove(selectedid);
                        adapters.notifyDataSetChanged();
                        lvContact.setAdapter(adapters);
                    }
                });
                dialogXacNhan.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, listUser.size()+"", Toast.LENGTH_SHORT).show();
                    }
                });
                dialogXacNhan.show();

                break;
            case R.id.itemedit:

                Intent intent = new Intent(MainActivity.this, AddUser.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Id", listUser.get(selectedid).getId());
                bundle.putString("Name", listUser.get(selectedid).getName());
                bundle.putString("Phone", listUser.get(selectedid).getPhone());
                intent.putExtras(bundle);
                startActivityForResult(intent, 100);
                Toast.makeText(MainActivity.this, "Edit", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
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
            listUser.add(new Contact_181201649(id, name, phone));
            mysqlitedb.addContact(new Contact_181201649(id,name,phone));
        }
        if (resultCode == 201) {
            Toast.makeText(MainActivity.this, "finish edit" + id + name, Toast.LENGTH_SHORT).show();
            listUser.set(selectedid, new Contact_181201649(id, name, phone));
            adapters.notifyDataSetChanged();
            lvContact.setAdapter(adapters);
            Contact_181201649 p = new Contact_181201649(id, name, phone);
            mysqlitedb.updateContact(id,p);
        }
        //cap nhat adapter
        adapters.notifyDataSetChanged();
    }
}