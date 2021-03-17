package com.pass.communify;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityVistaHistorial extends AppCompatActivity {
    //    ArrayList<Historial> listaHistorial=new ArrayList<>();
    RecyclerView rv;
    RecyclerView.LayoutManager lm;
    RecyclerView.Adapter ad;
    String[] ListaProductos = {"Taladradora", "Termomix", "Cortacesped", "Salir por Vallecas", "Pinzas de coche"};
    String[] ListaPeticion = {"Solicitado", "Solicitado", "Prestado", "Solicitado", "Prestado"};
    Double[] puntuacion = {3.5, 1.5, 2.2, 4.5, 5.0};
    int[] listaFotos = {R.drawable.fotobricolaje, R.drawable.fondococina, R.drawable.fotojardin,
            R.drawable.fotofiesta, R.drawable.fotomecanica};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_activity);

        rv = findViewById(R.id.recycler);
        rv.setHasFixedSize(true);
        lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        MiAdaptador adaptador = new MiAdaptador(this, ListaProductos, ListaPeticion, puntuacion, listaFotos);
        rv.setAdapter(adaptador);
    }
}
