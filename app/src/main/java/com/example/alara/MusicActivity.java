package com.example.alara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MusicActivity extends AppCompatActivity {
    private ImageView fotoImagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        fotoImagen=(ImageView)findViewById(R.id.fotoPlayCancion);
        Bundle datos= getIntent().getExtras();
        String url= datos.getString("urlcancion");
        Glide.with(this)
                .load(url)
                .into(fotoImagen);

    }
}
