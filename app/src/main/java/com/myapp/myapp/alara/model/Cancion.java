package com.myapp.myapp.alara.model;
import java.util.Date;

public class Cancion {

    private Long id;
    private String nombre;
    private Date fecha;
    private String contenido;
    private String url;
    private boolean estadoPublico;
    private Artista artista;
    private Genero genero;
    private Disco disco;

    public Cancion() {
        // TODO Auto-generated constructor stub
    }

    public Cancion(Long id, String nombre,Date fecha,String contenido,String url,boolean estadoPublico,
                   Artista artista,Genero genero,Disco disco) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.url=url;
        this.contenido = contenido;
        this.estadoPublico = estadoPublico;
        this.artista = artista;
        this.genero = genero;
        this.disco = disco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public boolean isEstadoPublico() {
        return estadoPublico;
    }

    public void setEstadoPublico(boolean estadoPublico) {
        this.estadoPublico = estadoPublico;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Disco getDisco() {
        return disco;
    }

    public void setDisco(Disco disco) {
        this.disco = disco;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }





}
