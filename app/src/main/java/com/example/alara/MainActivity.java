package com.example.alara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.db.*;

public class MainActivity extends AppCompatActivity {
    Button btnIng;
    EditText etNom,etPas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNom=(EditText)findViewById(R.id.txt_User);
        etPas=(EditText)findViewById(R.id.p_password);
        btnIng=(Button)findViewById(R.id.btn_Ingresar);
       /* btnIng.setOnClickListener(  (View v) -> {
            String Nom = etNom.getText().toString();
            String Pas = etPas.getText().toString();


            if (Nom.equals("utp") && Pas.equals("123")){
                Toast.makeText(getApplicationContext(),"Acceso permitido",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),Main_App.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(),"Acceso denegado ....",Toast.LENGTH_LONG).show();
                etNom.setText("");
                etPas.setText("");
                etNom.requestFocus();
            }
        });*/
    }

    public void Registro(View view){
        Intent intent = new Intent(getApplicationContext(),RegistroActivity.class);
        startActivity(intent);
    }




    public void Buscar(View view){
        dbHelper helper = new dbHelper(this,"usuarios.sqlite",null,1);
        SQLiteDatabase db= helper.getWritableDatabase();

        String usercol= etNom.getText().toString();
        String pass= etPas.getText().toString();

        if(!usercol.isEmpty()){
        Cursor fila = db.rawQuery("select user,password from usuarios where user= '"+usercol+"'",null);

            if(fila.moveToFirst()){
                if((etNom.getText().toString().equalsIgnoreCase(fila.getString(0))) &&  etPas.getText().toString().equalsIgnoreCase(fila.getString(1)) ){
                    Intent intent = new Intent(getApplicationContext(),Main_App.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Acceso exitoso",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Contrasena incorrecta",Toast.LENGTH_LONG).show();
                    etPas.setText("");
                    etNom.requestFocus();
                    db.close();
                }
            }else{
                Toast.makeText(getApplicationContext(),"No existe usuario",Toast.LENGTH_LONG).show();
                etNom.setText("");
                etPas.setText("");
                etNom.requestFocus();
                db.close();
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"Se debe llenar todos los campos",Toast.LENGTH_LONG).show();

        }
    }
}
