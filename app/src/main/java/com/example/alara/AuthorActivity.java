package com.example.alara;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.retrofit.Autor;
import com.example.retrofit.Cancion;
import com.example.retrofit.JsonPlaceHolderApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthorActivity extends AppCompatActivity  {

    private static final String TAG ="ARTISTA";
    private BottomNavigationView mainNav;


    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListaArtistaAdapter listaArtistaAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        mainNav=(BottomNavigationView) findViewById(R.id.navbar_item);
        Menu menu = mainNav.getMenu();
        MenuItem menuItem= menu.getItem(1);
        menuItem.setChecked(true);


        recyclerView=(RecyclerView)findViewById(R.id.recyclerArtist);
        listaArtistaAdapter= new ListaArtistaAdapter();
        recyclerView.setAdapter(listaArtistaAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager= new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://rocky-fjord-18899.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerDatos();








        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home_navbar:
                        Intent intent = new Intent(getApplicationContext(),Main_App.class);
                        startActivity(intent);
                        break;
                    case R.id.author_navbar:
                        break;
                    case R.id.account_navbar:
                        Intent intent3 = new Intent(getApplicationContext(),AccountActivity.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });
        onBackPressed();
    }

    @Override
    public void onBackPressed(){

    }


    private void obtenerDatos(){
        JsonPlaceHolderApi jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Autor>> call=jsonPlaceHolderApi.getArtista();
        call.enqueue(new Callback<List<Autor>>() {
            @Override
            public void onResponse(Call<List<Autor>> call, Response<List<Autor>> response) {
                if(response.isSuccessful()) {

                    List<Autor> canciones = response.body();
                    ArrayList<Autor> listaCancion = (ArrayList) canciones;
                    listaArtistaAdapter.adicionarListaCancion(listaCancion);
                }else{
                    Log.e(TAG,"onResponse:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Autor>> call, Throwable t) {
                Log.e(TAG,"onFailure:" + t.getMessage());
            }
        });
    }


}
