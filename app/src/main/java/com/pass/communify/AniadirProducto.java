package com.pass.communify;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class AniadirProducto extends AppCompatActivity implements Serializable {
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

                LocationListener oyente_localizaciones = new LocationListener() {
                    @Override
                    public void onLocationChanged(@NonNull Location location) {

                        LatLng ubicacion = new LatLng(location.getLatitude(), location.getLongitude());
                        Producto p = new Producto(LoginActivity.userEmail, ubicacion, etTitulo.getText().toString(), etDescripcion.getText().toString(), imageUri, (Categoria) intent.getSerializableExtra("categoria"));
                        Log.d("Localizacion: ", location.getLatitude() + ", " + location.getLongitude());

                    }

                };
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 10, oyente_localizaciones);
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