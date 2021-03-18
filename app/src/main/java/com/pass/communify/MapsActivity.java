package com.pass.communify;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, FirebaseConnection.iRecuperarDatos {

    private GoogleMap mMap;
    private int zoomLevel = 8; //De 0 a 21, 0 max zoom
    LocationManager lm = null;
    private Toast toast;
    private ProgressBar progressBar;
    FirebaseConnection.iRecuperarDatos interfaz_recuperarDatos = this;


    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        progressBar = findViewById(R.id.progressBar);
        FirebaseConnection.pedirUsuariosFirebase(interfaz_recuperarDatos);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        toast = Toast.makeText(this, "Calculando posicion...", Toast.LENGTH_LONG);
        toast.setDuration(999999999);
        toast.show();
            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            chequearPermisos();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 99) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                obtenerUbicacion();
            }
        }
    }

    private void obtenerUbicacion() {
        LocationListener oyente_localizaciones = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Log.d("Localizacion: ", location.getLatitude() + ", " + location.getLongitude());
                cambiarMapa(location.getLatitude(),location.getLongitude());
                lm.removeUpdates(this);

            }

        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 0, oyente_localizaciones);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void chequearPermisos() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] permisos = {Manifest.permission.ACCESS_FINE_LOCATION};
            requestPermissions(permisos, 99);

            return;
        } else {
            obtenerUbicacion();

        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    private void cambiarMapa(Double lat, Double longi) {
        LatLng posicionAct = new LatLng(lat, longi);
        mMap.addMarker(new MarkerOptions().position(posicionAct).title(LoginActivity.currentUser.getEmail()));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(posicionAct, zoomLevel));
        toast.cancel();
        progressBar.setVisibility(View.INVISIBLE);
        Posiciones p = new Posiciones(lat,longi);

        FirebaseConnection.grabarPosicion(LoginActivity.currentUser.getEmail(),p);
        //FirebaseConnection.grabarPosicion(posicionAct);



    }





    @Override
    public void recuperarUsuarios(ArrayList<Producto> lista_usuarios) {
        Log.d("DESDE MAIN", lista_usuarios.toString());
        for (Producto i:lista_usuarios) {
            LatLng pos = new LatLng(i.getcPosiciones().getLat(), i.getcPosiciones().getLng());
            mMap.addMarker(new MarkerOptions().position(pos).title(i.getNombreUsuario()));
        }

    }
}

//https://play.google.com/store/apps/details?id=com.pass.communify
