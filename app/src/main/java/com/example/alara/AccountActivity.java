package com.example.alara;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.retrofit.Cancion;
import com.example.retrofit.Discografica;
import com.example.retrofit.JsonPlaceHolderApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountActivity extends AppCompatActivity implements ListaDiscograficaAdapter.OnNoteListener {
    private BottomNavigationView mainNav;
    private static final int REQUEST_CALL=1;
    private static final String TAG = "DISCOGRAFICA";
    private ArrayList<Discografica> dataset = new ArrayList<>();
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private String telefono="";
    private ListaDiscograficaAdapter listaDiscograficaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_Discografica);
        listaDiscograficaAdapter = new ListaDiscograficaAdapter(this);
        recyclerView.setAdapter(listaDiscograficaAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        mainNav = (BottomNavigationView) findViewById(R.id.navbar_item);
        Menu menu = mainNav.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);


        retrofit = new Retrofit.Builder()
                .baseUrl("https://rocky-fjord-18899.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerDatos();

        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home_navbar:
                        Intent intent = new Intent(getApplicationContext(), Main_App.class);
                        startActivity(intent);
                        break;
                    case R.id.author_navbar:
                        Intent intent2 = new Intent(getApplicationContext(), AuthorActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.account_navbar:
                        break;

                }
                return false;
            }
        });

        onBackPressed();
    }

    private void obtenerDatos() {
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Discografica>> call = jsonPlaceHolderApi.getDiscografica();
        call.enqueue(new Callback<List<Discografica>>() {
            @Override
            public void onResponse(Call<List<Discografica>> call, Response<List<Discografica>> response) {
                if (response.isSuccessful()) {

                    List<Discografica> discograficas = response.body();
                    ArrayList<Discografica> listaCancion = (ArrayList) discograficas;
                    dataset = (ArrayList) discograficas;
                    listaDiscograficaAdapter.adicionarListaCancion(listaCancion, AccountActivity.this::oneNoteClick);
                } else {
                    Log.e(TAG, "onResponse:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Discografica>> call, Throwable t) {
                Log.e(TAG, "onFailure:" + t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void oneNoteClick(int position) {
        Intent llamar = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+dataset.get(position).getCorreoEmpresarial()));
        telefono=dataset.get(position).getCorreoEmpresarial();
        if (ContextCompat.checkSelfPermission(AccountActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(AccountActivity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
        } else {
            startActivity(llamar);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    if(requestCode == REQUEST_CALL){
        if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            Intent llamar = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+telefono));
            startActivity(llamar);
        }
    }
    }
}

