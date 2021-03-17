package com.pass.communify;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;

public class AniadirProducto extends AppCompatActivity {
    private ImageView imagen;
    private Context context;
    private Uri imageUri;
    private Button btnAniadir;
    private EditText etTitulo;
    private EditText etDescripcion;
    private String categoría;
    private LocationManager lm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir_producto);
        imagen = findViewById(R.id.imagenProducto);
        etTitulo = findViewById(R.id.etTitulo);
        etDescripcion = findViewById(R.id.DescripcionProducto);
        Intent intent = getIntent();

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);


            }
        });
        btnAniadir = findViewById(R.id.btnAniadir);
        btnAniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LatLng ubicacion = new LatLng(0, 0);
                Producto p = new Producto(LoginActivity.userEmail, ubicacion, etTitulo.getText().toString(), etDescripcion.getText().toString(), imageUri.toString());
                FirebaseConnection.grabarObjeto(LoginActivity.userEmail, p);
                FirebaseConnection.grabarFoto(Uri.parse(p.getUri()), p.getEmail());
            }
        });
    }

    /**
     * Este método es para la respuesta de la elección de la imagen, setea el ImageView con la imagen seleccionada
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (null != data) {
            imageUri = data.getData();
            Log.d("URI", imageUri.toString());
            imagen.setImageURI(imageUri);
            //Do whatever that you desire here. or leave this blank

        }

    }

}