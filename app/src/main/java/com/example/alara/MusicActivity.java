package com.example.alara;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MusicActivity extends AppCompatActivity {
    boolean val=false;
    private ImageView fotoImagen;
    private TextView txtNombre;
    private Button botonPla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        fotoImagen=(ImageView)findViewById(R.id.fotoPlayCancion);
        txtNombre=(TextView)findViewById(R.id.playCancion);
        botonPla=(Button)findViewById(R.id.btnPlay);
        Bundle datos= getIntent().getExtras();
        String url= datos.getString("urlcancion");
        String named= datos.getString("nameCancion");
        Glide.with(this)
                .load(url)
                .into(fotoImagen);
        txtNombre.setText(named);
        final MediaPlayer mp= MediaPlayer.create(MusicActivity.this,R.raw.scartissue);
        botonPla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mp.isPlaying()){
                    mp.pause();
                    botonPla.setBackgroundResource(R.drawable.play);

                }else{
                    mp.start();
                    botonPla.setBackgroundResource(R.drawable.pause);
                }
            }
        });

    }


}
