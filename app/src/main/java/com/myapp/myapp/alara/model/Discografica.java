package com.myapp.myapp.alara.model;
public class Discografica {

    private Long id;
    private String correoEmpresarial;
    private String pais;

    public Discografica() {
    }

    public Discografica(Long id, String correoEmpresarial, String pais) {
        super();
        this.id = id;
        this.correoEmpresarial = correoEmpresarial;
        this.pais = pais;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorreoEmpresarial() {
        return correoEmpresarial;
    }

    public void setCorreoEmpresarial(String correoEmpresarial) {
        this.correoEmpresarial = correoEmpresarial;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }



}
