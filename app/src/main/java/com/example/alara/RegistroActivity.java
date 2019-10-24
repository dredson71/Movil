package com.example.alara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.db.*;

public class RegistroActivity extends AppCompatActivity {
    EditText uCod,nCod,passCod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        uCod=(EditText)findViewById(R.id.txt_UserRegister);
        nCod=(EditText)findViewById(R.id.p_Name_Register);
        passCod=(EditText)findViewById(R.id.p_passwordRegister);
    }


    public void Registrar(View view){
    dbHelper helper = new dbHelper(this,"usuarios.sqlite",null,1);
    SQLiteDatabase db= helper.getWritableDatabase();

    String vuser= uCod.getText().toString();
    String valor_name= nCod.getText().toString();
    String valor_pass= passCod.getText().toString();


    if( ! vuser.isEmpty() && !valor_name.isEmpty() && !valor_pass.isEmpty()) {
        ContentValues registro = new ContentValues();
        registro.put("user",vuser);
        registro.put("nombre",valor_name);
        registro.put("password",valor_pass);
        db.insert("usuarios",null,registro);

        db.close();
        uCod.setText("");
        nCod.setText("");
        passCod.setText("");
        Toast.makeText(getApplicationContext(),"Registro exitoso",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),Main_App.class);
        startActivity(intent);
    }
    else{
        Toast.makeText(getApplicationContext(),"Se debe llenar todos los campos",Toast.LENGTH_SHORT).show();
    }
    }



}
