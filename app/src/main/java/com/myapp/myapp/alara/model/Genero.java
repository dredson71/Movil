package com.myapp.myapp.alara.model;

public class Genero {

    private Long id;
    private String detalle;

    public Genero() {
    }

    public Genero(Long id, String detalle) {
        super();
        this.id = id;
        this.detalle = detalle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }



}
