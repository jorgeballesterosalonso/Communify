package com.pass.communify;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;


public class Producto implements Serializable {

    private String nombreUsuario;
    private String email;
    private LatLng posicion;
    private  Posiciones cPosiciones;
    private Double lat;
    private String titulo;
    private String descripcion;
    private String uri;
    private Categoria categoria;
    private ArrayList<Posiciones> lista_posiciones;

    public Producto() {
    }

    public Producto(Posiciones cPosiciones) {
        this.cPosiciones = cPosiciones;
    }

    public Producto(String nombreUsuario, Posiciones cPosiciones) {
        this.nombreUsuario = nombreUsuario;
        this.cPosiciones = cPosiciones;
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

    public Posiciones getcPosiciones() {
        return cPosiciones;
    }

    public void setcPosiciones(Posiciones cPosiciones) {
        this.cPosiciones = cPosiciones;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ArrayList<Posiciones> getLista_posiciones() {
        return lista_posiciones;
    }

    public void setLista_posiciones(ArrayList<Posiciones> lista_posiciones) {
        this.lista_posiciones = lista_posiciones;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", email='" + email + '\'' +
                ", posicion=" + posicion +
                ", cPosiciones=" + cPosiciones +
                ", lat=" + lat +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", uri='" + uri + '\'' +
                ", categoria=" + categoria +
                ", lista_posiciones=" + lista_posiciones +
                '}';
    }
}
