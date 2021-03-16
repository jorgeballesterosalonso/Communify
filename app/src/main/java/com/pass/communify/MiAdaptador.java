package com.pass.communify;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.MiContenedorDeVistas> {
    private ArrayList<Historial> lista_historial;

    public MiAdaptador(ArrayList<Historial> lista_historial) {
        this.lista_historial = lista_historial;
    }

    @NonNull
    @Override
    public MiAdaptador.MiContenedorDeVistas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adaptador, parent, false);
        MiContenedorDeVistas contenedor = new MiContenedorDeVistas(vista);
        return contenedor;
    }

    @Override
    public void onBindViewHolder(@NonNull MiAdaptador.MiContenedorDeVistas holder, int position) {
        Historial h = lista_historial.get(position);
        holder.nombre.setText(h.getProducto());
        holder.solicitado.setText(h.getSolicitadoenviado());
        holder.estrellas.setRating(h.getCalificacion());
        holder.foto.setImageResource(h.getFoto());


    }

    @Override
    public int getItemCount() {
        return lista_historial.size();
    }


    public static class MiContenedorDeVistas extends RecyclerView.ViewHolder{
        public TextView nombre, solicitado;
        public ImageView foto;
        public RatingBar estrellas;

        public MiContenedorDeVistas(View vista) {
            super(vista);
            this.nombre = vista.findViewById(R.id.tv_producto);
            this.solicitado = vista.findViewById(R.id.tv_solicitado);
            this.foto = vista.findViewById(R.id.iv_foto);
            this.estrellas = vista.findViewById(R.id.ratingBar);
        }
    }
}


