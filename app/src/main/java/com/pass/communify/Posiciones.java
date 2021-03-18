package com.pass.communify;

import com.google.android.gms.maps.model.LatLng;

public class Posiciones {
    private Double lat, lng;

    public Posiciones() {
    }
    public Posiciones(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "posicion{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
