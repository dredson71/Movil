package com.myapp.alara;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.myapp.myapp.alara.Service.*;
import com.myapp.myapp.alara.model.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtistaActivity extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artista_list);

        textViewResult=findViewById((R.id.text_artista_result));
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://localhost:8097/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Artista>> call = jsonPlaceHolderApi.getArtista();
        call.enqueue(new Callback<List<Artista>>() {
            @Override
            public void onResponse(Call<List<Artista>> call, Response<List<Artista>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code:"+response.code());
                    return;
                }
                List<Artista>artistas = response.body();
                for(Artista artista:artistas){
                    String content ="";
                    content += "Sexo: "+artista.getSexo()+ "\n";
                    content += "Correo: "+artista.getCorreo()+ "\n";
                    content += "Contrasena: "+artista.getContraseña()+ "\n";
                    content += "Nombre: "+artista.getNombreArtistico()+ "\n";

                    content += "Edad: "+artista.getEdad()+ "\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Artista>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });



    }
}
