package com.example.bai4;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//tham chieu toi edittext voi listview
        lvContact = findViewById(R.id.lvContact);
        etSearch = findViewById(R.id.etSearch);
        registerForContextMenu(lvContact);

        //tao
        listUser = new ArrayList<>();
        listUser.add(new User("sdfdf", "Phi", 1, 2,3));
        listUser.add(new User("sdfdf", "Phi", 1, 2,3));
        listUser.add(new User("sdfdf", "Phi", 1, 2,3));
        adapters = new MainAdapter(this, listUser);
        //lvAddapter=new ArrayAdapter<>( this,
        //      R.layout.support_simple_spinner_dropdown_item,Contacts);
        lvContact.setAdapter(adapters);
        lvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
        inflater.inflate(R.menu.menu_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }




}