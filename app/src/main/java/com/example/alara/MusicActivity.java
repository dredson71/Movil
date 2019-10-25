package com.example.alara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.IOException;

public class MusicActivity extends AppCompatActivity {
    boolean val=false;
    private ImageView fotoImagen;
    private TextView txtNombre;
    private Button botonPla;
    private Button retrocederbutton;
    private Button equalizerButton;
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
        String url= datos.getString("urlcancion");
        String named= datos.getString("nameCancion");
        String songmp3= datos.getString("mp3cancion");
        Glide.with(this)
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




    @Override
    public void onBackPressed(){

    }

}
