package com.latam.airlines.modelo;

public class Aeropuerto {

    private int idAeropuerto;
    private String nombre;
    private String codigo;

    public Aeropuerto() {
    }

    public Aeropuerto(int idAeropuerto) {
        this.idAeropuerto = idAeropuerto;
    }

    public int getIdAeropuerto() {
        return idAeropuerto;
    }

    public void setIdAeropuerto(int idAeropuerto) {
        this.idAeropuerto = idAeropuerto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
