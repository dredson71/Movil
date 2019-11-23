package com.example.alara;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.retrofit.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main_App extends AppCompatActivity implements ListCancionAdapter.OnNoteListener {
    private static final String TAG ="CANCION";
    private BottomNavigationView mainNav;
    private ArrayList<Cancion> dataset = new ArrayList<>();
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private Button addbutton;
    private ListCancionAdapter listacancionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__app);
        recyclerView =(RecyclerView) findViewById(R.id.recycler);
        listacancionAdapter = new ListCancionAdapter(this);
        recyclerView.setAdapter(listacancionAdapter);
        mainNav=(BottomNavigationView) findViewById(R.id.navbar_item);
        recyclerView.setHasFixedSize(true);
        addbutton=(Button)findViewById(R.id.buttonAddMusic);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
    Menu menu = mainNav.getMenu();
    MenuItem menuItem= menu.getItem(0);
    menuItem.setChecked(true);


         retrofit = new Retrofit.Builder()
                .baseUrl("https://rocky-fjord-18899.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        addbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddMusic.class);
                startActivity(intent);
            }
        });

        obtenerDatos();
        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home_navbar:
                        break;
                    case R.id.author_navbar:
                        Intent intent2 = new Intent(getApplicationContext(),AuthorActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.account_navbar:
                        Intent intent3 = new Intent(getApplicationContext(),AccountActivity.class);
                        startActivity(intent3);
                        break;
                        default:
                            break;
                }
                return false;
            }
        });
        onBackPressed();
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
    public void onBackPressed(){

    }

    @Override
    public void oneNoteClick(int position){

        Toast.makeText(getApplicationContext(),"Cargando ...",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(),MusicActivity.class);
        intent.putExtra("posicion",dataset.get(position).getId());
    /*    intent.putExtra("nameCancion",dataset.get(position).getNombre());
        intent.putExtra("mp3cancion",dataset.get(position).getContenido());*/
        startActivity(intent);
    }
}
