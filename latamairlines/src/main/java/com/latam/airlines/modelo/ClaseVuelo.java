package com.latam.airlines.modelo;

public class ClaseVuelo {

    private int idClaseVuelo;
    private String descripcion;

    public ClaseVuelo() {
    }

    public ClaseVuelo(int idClaseVuelo) {
        this.idClaseVuelo = idClaseVuelo;
    }

    public int getIdClaseVuelo() {
        return idClaseVuelo;
    }

    public void setIdClaseVuelo(int idClaseVuelo) {
        this.idClaseVuelo = idClaseVuelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
