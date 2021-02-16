package com.pass.communify;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager lm = null;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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

            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
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
        mMap.addMarker(new MarkerOptions().position(posicionAct).title("Tu ubicacion"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(posicionAct));
    }
}