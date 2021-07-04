package com.example.bt2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvContact;
    private EditText etSearch;

    ArrayList<User> listUser;
    MainAdapter adapters;
    Button btndel, btnAdd;
    Switch aSwitch;
    Boolean status;
    int selectedid = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aSwitch = findViewById(R.id.switch1);
        btnAdd = findViewById(R.id.btnAdd);
        btndel = findViewById(R.id.btnDel);
        lvContact = findViewById(R.id.lvContact);
        etSearch = findViewById(R.id.etSearch);


        listUser = new ArrayList<>();
        listUser.add(new User(1, "Bóng đèn", "Điện 220v", "", true));
        listUser.add(new User(2, "Bình nóng", "Công suất 1000w", "", true));
        listUser.add(new User(3, "Điều hòa", "Có inverter", "", false));
        adapters = new MainAdapter(this, listUser);
        lvContact.setAdapter(adapters);


        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (listUser.get(position).isStatus()) {
                    aSwitch.setChecked(false);
                    listUser.get(position).setStatus(false);
                    adapters.notifyDataSetChanged();
                    lvContact.setAdapter(adapters);
                } else {
                    aSwitch.setChecked(true);
                    listUser.get(position).setStatus(true);
                    adapters.notifyDataSetChanged();
                    lvContact.setAdapter(adapters);
                }
            }
        });
    }
}