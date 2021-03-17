package com.pass.communify;

import java.io.Serializable;

public class Historial implements Serializable {

    private String producto, solicitadoenviado;
    private int foto;
    private float calificacion;
    private Categoria cat;

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public Categoria getCat() {
        return cat;
    }

    public void setCat(Categoria cat) {
        this.cat = cat;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getSolicitadoenviado() {
        return solicitadoenviado;
    }

    public void setSolicitadoenviado(String solicitadoenviado) {
        this.solicitadoenviado = solicitadoenviado;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public Historial(String producto, String solicitadoenviado, int foto) {
        this.producto = producto;
        this.solicitadoenviado = solicitadoenviado;
        this.foto = foto;
    }

    public Historial() {
    }
}
