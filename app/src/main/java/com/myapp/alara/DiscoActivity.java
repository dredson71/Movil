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

public class DiscoActivity extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disco_layout);

        textViewResult=findViewById((R.id.text_disco_result));
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://localhost:8097/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Disco>> call = jsonPlaceHolderApi.getDisco();
        call.enqueue(new Callback<List<Disco>>() {
            @Override
            public void onResponse(Call<List<Disco>> call, Response<List<Disco>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code:"+response.code());
                    return;
                }
                List<Disco>discos = response.body();
                for(Disco disco:discos){
                    String content ="";
                    content += "Nombre: "+disco.getNombre()+ "\n";
                    content += "Descripcion: "+disco.getDescripcion()+ "\n";
                    content += "Fecha: "+disco.getFecha()+ "\n";




                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Disco>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });



    }
}
