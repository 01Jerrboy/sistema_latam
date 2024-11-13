package com.latam.airlines.modelo;

public class Avion {

    private int idAvion;
    private String modelo;
    private int capacidad;
    private String fabricante;
    private int annioFabricacion;

    public Avion() {
    }

    public Avion(int idAvion) {
        this.idAvion = idAvion;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getAnnioFabricacion() {
        return annioFabricacion;
    }

    public void setAnnioFabricacion(int annioFabricacion) {
        this.annioFabricacion = annioFabricacion;
    }

}
