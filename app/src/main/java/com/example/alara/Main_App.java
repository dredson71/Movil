package com.example.alara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.retrofit.*;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main_App extends AppCompatActivity implements ListCancionAdapter.OnNoteListener {
    private static final String TAG ="CANCION";
    private ArrayList<Cancion> dataset = new ArrayList<>();
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListCancionAdapter listacancionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__app);
        recyclerView =(RecyclerView) findViewById(R.id.recycler);
        listacancionAdapter = new ListCancionAdapter(this);
        recyclerView.setAdapter(listacancionAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);


         retrofit = new Retrofit.Builder()
                .baseUrl("https://rocky-fjord-18899.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerDatos();


    }

    private void obtenerDatos(){
        JsonPlaceHolderApi jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Cancion>> call=jsonPlaceHolderApi.getCancion();
        call.enqueue(new Callback<List<Cancion>>() {
            @Override
            public void onResponse(Call<List<Cancion>> call, Response<List<Cancion>> response) {
                if(response.isSuccessful()) {

                    List<Cancion> canciones = response.body();
                    ArrayList<Cancion> listaCancion = (ArrayList) canciones;
                    dataset=(ArrayList) canciones;
                    listacancionAdapter.adicionarListaCancion(listaCancion,Main_App.this::oneNoteClick);
                }else{
                    Log.e(TAG,"onResponse:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Cancion>> call, Throwable t) {
                Log.e(TAG,"onFailure:" + t.getMessage());
            }
        });
    }

    @Override
    public void oneNoteClick(int position){
        Intent intent = new Intent(getApplicationContext(),MusicActivity.class);
        intent.putExtra("urlcancion",dataset.get(position).getUrl());
        startActivity(intent);
    }
}
