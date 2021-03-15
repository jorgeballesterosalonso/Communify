package com.pass.communify;

import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;


public class Producto {
    private String nombreUsuario;
    private String email;
    private LatLng posicion;
    private Double lat;
    private String titulo;
    private String descripcion;
    private Uri uri;

    public Producto(String nombreUsuario, LatLng posicion) {
        this.nombreUsuario = nombreUsuario;
        this.posicion = posicion;
    }

    public Producto(String titulo, String descripcion, Uri uri) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.uri = uri;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LatLng getPosicion() {
        return posicion;
    }

    public void setPosicion(LatLng posicion) {
        this.posicion = posicion;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
