package com.pass.communify;

import android.net.Uri;


public class Producto {
    private String titulo;
    private String descripcion;
    private Uri uri;
    private boolean esCompartido;

    public boolean isEsCompartido() {
        return esCompartido;
    }

    public void setEsCompartido(boolean esCompartido) {
        this.esCompartido = esCompartido;
    }

    public Producto(String titulo, String descripcion, Uri uri, boolean esCompartido) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.uri = uri;
        this.esCompartido = esCompartido;
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
