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
import com.example.retrofit.Discografica;

import java.util.ArrayList;

public class ListaDiscograficaAdapter extends RecyclerView.Adapter<ListaDiscograficaAdapter.ViewHolder> {

    private ArrayList<Discografica> dataset;
    private Context context;
    private ListaDiscograficaAdapter.OnNoteListener canonNoteListener;

    public ListaDiscograficaAdapter(Context context)
    {
        this.context=context;
        dataset= new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discograficalist,parent,false);

        return new ViewHolder(view,canonNoteListener);
    }

    @Override
    public void onBindViewHolder(ListaDiscograficaAdapter.ViewHolder holder, int position){

        Discografica c = dataset.get(position);
        holder.txt_Cancion.setText(c.getCorreoEmpresarial());
    }

    @Override
    public int getItemCount(){
        return dataset.size();
    }


    public void adicionarListaCancion(ArrayList<Discografica> listaDiscografica, ListaDiscograficaAdapter.OnNoteListener canonNoteListener){
        dataset.addAll(listaDiscografica);
        notifyDataSetChanged();
        this.canonNoteListener=canonNoteListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txt_Cancion;
        ListaDiscograficaAdapter.OnNoteListener onNoteListener;

        public ViewHolder(View itemView , ListaDiscograficaAdapter.OnNoteListener onNoteListener ){
            super(itemView);
            txt_Cancion=(TextView) itemView.findViewById(R.id.txt_Discografica);
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
