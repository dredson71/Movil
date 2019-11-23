package com.example.alara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.retrofit.Cancion;
import com.example.retrofit.JsonPlaceHolderApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddMusic extends AppCompatActivity {
    private Retrofit retrofit;
    private EditText txt_Name;
    private TextView txt_Fecha;
    private TextView txt_Contenido;
    private TextView txt_URL;
    private TextView txt_Artista;
    private JsonPlaceHolderApi jsonPlaceHolder;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_music);
        txt_Name=(EditText) findViewById(R.id.editname);
        txt_Fecha=(EditText) findViewById(R.id.editFecha);
        txt_Contenido=(EditText) findViewById(R.id.editUrlMusic);
        txt_URL=(EditText) findViewById(R.id.editURLImage);
        txt_Artista=(EditText) findViewById(R.id.editArtistaID);
        button=(Button)findViewById(R.id.btn_Add);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://rocky-fjord-18899.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolder=retrofit.create(JsonPlaceHolderApi.class);


        button.setOnClickListener(new View.OnClickListener(){
            SimpleDateFormat formatter2=new SimpleDateFormat("dd-MMM-yyyy");
            String text=txt_Fecha.getText().toString();
            java.util.Date textFieldAsDate = null;
            @Override
            public void onClick(View view) {

                try {
                    textFieldAsDate = formatter2.parse(text);
                } catch (ParseException pe) {
                    // deal with ParseException
                }
                Cancion cancion= new Cancion();
                cancion.setNombre(txt_Name.getText().toString());
                cancion.setContenido(txt_Contenido.getText().toString());
                cancion.setUrl(txt_URL.getText().toString());
                cancion.setFecha(textFieldAsDate);
                cancion.setArtistaId(Integer.parseInt(txt_Artista.getText().toString()));
                cancion.setEstadoPublico(true);
                cancion.setGeneroId(1);
                cancion.setDiscoId(1);
                Call<Cancion> call=jsonPlaceHolder.createMusic(cancion);
                Intent intent2 = new Intent(getApplicationContext(),Main_App.class);
                startActivity(intent2);

            }
        });
    }

}