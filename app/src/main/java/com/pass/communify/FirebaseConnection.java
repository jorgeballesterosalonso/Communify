package com.pass.communify;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FirebaseConnection {
    private static final FirebaseDatabase database = FirebaseDatabase.getInstance("https://communify-78b34-default-rtdb.europe-west1.firebasedatabase.app/");
    private static final FirebaseStorage storage = FirebaseStorage.getInstance("gs://communify-392d2.appspot.com");

    public static void grabarPosicion(String nombreUsuario, Posiciones posicion) {

        Log.d("TAG", nombreUsuario);
        Log.d("TAG", posicion.toString());


        Producto c1 = new Producto(nombreUsuario,posicion);

        DatabaseReference myRef = database.getReference("User");;

        myRef.push().setValue(c1);
        //myRef.push().setValue(posicion);

    }
    public static void pedirUsuariosFirebase(iRecuperarDatos callback) {
        DatabaseReference myRef = database.getReference("User");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Producto> usuarios = new ArrayList<>();
                Iterable<DataSnapshot> datos = snapshot.getChildren();
                while (datos.iterator().hasNext()) {
                    DataSnapshot d = datos.iterator().next();
                    Producto u = d.getValue(Producto.class);
                    usuarios.add(u);
                    Log.d("UsuariosFirebase", usuarios.toString());
                }
                callback.recuperarUsuarios((ArrayList<Producto>) usuarios);
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public interface iRecuperarDatos {
        public void recuperarUsuarios(ArrayList<Producto> lista_usuarios);
    }
    public static void grabarObjeto(String userName, Producto p){
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://communify-392d2-default-rtdb.firebaseio.com/");

        Log.d("TAG","Grabando Objeto...");
        DatabaseReference referencia = database.getReference("Objeto").child(userName.replace('@',' ').replace('.',' '));
        referencia.push().setValue(p);
    }

    public static void grabarFoto(Uri uri, String userName) {
        FirebaseStorage storage = FirebaseStorage.getInstance("gs://communify-392d2.appspot.com");
        Log.d("TAG","Grabando foto...");
        StorageReference storageRef = storage.getReference();
        UploadTask referenciaFoto = storageRef.child(userName + uri.toString()).putFile(uri);
    }



}
