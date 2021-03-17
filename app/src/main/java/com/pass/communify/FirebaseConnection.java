package com.pass.communify;

import android.net.Uri;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.Serializable;

public class FirebaseConnection {
    private static final FirebaseDatabase database = FirebaseDatabase.getInstance("https://communify-392d2-default-rtdb.firebaseio.com/");
    private static final FirebaseStorage storage = FirebaseStorage.getInstance("gs://communify-392d2.appspot.com");

    public static void grabarPosicion(String nombreUsuario, LatLng posicion) {
        Log.d("TAG", nombreUsuario);
        Log.d("TAG", posicion.toString());


        Producto c1 = new Producto(nombreUsuario, posicion);

        DatabaseReference myRef = database.getReference("User").child(nombreUsuario.split("@")[0] + nombreUsuario.split("@")[1].replace('.', ' '));

        myRef.push().setValue(c1);
        //myRef.push().setValue(posicion);

    }

    public static void grabarObjeto(String userName, Producto p){
        Log.d("TAG","Grabando Objeto...");
        DatabaseReference referencia = database.getReference("Objeto").child(userName.replace('@',' ').replace('.',' '));
        referencia.push().setValue(p);
    }

    public static void grabarFoto(Uri uri, String userName) {
        Log.d("TAG","Grabando foto...");
        StorageReference storageRef = storage.getReference();
        UploadTask referenciaFoto = storageRef.child(userName + uri.toString()).putFile(uri);
    }
}
