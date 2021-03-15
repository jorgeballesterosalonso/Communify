package com.pass.communify;

import android.net.Uri;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseConnection {
    private static final FirebaseDatabase database = FirebaseDatabase.getInstance("https://communify-78b34-default-rtdb.europe-west1.firebasedatabase.app/");

    public static void grabarPosicion(String nombreUsuario, LatLng posicion) {
        Log.d("TAG", nombreUsuario);
        Log.d("TAG", posicion.toString());

      
        Producto c1 = new Producto(nombreUsuario, posicion);

        DatabaseReference myRef = database.getReference("User").child(nombreUsuario.split("@")[0]+nombreUsuario.split("@")[1].replace('.',' '));

        myRef.push().setValue(c1);
        //myRef.push().setValue(posicion);

    }
}
