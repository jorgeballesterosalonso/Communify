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

import java.util.ArrayList;
import java.util.List;


public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.MiAdaptadorVistas> {

    Context context;
    String[] productos, solicitados;
    int[] fotos;

    public MiAdaptador(ArrayList<Historial> listaHistorial) {
    }


    public class MiAdaptadorVistas extends RecyclerView.ViewHolder {
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

    public MiAdaptador(Context context, String[] productos, String[] solicitados, int[] fotos) {
        this.context = context;
        this.productos = productos;
        this.solicitados = solicitados;
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
        holder.producto.setText(productos[position]);
        holder.solicitado.setText(solicitados[position]);
        holder.rowImage.setImageResource(fotos[position]);
    }

    @Override
    public int getItemCount() {

        return productos.length;
    }


}




