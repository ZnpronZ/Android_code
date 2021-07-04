 package com.example.sqllite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Database database;
    ListView lvLogin;
    ArrayList<Login> arrayLogin;
    LoginAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvLogin= (ListView)findViewById(R.id.lvLogin);
        arrayLogin = new ArrayList<>();
        adapter = new LoginAdapter(this,R.layout.login_list,arrayLogin);
        lvLogin.setAdapter(adapter);
        database= new Database(this,"DBLogin",null,1);

        database.QueryData("CREATE TABLE IF NOT EXISTS Login(Id INTEGER PRIMARY KEY AUTOINCREMENT,Username VARCHAR(50),Password VARCHAR(50))");
        //insert data
        //database.QueryData("INSERT INTO Login VALUES(null,'Admin1','Admin1')");
        //select data
        GetDataLogin();
    }
    public  void  DialogXoaLogin(String user,int id)
    {
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xóa "+user+" này không ?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               database.QueryData("DELETE FROM Login WHERE Id='"+id+"'");
                Toast.makeText(MainActivity.this,"Đã xóa "+user,Toast.LENGTH_SHORT).show();
                GetDataLogin();
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }
    public void DialogSuaLogin(String user,String pass,int id)
    {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua_login);

        EditText editUser = (EditText) dialog.findViewById(R.id.editTextUser);
        EditText editPass = (EditText) dialog.findViewById(R.id.editTextPass);
        Button btnEdit=(Button) dialog.findViewById(R.id.btnEdit);
        Button btnThoat=(Button) dialog.findViewById(R.id.btn_Exit_edit);
        editUser.setText(user);
        editPass.setText(pass);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User = editUser.getText().toString().trim();
                String Pass = editPass.getText().toString().trim();
                database.QueryData("UPDATE Login SET Username='"+User+"' ,Password='"+Pass+"' WHERE Id='"+id+"'");
                Toast.makeText(MainActivity.this,"Đã cập nhật",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                GetDataLogin();
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void GetDataLogin()
    {
        Cursor dataLogin = database.GetData("SELECT * FROM Login");
        arrayLogin.clear();
        while (dataLogin.moveToNext())
        {
            String user =dataLogin.getString(1);
            String pass =dataLogin.getString(2);
            Integer Id = dataLogin.getInt(0);
            arrayLogin.add(new Login(Id,user,pass));
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_login,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()== R.id.menuAdd)
        {
            DialogThem();
        }
        return super.onOptionsItemSelected(item);
    }
    private void DialogThem(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them_login);
        EditText edUser = (EditText) dialog.findViewById(R.id.edTextUser);
        EditText edPass = (EditText) dialog.findViewById(R.id.edTextPass);
        Button btnThem=(Button) dialog.findViewById(R.id.btnAdd);
        Button btnThoat=(Button) dialog.findViewById(R.id.btnExit_add);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User= edUser.getText().toString();
                String Pass = edPass.getText().toString();
                if(User.equals("") || Pass.equals(""))
                {
                    Toast.makeText(MainActivity.this,"Không được để trống",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    database.QueryData("INSERT INTO Login VALUES(null,'" + User + "','"+ Pass +"')");
                    Toast.makeText(MainActivity.this,"Đã thêm",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    GetDataLogin();

                }
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}