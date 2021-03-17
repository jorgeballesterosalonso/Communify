package com.pass.communify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;


public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.MiAdaptadorVistas> {

    Context context;
    String[] productos;
    String[] solicitados;
    Double[] puntuacion;
    int[] fotos;

    public MiAdaptador(ArrayList<Historial> listaHistorial) {
    }


    public class MiAdaptadorVistas extends RecyclerView.ViewHolder {
        MiAdaptadorVistas contexto2 = this;
        TextView producto;
        TextView solicitado;
        ImageView rowImage;
        RatingBar estrellitas;



        public MiAdaptadorVistas(@NonNull View itemView) {
            super(itemView);

            producto = itemView.findViewById(R.id.tv_producto);
            solicitado = itemView.findViewById(R.id.tv_solicitado);
            rowImage = itemView.findViewById(R.id.iv_foto);
            estrellitas = itemView.findViewById(R.id.ratingBar);

            }
        }


    public MiAdaptador(Context context, String[] productos, String[] solicitados, Double[] puntuacion, int[] fotos) {
        this.context = context;
        this.productos = productos;
        this.solicitados = solicitados;
        this.puntuacion = puntuacion;
        this.fotos = fotos;

    }

    @NonNull
    @Override
    public MiAdaptador.MiAdaptadorVistas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_adaptador, parent, false);
        MiAdaptadorVistas viewHolder = new MiAdaptadorVistas(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MiAdaptador.MiAdaptadorVistas holder, int position) {
        int idfoto = fotos[position];
        holder.producto.setText(productos[position]);
        holder.solicitado.setText(solicitados[position]);
        holder.estrellitas.setRating(puntuacion[position].floatValue());
        //holder.rowImage.setImageResource(fotos[position]);
        Glide.with(context)
                .load(idfoto)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade(100))
                .circleCrop()
                .into(holder.rowImage);
    }

    @Override
    public int getItemCount() {

        return productos.length;
    }


}




