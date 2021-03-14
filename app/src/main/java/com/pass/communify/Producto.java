package com.pass.communify;

import android.net.Uri;

import java.io.Serializable;


public class Producto implements Serializable {
    private String titulo;
    private String descripcion;
    private Uri uri;
    private boolean esCompartido;
    private Categoría categoria;

    public Producto(String titulo, String descripcion, Uri uri, boolean esCompartido, Categoría categoria) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.uri = uri;
        this.esCompartido = esCompartido;
        this.categoria = categoria;
    }

    public Categoría getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoría categoria) {
        this.categoria = categoria;
    }

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
                ", esCompartido=" + esCompartido +
                ", categoria=" + categoria +
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
