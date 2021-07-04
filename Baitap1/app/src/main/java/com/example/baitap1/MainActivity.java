package com.example.baitap1;

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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   private ListView lvThietBi;
    ArrayList<Contact> arrContact;
    AdapterContact adapterContact;
    Button btnThem,btnXoa;
    DBContact mysqlitedb;
    int selectedid = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvThietBi=(ListView) findViewById(R.id.listViewDS);
        btnThem=(Button) findViewById(R.id.buttonThem);
        btnXoa=(Button) findViewById(R.id.buttonXoa);
        registerForContextMenu(lvThietBi);
//        arrContact=new ArrayList<>();
//        arrContact.add(new Contact(1,"Mot","34567",false));
//        arrContact.add(new Contact(2,"Hai","09874",true));
//        arrContact.add(new Contact(3,"Ba","56789",true));

        mysqlitedb = new DBContact(this,"ContactDB",null,1);
        mysqlitedb.addContact(new Contact(null,"Mot","34567",false));
        mysqlitedb.addContact(new Contact(null,"Hai","09874",true));
        mysqlitedb.addContact(new Contact(null,"Ba","56789",true));
        arrContact= mysqlitedb.getAllContact();
        adapterContact=new AdapterContact(this,R.layout.dong_ho_ten,arrContact);
        lvThietBi.setAdapter(adapterContact);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddContact.class);
                startActivityForResult(intent, 100);
            }
        });
        lvThietBi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()  {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedid = position;

                return false;
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogXacNhan=new AlertDialog.Builder(MainActivity.this);
                dialogXacNhan.setMessage("Bạn có chắc muốn xóa?");
                dialogXacNhan.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i=0;i<arrContact.size();i++){
                            if(arrContact.get(i).getStatus()==false){
                                Contact contact = arrContact.get(i);
                                arrContact.remove(i);
                                mysqlitedb.deleteContact(contact.getId());
                                i--;
                            }
                        }
                        adapterContact.notifyDataSetChanged();
                        lvThietBi.setAdapter(adapterContact);
                    }
                });
                dialogXacNhan.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, arrContact.size()+"", Toast.LENGTH_SHORT).show();
                    }
                });
                dialogXacNhan.show();
            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.contextmenuitem, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Contact names = ((Contact) lvThietBi.getAdapter().getItem(selectedid));
        switch (item.getItemId()) {

            case R.id.itemedit:

                Intent intent = new Intent(MainActivity.this, AddContact.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Id", arrContact.get(selectedid).getId());
                bundle.putString("Name", arrContact.get(selectedid).getName());
                bundle.putString("Phone", arrContact.get(selectedid).getPhoneNumber());
                bundle.putBoolean("Status",arrContact.get(selectedid).getStatus());
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
        Bundle bundle = data.getExtras();
        int id = bundle.getInt("Id");
        String name = bundle.getString("Name");
        String phone = bundle.getString("Phone");
        Boolean status = bundle.getBoolean("Status");
        if (requestCode == 100 && resultCode == 200) {
            //dat vao data
            arrContact.add(new Contact(id, name, phone, status));
            mysqlitedb.addContact(new Contact(id, name, phone, status));
        }
        if (resultCode == 201) {
            arrContact.set(selectedid, new Contact(id, name, phone, status));
            adapterContact.notifyDataSetChanged();
            lvThietBi.setAdapter(adapterContact);
            Contact p = new Contact(id, name, phone, status);
            mysqlitedb.updateContact(id,p);
        }
        //cap nhat adapter
        adapterContact.notifyDataSetChanged();

    }
}