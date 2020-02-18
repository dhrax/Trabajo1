package com.daisa.trabajo1;

public class Opinion {

    private float ID;
    private String autor;
    private String videojuego;
    private float valoracion;
    private String comentario;

    public Opinion(String autor, String videojuego, float valoracion, String comentario) {
        this.autor = autor;
        this.videojuego = videojuego;
        this.valoracion = valoracion;
        this.comentario = comentario;
    }

    public Opinion(float ID, String autor, String videojuego, float valoracion, String comentario) {
        this.ID = ID;
        this.autor = autor;
        this.videojuego = videojuego;
        this.valoracion = valoracion;
        this.comentario = comentario;
    }

    public Opinion() {
    }

    public float getID() {
        return ID;
    }

    public void setID(float ID) {
        this.ID = ID;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getVideojuego() {
        return videojuego;
    }

    public void setVideojuego(String videojuego) {
        this.videojuego = videojuego;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
