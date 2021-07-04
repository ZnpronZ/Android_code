package com.example.baitap3;

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
    private ListView lvMusic;
    private EditText etSearch;
    ArrayList<Music> listMusic;
    MusicAdapter adapters;
    int selectedid = -1;
    MusicDB mysqlitedb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvMusic = findViewById(R.id.lvMusic);
        etSearch = findViewById(R.id.etSearch);
        registerForContextMenu(lvMusic);
        mysqlitedb = new MusicDB(this,"MusicDB",null,1);

        Anhxa();
        listMusic = mysqlitedb.getAllContact();
        adapters = new MusicAdapter(this, listMusic);
        lvMusic.setAdapter(adapters);
        lvMusic.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedid = position;

                return false;
            }
        });
    }
    private void  Anhxa()
    {
        mysqlitedb.addContact(new Music("Phút cuối",1,"Bằng Kiều",3));
        mysqlitedb.addContact(new Music("Bông hồn thủy tinh",2,"Bức Tường",4));
        mysqlitedb.addContact(new Music("Hà Nội mùa thu",3,"Mỹ Linh",4));
        mysqlitedb.addContact(new Music("Ba tôi",4,"Tùng Dương",3));
        mysqlitedb.addContact(new Music("Gót Hồng",5,"Quang Dung",2));

//        listMusic = new ArrayList<>();
//        listMusic.add(new Music("Phút cuối",1,"Bằng Kiều",3));
//        listMusic.add(new Music("Bông hồn thủy tinh",2,"Bức Tường",4));
//        listMusic.add(new Music("Hà Nội mùa thu",3,"Mỹ Linh",4));
//        listMusic.add(new Music("Ba tôi",4,"Tùng Dương",3));
//        listMusic.add(new Music("Gót Hồng",5,"Quang Dung",2));

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
                Intent intent = new Intent(MainActivity.this, AddMusic.class);

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
        Music names = ((Music) lvMusic.getAdapter().getItem(selectedid));
        switch (item.getItemId()) {

            case R.id.itemdelete:

                Toast.makeText(MainActivity.this, "Delete" + names.getName(), Toast.LENGTH_SHORT).show();

                Music user = listMusic.get(selectedid);
                mysqlitedb.deleteContact(user.getId());
                listMusic.remove(selectedid);
                adapters.notifyDataSetChanged();
                lvMusic.setAdapter(adapters);
                break;
            case R.id.itemedit:

                Intent intent = new Intent(MainActivity.this, AddMusic.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Id", listMusic.get(selectedid).getId());
                bundle.putString("BaiHat", listMusic.get(selectedid).getName());
                bundle.putDouble("Phut", listMusic.get(selectedid).getTime());
                bundle.putString("CaSi", listMusic.get(selectedid).getSinger());
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
        String baihat = bundle.getString("BaiHat");
        Double phut = bundle.getDouble("Phut");
        String casi = bundle.getString("CaSi");
        if (requestCode == 100 && resultCode == 200) {
            //dat vao data
            listMusic.add(new Music(baihat, id, casi, phut));
            mysqlitedb.addContact(new Music(baihat,id,casi,phut));
        }
        if (resultCode == 201) {
            Toast.makeText(MainActivity.this, "finish edit" + id + baihat, Toast.LENGTH_SHORT).show();
            listMusic.set(selectedid, new Music(baihat,id , casi, phut));
            adapters.notifyDataSetChanged();
            lvMusic.setAdapter(adapters);
            Music p = new Music(baihat,id , casi, phut);
            mysqlitedb.updateContact(id,p);
        }
        //cap nhat adapter
        adapters.notifyDataSetChanged();
    }
}