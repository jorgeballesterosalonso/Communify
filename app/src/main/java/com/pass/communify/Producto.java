package com.pass.communify;

import android.net.Uri;


public class Producto {
    private String titulo;
    private String descripcion;
    private Uri uri;

    public Producto(String titulo, String descripcion, Uri uri) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", uri=" + uri +
                '}';
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
