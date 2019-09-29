package com.myapp.myapp.alara.model;
import java.util.Date;

public class Artista {

    private Long id;
    private String sexo;
    private int edad;
    private String nombreArtistico;
    private String correo;
    private String contraseña;
    private boolean permisoNotificaciones;
    private Date fecha;
    private boolean activo;

    private Discografica discografica;

    private Genero genero;

    public Artista() {
    }

    public Artista(Long id, String sexo,int edad, String nombreArtistico,String correo,String contraseña,
                   boolean permisoNotificaciones,Date fecha,boolean activo,Discografica discografica,Genero genero) {
        super();
        this.id = id;
        this.sexo = sexo;
        this.nombreArtistico = nombreArtistico;
        this.correo = correo;
        this.contraseña = contraseña;
        this.permisoNotificaciones = permisoNotificaciones;
        this.fecha = fecha;
        this.activo = activo;
        this.edad =edad;
        this.discografica=discografica;
        this.genero=genero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean isPermisoNotificaciones() {
        return permisoNotificaciones;
    }

    public void setPermisoNotificaciones(boolean permisoNotificaciones) {
        this.permisoNotificaciones = permisoNotificaciones;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Discografica getDiscografica() {
        return discografica;
    }

    public void setDiscografica(Discografica discografica) {
        this.discografica = discografica;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }


}
