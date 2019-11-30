package com.daisa.trabajo1;

import android.graphics.Bitmap;

public class Videojuego {

    private float ID;
    private Bitmap imagen;
    private String nombre;
    private String desarrolladora;
    private String genero;
    private int anhoSalida;
    private boolean PC;
    private boolean xbox;
    private boolean playStation;
    private boolean SW;
    private float valoracion;
    private String tienda;
    private boolean favorito;


    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
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

    public String getDesarrolladora() {
        return desarrolladora;
    }

    public void setDesarrolladora(String desarrolladora) {
        this.desarrolladora = desarrolladora;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnhoSalida() {
        return anhoSalida;
    }

    public void setAnhoSalida(int anhoSalida) {
        this.anhoSalida = anhoSalida;
    }

    public boolean isPC() {
        return PC;
    }

    public void setPC(boolean PC) {
        this.PC = PC;
    }

    public boolean isXbox() {
        return xbox;
    }

    public void setXbox(boolean xbox) {
        this.xbox = xbox;
    }

    public boolean isPlayStation() {
        return playStation;
    }

    public void setPlayStation(boolean playStation) {
        this.playStation = playStation;
    }

    public boolean isSW() {
        return SW;
    }

    public void setSW(boolean SW) {
        this.SW = SW;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    @Override
    public String toString(){
        return nombre;
    }



}
