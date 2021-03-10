package com.pass.communify;

import android.net.Uri;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseConnection {
    private static final FirebaseDatabase database = FirebaseDatabase.getInstance("https://communify-78b34-default-rtdb.europe-west1.firebasedatabase.app/");

    public static void grabarPosicion(String nombre, LatLng posicion) {
        Log.d("TAG", nombre);
        Log.d("TAG", posicion.toString());
        Double lat = Double.valueOf(posicion.toString());
      
        Producto c1 = new Producto(nombre,posicion,lat);

        DatabaseReference myRef = database.getReference("Usuario");
        myRef.push().setValue(c1);

    }
}
