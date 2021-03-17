package com.pass.communify;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class FirebaseConnection {
    private static final FirebaseDatabase database = FirebaseDatabase.getInstance("https://communify-392d2-default-rtdb.firebaseio.com/");
    private static final FirebaseStorage storage = FirebaseStorage.getInstance("gs://communify-392d2.appspot.com");
    private static Context contexto;

    public static void grabarPosicion(String nombreUsuario, LatLng posicion) {

        Log.d("TAG", nombreUsuario);
        Log.d("TAG", posicion.toString());


        Producto c1 = new Producto(nombreUsuario, posicion);

        DatabaseReference myRef = database.getReference("User").child(nombreUsuario.split("@")[0] + nombreUsuario.split("@")[1].replace('.', ' '));

        myRef.push().setValue(c1);
        //myRef.push().setValue(posicion);

    }

    public static void grabarObjeto(String userName, Producto p) {

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://communify-392d2-default-rtdb.firebaseio.com/");
        FirebaseDatabase.getInstance().goOffline();
        FirebaseDatabase.getInstance().goOnline();
        Log.d("TAG", "Grabando Objeto...");
        DatabaseReference referencia = database.getReference("Objeto").child(userName.replace('@', ' ').replace('.', ' '));
        referencia.push().setValue(p);
    }

    public static void grabarFoto(Uri uri, String userName) {
        FirebaseStorage storage = FirebaseStorage.getInstance("gs://communify-392d2.appspot.com");
        Log.d("TAG", "Grabando foto...");
        StorageReference storageRef = storage.getReference();
        UploadTask referenciaFoto = storageRef.child(userName + uri.toString()).putFile(uri);
    }

    public static List<Producto> obtenerProductosFirebase(String userName) {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://communify-392d2-default-rtdb.firebaseio.com/");
        FirebaseDatabase.getInstance().goOffline();
        FirebaseDatabase.getInstance().goOnline();
        DatabaseReference referencia = database.getReference("Objeto");
        List<Producto> listaProductos = new ArrayList<>();
        referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Iterable<DataSnapshot> espanshot = dataSnapshot.getChildren();
                Log.d("TAG", String.valueOf(dataSnapshot.getChildrenCount()));

                for (DataSnapshot hijo : espanshot) {

                    listaProductos.add(hijo.getValue(Producto.class));

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
        Log.d("TAG", listaProductos.toString());
        return listaProductos;
    }
}
