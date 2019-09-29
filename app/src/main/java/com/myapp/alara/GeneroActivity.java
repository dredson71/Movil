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

public class GeneroActivity extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genero_layout);

        textViewResult=findViewById((R.id.text_genero_result));
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://localhost:8097/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Genero>> call = jsonPlaceHolderApi.getGenero();
        call.enqueue(new Callback<List<Genero>>() {
            @Override
            public void onResponse(Call<List<Genero>> call, Response<List<Genero>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code:"+response.code());
                    return;
                }
                List<Genero>generos = response.body();
                for(Genero genero:generos){
                    String content ="";
                    content += "Id: "+genero.getId()+ "\n";
                    content += "Detalle: "+genero.getDetalle()+ "\n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Genero>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });



    }
}
