package com.latam.airlines.modelo;

public class Vuelo {

    private int idVuelo;
    private Aeropuerto aeropuerto;
    private ClaseVuelo claseVuelo;
    private Avion avion;
    private Destino destinoOrigen;
    private Destino destinoRegreso;
    private String fechaSalida;
    private String horaSalida;
    private String fechaRegreso;
    private String horaRegreso;
    private String tiempoVuelo;
    private String tipoVuelo;
    private double costoVuelo;

    public Vuelo() {
        destinoOrigen = new Destino();
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public Aeropuerto getAeropuerto() {
        return aeropuerto;
    }

    public void setAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }

    public ClaseVuelo getClaseVuelo() {
        return claseVuelo;
    }

    public void setClaseVuelo(ClaseVuelo claseVuelo) {
        this.claseVuelo = claseVuelo;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Destino getDestinoOrigen() {
        return destinoOrigen;
    }

    public void setDestinoOrigen(Destino destinoOrigen) {
        this.destinoOrigen = destinoOrigen;
    }

    public Destino getDestinoRegreso() {
        return destinoRegreso;
    }

    public void setDestinoRegreso(Destino destinoRegreso) {
        this.destinoRegreso = destinoRegreso;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getFechaRegreso() {
        return fechaRegreso;
    }

    public void setFechaRegreso(String fechaRegreso) {
        this.fechaRegreso = fechaRegreso;
    }

    public String getHoraRegreso() {
        return horaRegreso;
    }

    public void setHoraRegreso(String horaRegreso) {
        this.horaRegreso = horaRegreso;
    }

    public String getTiempoVuelo() {
        return tiempoVuelo;
    }

    public void setTiempoVuelo(String tiempoVuelo) {
        this.tiempoVuelo = tiempoVuelo;
    }

    public String getTipoVuelo() {
        return tipoVuelo;
    }

    public void setTipoVuelo(String tipoVuelo) {
        this.tipoVuelo = tipoVuelo;
    }

    public double getCostoVuelo() {
        return costoVuelo;
    }

    public void setCostoVuelo(double costoVuelo) {
        this.costoVuelo = costoVuelo;
    }

    @Override
    public String toString() {
        return "Vuelo{" + "idVuelo=" + idVuelo + ", aeropuerto=" + aeropuerto + ", claseVuelo=" + claseVuelo + ", avion=" + avion + ", destinoOrigen=" + destinoOrigen + ", destinoRegreso=" + destinoRegreso + ", fechaSalida=" + fechaSalida + ", horaSalida=" + horaSalida + ", fechaRegreso=" + fechaRegreso + ", horaRegreso=" + horaRegreso + ", tiempoVuelo=" + tiempoVuelo + ", tipoVuelo=" + tipoVuelo + ", costoVuelo=" + costoVuelo + '}';
    }

}
