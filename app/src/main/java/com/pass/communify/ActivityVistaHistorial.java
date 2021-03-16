package com.pass.communify;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    String[] ListaProductos = {"Taladradora", "Termomix","Cortacesped", "Salir por Vallecas", "Pinzas de coche"};
    String[] ListaPeticion = {"Solicitado", "Solicitado", "Prestado", "Solicitado", "Prestado"};
    int[] listFotos = {R.drawable.fotobricolaje, R.drawable.fondococina, R.drawable.fotojardin,
            R.drawable.fotofiesta, R.drawable.fotomecanica};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_activity);

//        Historial h1 = new Historial();
//        Historial h2 = new Historial();
//        Historial h3 = new Historial();
//        Historial h4 = new Historial();
//        Historial h5 = new Historial();
//        Historial h6 = new Historial();
//
//        //True, solicitado. False, enviado
//        h1.setSolicitadoenviado("Solicitado");
//        h1.setProducto("Taladradora eléctrica");
//        h1.setFoto(R.drawable.fotobricolaje);
//        h1.setCalificacion((float) 4.5);
//        h2.setSolicitadoenviado("Solicitado");
//        h2.setProducto("Termomix");
//        h2.setFoto(R.drawable.fondococina);
//        h2.setCalificacion((float) 3.5);
//        h3.setSolicitadoenviado("Enviado");
//        h3.setProducto("Cafetera Cetotec");
//        h3.setFoto(R.drawable.fondococina);
//        h3.setCalificacion((float) 5.0);
//        h4.setSolicitadoenviado("Enviado");
//        h4.setProducto("Cortacesped manual");
//        h4.setFoto(R.drawable.fotojardin);
//        h4.setCalificacion((float) 4.0);
//        h5.setSolicitadoenviado("Solicitado");
//        h5.setProducto("Salir por el barrio");
//        h5.setFoto(R.drawable.fotofiesta);
//        h5.setCalificacion((float) 2.5);
//        h6.setSolicitadoenviado("Enviado");
//        h6.setProducto("Maletín de arrancado");
//        h6.setFoto(R.drawable.fotomecanica);
//        h6.setCalificacion((float) 5.0);
//
//        listaHistorial = new ArrayList<>();
//
//        listaHistorial.add(h1);
//        listaHistorial.add(h2);
//        listaHistorial.add(h3);
//        listaHistorial.add(h4);
//        listaHistorial.add(h5);
//        listaHistorial.add(h6);

        rv = findViewById(R.id.recycler);
        rv.setHasFixedSize(true);
        lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        MiAdaptador adaptador = new MiAdaptador(this, ListaProductos, ListaPeticion, listFotos);
        rv.setAdapter(adaptador);
    }
}
