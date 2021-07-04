package com.example.bai2_cuavinh;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvThietBi;
    ArrayList<ThietBi> arrThietBi;
    AdapterThietBi adapterThietBi;
    Button btnThem,btnXoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvThietBi=(ListView) findViewById(R.id.listViewDS);
        btnThem=(Button) findViewById(R.id.buttonThem);
        btnXoa=(Button) findViewById(R.id.buttonXoa);
        arrThietBi=new ArrayList<>();
        arrThietBi.add(new ThietBi(1,"Bóng đèn","công suất 100","",true));
        arrThietBi.add(new ThietBi(2,"Bóng đá","công suất 200","",false));
        arrThietBi.add(new ThietBi(3,"Bóng đù","công suất 130","",false));
        arrThietBi.add(new ThietBi(5,"Bóng đì","công suất 640","",false));
        adapterThietBi=new AdapterThietBi(this,R.layout.dong_thiet_bi,arrThietBi);
        lvThietBi.setAdapter(adapterThietBi);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              AlertDialog.Builder dialogXacNhan=new AlertDialog.Builder(MainActivity.this);
              dialogXacNhan.setMessage("Bạn có chắc muốn xóa?");
              dialogXacNhan.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      for (int i=0;i<arrThietBi.size();i++){
                          if(arrThietBi.get(i).isStatus()==false){
                              arrThietBi.remove(i);
                              i--;
                          }
                      }
                      adapterThietBi.notifyDataSetChanged();
                      //lvThietBi.setAdapter(adapterThietBi);
                  }
              });
                dialogXacNhan.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, arrThietBi.size()+"", Toast.LENGTH_SHORT).show();
                    }
                });
              dialogXacNhan.show();
            }
        });


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivityAdd.class);
               startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle=data.getExtras();
        if(bundle!=null){
            int id=bundle.getInt("Id");
            String name=bundle.getString("Name");
            String moTa=bundle.getString("MoTa");
            boolean status=bundle.getBoolean("status");
            if(requestCode==100 &&resultCode==200){
                arrThietBi.add(new ThietBi(id,name,moTa,"",status));
            }
            adapterThietBi.notifyDataSetChanged();
            //lvThietBi.setAdapter(adapterThietBi);
        }
    }
}