package com.example.alara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        btnIng.setOnClickListener(  (View v) -> {
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
        });
    }
}
