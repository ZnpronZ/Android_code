package com.example.bai2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvContact;
    private EditText etSearch;
    ArrayList<String> Contacts;
    ArrayAdapter<String> lvAddapter;
    ArrayList<User> listUser;
    MainAdapter adapters;
    int selectedid = -1;
   ContactDB mysqlitedb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//tham chieu toi edittext voi listview
        lvContact = findViewById(R.id.lvContact);
        etSearch = findViewById(R.id.etSearch);
        registerForContextMenu(lvContact);

        //tao
//        listUser = new ArrayList<>();
//        listUser.add(new User(1, "Phi", "12345678", ""));
//        listUser.add(new User(2, "Phi 1", "12345678", ""));
//        listUser.add(new User(3, "Phi 2", "12345678", ""));
        mysqlitedb = new ContactDB(this,"ContactDB",null,1);
        mysqlitedb.addContact(new User(1,"Phi 1", "9999",""));
        mysqlitedb.addContact(new User(2,"Phi 2", "9999",""));
        mysqlitedb.addContact(new User(3,"Phi 3", "9999",""));
        listUser = mysqlitedb.getAllContact();
        adapters = new MainAdapter(this, listUser);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSort:
                Toast.makeText(MainActivity.this, "Sort", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuAdd:
                Intent intent = new Intent(MainActivity.this, AddUser.class);

                startActivityForResult(intent, 100);
                // Toast.makeText(MainActivity.this,"Add",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.contextmenuitem, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        User names = ((User) lvContact.getAdapter().getItem(selectedid));
        switch (item.getItemId()) {

            case R.id.itemdelete:

                Toast.makeText(MainActivity.this, "Delete" + names.getName(), Toast.LENGTH_SHORT).show();

                User user = listUser.get(selectedid);
                mysqlitedb.deleteContact(user.getId());
                listUser.remove(selectedid);
                adapters.notifyDataSetChanged();
                lvContact.setAdapter(adapters);
                break;
            case R.id.itemedit:

                Intent intent = new Intent(MainActivity.this, AddUser.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Id", listUser.get(selectedid).getId());
                bundle.putString("Name", listUser.get(selectedid).getName());
                bundle.putString("Phone", listUser.get(selectedid).getPhoneNum());
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
            listUser.add(new User(id, name, phone, ""));
            mysqlitedb.addContact(new User(id,name,phone,""));
        }
        if (resultCode == 201) {
            Toast.makeText(MainActivity.this, "finish edit" + id + name, Toast.LENGTH_SHORT).show();
            listUser.set(selectedid, new User(id, name, phone, ""));
            adapters.notifyDataSetChanged();
            lvContact.setAdapter(adapters);
            User p = new User(id, name, phone, "");
            mysqlitedb.updateContact(id,p);
        }
        //cap nhat adapter
        adapters.notifyDataSetChanged();
    }
}