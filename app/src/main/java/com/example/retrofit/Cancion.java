package com.example.retrofit;

import java.util.Date;

public class Cancion {
    private int id;
    private String nombre;
    private String url;
    private String contenido;
    private int artistaId;
    private int discoId;
    private boolean estadoPublico;
    private Date fecha;
    private int generoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre  (String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getArtistaId() {
        return artistaId;
    }

    public void setArtistaId(int artistaId) {
        this.artistaId = artistaId;
    }

    public int getDiscoId() {
        return discoId;
    }

    public void setDiscoId(int discoId) {
        this.discoId = discoId;
    }

    public boolean getEstadoPublico() {
        return estadoPublico;
    }

    public void setEstadoPublico(boolean estadoPublico) {
        this.estadoPublico = estadoPublico;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getGeneroId() {
        return generoId;
    }

    public void setGeneroId(int generoId) {
        this.generoId = generoId;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
