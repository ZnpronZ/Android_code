package com.example.listviewhigh;

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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvTraiCay;
    ArrayList<TraiCay> arrayTraiCay;
    TraiCayAdapter adapter;
    ArrayList<TraiCay> listTraiCay;
    int selectedid = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        adapter= new TraiCayAdapter(this,R.layout.dong_trai_cay,arrayTraiCay);
        lvTraiCay.setAdapter(adapter);
        lvTraiCay.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedid = position;

                return false;
            }
        });
    }
    private void  Anhxa()
    {
        lvTraiCay=(ListView) findViewById(R.id.listviewTraiCay);
        arrayTraiCay= new ArrayList<>();
        arrayTraiCay.add(new TraiCay("Dâu tây","Dâu tây đà lạt",R.drawable.dautay));
        arrayTraiCay.add(new TraiCay("Dứa","Đặc sản trà vinh",R.drawable.dua));
        arrayTraiCay.add(new TraiCay("Măng cụt","Măng cụt miền tây",R.drawable.mangcut));
        arrayTraiCay.add(new TraiCay("Thanh Long","Thanh long bình thuận",R.drawable.thanhlong));
        arrayTraiCay.add(new TraiCay("Xoài","Xoài thơm ngọt",R.drawable.qua_xoai_2));

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
                Intent intent = new Intent(MainActivity.this, AddTraiCay.class);

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
        TraiCay names = ((TraiCay) lvTraiCay.getAdapter().getItem(selectedid));
        switch (item.getItemId()) {

            case R.id.itemdelete:

                Toast.makeText(MainActivity.this, "Delete" + names.getTen(), Toast.LENGTH_SHORT).show();

                TraiCay user = listTraiCay.get(selectedid);

                listTraiCay.remove(selectedid);
                adapter.notifyDataSetChanged();
                lvTraiCay.setAdapter(adapter);
                break;
            case R.id.itemedit:

                Intent intent = new Intent(MainActivity.this, AddTraiCay.class);
                Bundle bundle = new Bundle();
                bundle.putString("Ten", listTraiCay.get(selectedid).getTen());
                bundle.putString("MoTa", listTraiCay.get(selectedid).getMoTa());
                bundle.putInt("Hinh", listTraiCay.get(selectedid).getHinh());
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
        String moTa = bundle.getString("MoTa");
        String ten = bundle.getString("Ten");
        int hinh = bundle.getInt("Hinh");
        if (requestCode == 100 && resultCode == 200) {
            //dat vao data
            listTraiCay.add(new TraiCay(ten, moTa,R.drawable.dautay));

        }
        if (resultCode == 201) {
            Toast.makeText(MainActivity.this, "finish edit" + moTa + ten, Toast.LENGTH_SHORT).show();
            listTraiCay.set(selectedid, new TraiCay(ten, moTa,R.drawable.dautay));
            adapter.notifyDataSetChanged();
            lvTraiCay.setAdapter(adapter);
            TraiCay p = new TraiCay(ten, moTa,R.drawable.dautay);

        }
        //cap nhat adapter
        adapter.notifyDataSetChanged();
    }
}