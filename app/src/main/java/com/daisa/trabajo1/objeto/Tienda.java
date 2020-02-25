package com.daisa.trabajo1.objeto;

public class Tienda {

    private float ID;
    private String nombre;
    private double latitud, longitud;
    private long telefono;

    public Tienda(String nombre, double latitud, double longitud) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Tienda(float ID, String nombre, double latitud, double longitud) {
        this.ID = ID;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Tienda() {
    }

    public float getID() {
        return ID;
    }

    public void setID(float ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }
}
