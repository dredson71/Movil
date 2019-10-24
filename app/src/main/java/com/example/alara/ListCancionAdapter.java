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
import com.example.retrofit.Cancion;

import java.util.ArrayList;

public class ListCancionAdapter extends RecyclerView.Adapter<ListCancionAdapter.ViewHolder> {

    private ArrayList<Cancion> dataset;
    private Context context;
    private OnNoteListener canonNoteListener;

    public ListCancionAdapter(Context context)
    {
        this.context=context;
        dataset= new ArrayList<>();
    }

   @Override
   public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cancionlist,parent,false);

       return new ViewHolder(view,canonNoteListener);
   }

   @Override
   public void onBindViewHolder(ViewHolder holder,int position){

    Cancion c = dataset.get(position);
    holder.txt_Cancion.setText(c.getNombre());
       Glide.with(context)
               .load(c.getUrl())
               .centerCrop()
               .crossFade()
               .diskCacheStrategy(DiskCacheStrategy.ALL)
               .into(holder.fotoImagen);
   }

   @Override
   public int getItemCount(){
       return dataset.size();
   }


   public void adicionarListaCancion(ArrayList<Cancion> listaCancion,OnNoteListener canonNoteListener){
        dataset.addAll(listaCancion);
       notifyDataSetChanged();
        this.canonNoteListener=canonNoteListener;
   }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       private ImageView fotoImagen;
       private TextView txt_Cancion;
       OnNoteListener onNoteListener;

        public ViewHolder(View itemView ,OnNoteListener onNoteListener ){
            super(itemView);
            fotoImagen=(ImageView) itemView.findViewById(R.id.fotoImage);
            txt_Cancion=(TextView) itemView.findViewById(R.id.txt_Cancion);
            this.onNoteListener=onNoteListener;

                itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.oneNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void oneNoteClick(int position);
    }
}
