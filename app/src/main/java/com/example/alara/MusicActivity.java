package com.example.alara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.retrofit.Cancion;
import com.example.retrofit.JsonPlaceHolderApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MusicActivity extends AppCompatActivity {
    private static final String TAG ="CANCION";
    boolean val=false;
    private ImageView fotoImagen;
    private TextView txtNombre;
    private Button botonPla;
    private Button retrocederbutton;
    private Button equalizerButton;
    private Retrofit retrofit;
    private Cancion cancion23;
    private ListCancionAdapter listacancionAdapter;
    MediaPlayer mPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        fotoImagen=(ImageView)findViewById(R.id.fotoPlayCancion);
        txtNombre=(TextView)findViewById(R.id.playCancion);
        retrocederbutton=(Button)findViewById(R.id.button);
        equalizerButton=(Button)findViewById(R.id.buttonecualizer);
        botonPla=(Button)findViewById(R.id.btnPlay);


        Bundle datos= getIntent().getExtras();

        int position= datos.getInt("posicion");

        retrofit = new Retrofit.Builder()

                .baseUrl("https://rocky-fjord-18899.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       obtenerDatos( String.valueOf(position));





   /*   String url=cancion23.getUrl();
       String named=cancion23.getNombre();
        String songmp3=cancion23.getContenido();

*/


     /*  String url= datos.getString("urlcancion");
        String named= datos.getString("nameCancion");
        String songmp3= datos.getString("mp3cancion");*/


   /*     Glide.with(this)
                .load(url)
                .into(fotoImagen);
        txtNombre.setText(named);
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(songmp3);
            mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        retrocederbutton.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            mPlayer.pause();
            botonPla.setBackgroundResource(R.drawable.play);
            Intent intent = new Intent(getApplicationContext(),Main_App.class);
            startActivity(intent);
        }
        });

        equalizerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mPlayer.pause();
                botonPla.setBackgroundResource(R.drawable.play);
                Intent intent = new Intent(getApplicationContext(),EcualizadorActivity.class);
                startActivity(intent);
            }
        });

       botonPla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPlayer.isPlaying()){
                    mPlayer.pause();
                    botonPla.setBackgroundResource(R.drawable.play);

                }else{
                    mPlayer.start();
                    botonPla.setBackgroundResource(R.drawable.pause);
                }
            }
        });

        onBackPressed();

    }


    private void obtenerDatos(String position){
        JsonPlaceHolderApi jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        Call<Cancion> call=jsonPlaceHolderApi.getCancionbyId("cancion/"+position);
        call.enqueue(new Callback<Cancion>() {
            @Override
            public void onResponse(Call<Cancion> call, Response<Cancion> response) {
                if(response.isSuccessful()) {

                    Cancion canciones = response.body();
                    cancion23=canciones;
                    String url=cancion23.getUrl();
                    String named=cancion23.getNombre();
                    String songmp3=cancion23.getContenido();
                    Glide.with(MusicActivity.this)
                            .load(url)
                            .into(fotoImagen);
                    txtNombre.setText(named);
                    mPlayer = new MediaPlayer();
                    try {
                        mPlayer.setDataSource(songmp3);
                        mPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Log.e(TAG,"onResponse:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Cancion> call, Throwable t) {
                Log.e(TAG,"onFailure:" + t.getMessage());
            }
        });




    }


    @Override
    public void onBackPressed(){

    }

}
