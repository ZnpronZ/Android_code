package com.example.test1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private Database dataBase = new Database(this);
    private ListView lvThiSinh;
    private ArrayList<Vetau> arrayListNhac;
    private VetaAdapter adapterNhac;

    private Button btnAdd;
    private EditText lsvTim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Map();
        // dataBase.xoaToanBo();
           add();
        arrayListNhac= (ArrayList<Vetau>) dataBase.getAll();
        Collections.sort(arrayListNhac);
        adapterNhac = new VetaAdapter(MainActivity.this, R.layout.item_listview, arrayListNhac);
        lvThiSinh.setAdapter(adapterNhac);
    lvThiSinh.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        registerForContextMenu(lvThiSinh);
        return false;
    }
    });

//        lvThiSinh.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        // adapterNhac.notifyDataSetChanged();
        // bat su kien long click listview
    }
    private void add(){
        dataBase.addSP(new Vetau(1, "Vinh", "Quảng Ninh", (double) 5000, true));
        dataBase.addSP(new Vetau(2, "Hà Nội", "Thái Nguyên", (double) 3000, true));
        dataBase.addSP(new Vetau(3, "Sài Gòn", "Nam Định", (double) 6000, true));
        dataBase.addSP(new Vetau(4, "Đà Nẵng", "Phú Thọ", (double) 60000, false));
    }

    private void Map() {
        lsvTim = (EditText)findViewById(R.id.edTimKiem);
        arrayListNhac = new ArrayList<>();
        lvThiSinh = (ListView) findViewById(R.id.lsvBaiHat);
        btnAdd = (Button)findViewById(R.id.btnAdd);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.itemSua) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int listPosition = info.position;
            int ma = arrayListNhac.get(listPosition).getMa();//list item title
            String gaden = arrayListNhac.get(listPosition).getGaden();//list item title
            String gadi = arrayListNhac.get(listPosition).getGadi();//list item title
            double giatien = arrayListNhac.get(listPosition).getGiaTien();//list item title
            boolean khuhoi = arrayListNhac.get(listPosition).isKhuhoi();//list item title


            Toast.makeText(MainActivity.this, ma+"", Toast.LENGTH_SHORT).show();
            Intent it = new Intent(MainActivity.this, UpdateActivity.class);
            it.putExtra("ID",  ma );
            it.putExtra("GADEN",  gaden );
            it.putExtra("GADI",  gadi );
            it.putExtra("GIATIEN",  giatien );
            it.putExtra("KHIHOI",  khuhoi );


            startActivity(it);
        }
        return super.onContextItemSelected(item);
    }
}
