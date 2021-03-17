package com.pass.communify;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;


public class Producto implements Serializable {

    private String nombreUsuario;
    private String email;
    private LatLng posicion;
    private Double lat;
    private String titulo;
    private String descripcion;
    private String uri;
    private Categoria categoria;

    public Producto() {
    }

    public Producto(String email, LatLng posicion, String titulo, String descripcion, String uri, Categoria categoria) {
        this.email = email;
        this.posicion = posicion;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.uri = uri;
        this.categoria = categoria;

    }

    public Producto(String titulo, String descripcion, String uri, Categoria categoria) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.uri = uri;
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


    public Producto(String nombreUsuario, LatLng posicion) {
        this.nombreUsuario = nombreUsuario;
        this.posicion = posicion;
    }

    public Producto(String titulo, String descripcion, String uri) {
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
