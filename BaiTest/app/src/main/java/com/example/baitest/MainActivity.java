package com.example.baitest;

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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvDatVe;
    private EditText etSearch;
    ArrayList<String> Contacts;
    ArrayAdapter<String> lvAddapter;
    ArrayList<VeTau> listVeTau;
    MainAdapter adapters;
    int selectedid = -1;
    VeTauDB mysqlitedb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvDatVe = findViewById(R.id.lvDatVe);
        etSearch = findViewById(R.id.etSearch);
        registerForContextMenu(lvDatVe);
        mysqlitedb = new VeTauDB(this,"VeTauDB",null,1);
//        mysqlitedb.addVeTau(new VeTau(null,"Vinh", "Nam Định", (float) 355.5,true));
//        mysqlitedb.addVeTau(new VeTau(null,"Nam Định", "Thanh Hóa", (float) 237.5,true));
//        mysqlitedb.addVeTau(new VeTau(null,"Thanh Hóa", "Hà Nội", (float) 170,false));
//        mysqlitedb.addVeTau(new VeTau(null,"Hà Nội", "Thanh Hóa", (float) 170,true));
//        mysqlitedb.addVeTau(new VeTau(null,"Vinh", "Nam Định", (float) 354,false));
        listVeTau = mysqlitedb.getAllVeTau();
        adapters = new MainAdapter(this, listVeTau);
        //lvAddapter=new ArrayAdapter<>( this,
        //      R.layout.support_simple_spinner_dropdown_item,Contacts);
        lvDatVe.setAdapter(adapters);
        lvDatVe.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()  {
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
                Intent intent = new Intent(MainActivity.this, AddVeTau.class);

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
        VeTau names = ((VeTau) lvDatVe.getAdapter().getItem(selectedid));
        switch (item.getItemId()) {

            case R.id.itemdelete:


                VeTau vetau = listVeTau.get(selectedid);
                mysqlitedb.deleteVeTau(vetau.getId());
                listVeTau.remove(selectedid);
                adapters.notifyDataSetChanged();
                lvDatVe.setAdapter(adapters);
                break;
            case R.id.itemedit:

                Intent intent = new Intent(MainActivity.this, AddVeTau.class);
                Bundle bundle = new Bundle();
                bundle.putString("GaDi", listVeTau.get(selectedid).getGaDi());
                bundle.putString("GaDen", listVeTau.get(selectedid).getGaDen());
                bundle.putFloat("Gia",listVeTau.get(selectedid).getGia());
                bundle.putBoolean("KhuHoi",listVeTau.get(selectedid).getKhuHoi());
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
        String gadi = bundle.getString("GaDi");
        String gaden = bundle.getString("GaDen");
        Float gia = bundle.getFloat("Gia");
        Boolean khuhoi= bundle.getBoolean("KhuHoi");
        if (requestCode == 100 && resultCode == 200) {
            //dat vao data
            listVeTau.add(new VeTau(selectedid, gadi, gaden, gia,khuhoi));
            mysqlitedb.addVeTau(new VeTau(selectedid, gadi, gaden, gia,khuhoi));
        }
        if (resultCode == 201) {
            listVeTau.set(selectedid, new VeTau(selectedid, gadi, gaden, gia,khuhoi));
            adapters.notifyDataSetChanged();
            lvDatVe.setAdapter(adapters);
            VeTau p = new VeTau(selectedid, gadi, gaden, gia,khuhoi);
            mysqlitedb.updateVeTau(selectedid,p);
        }
        //cap nhat adapter
        adapters.notifyDataSetChanged();
    }
}