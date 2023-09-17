package Objetos;

import java.util.List;

public class Prestacion {
    //TODO asociar con profesional a traves de una clase que no se como se llama

    private String nombre;

    private String descripcion;

    private List<ObraSocial> obrasSociales;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<ObraSocial> getObrasSociales() {
        return obrasSociales;
    }

    public void setObrasSociales(List<ObraSocial> obrasSociales) {
        this.obrasSociales = obrasSociales;
    }
}
