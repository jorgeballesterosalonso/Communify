package com.pass.communify;

import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;


public class Producto {
    private String nombreUsuario, email;
    private LatLng posicion;
    private Double lat;
    private String titulo;
    private String descripcion;
    private Uri uri;

    public Producto(String nombreUsuario, LatLng posicion,Double lat ) {
        this.nombreUsuario = nombreUsuario;
        this.posicion = posicion;
        this.lat = lat;
    }



    public Producto() {
    }

    public Producto(String titulo, String descripcion, Uri uri) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.uri = uri;
    }

    public Producto(String nombreUsuario, String email, String titulo, String descripcion, Uri uri) {
        this.nombreUsuario = nombreUsuario;
        this.email = email;
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

    @Override
    public String toString() {
        return "Producto{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", email='" + email + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", uri=" + uri +
                '}';
    }
}
