package com.example.alara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MusicActivity extends AppCompatActivity {
    private ImageView fotoImagen;
    private TextView txtNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        fotoImagen=(ImageView)findViewById(R.id.fotoPlayCancion);
        txtNombre=(TextView)findViewById(R.id.playCancion);
        Bundle datos= getIntent().getExtras();
        String url= datos.getString("urlcancion");
        String named= datos.getString("nameCancion");
        Glide.with(this)
                .load(url)
                .into(fotoImagen);
        txtNombre.setText(named);
    }
}
