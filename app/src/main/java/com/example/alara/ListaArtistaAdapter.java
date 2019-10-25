package com.example.alara;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.retrofit.Autor;
import com.example.retrofit.Cancion;

import java.util.ArrayList;

public class ListaArtistaAdapter extends RecyclerView.Adapter<ListaArtistaAdapter.ViewHolder> {

    private ArrayList<Autor> dataset;

    public ListaArtistaAdapter()
    {
        dataset= new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artistalist,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){

        Autor c = dataset.get(position);
        holder.txt_Artista.setText(c.getNombreArtistico());
    }

    @Override
    public int getItemCount(){
        return dataset.size();
    }


    public void adicionarListaCancion(ArrayList<Autor> listaArtista){
        dataset.addAll(listaArtista);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_Artista;

        public ViewHolder(View itemView  ){
            super(itemView);
            txt_Artista=(TextView) itemView.findViewById(R.id.txt_Artista);
        }

        }
    }


