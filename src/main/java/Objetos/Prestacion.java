package Objetos;

import java.util.List;

public class Prestacion {
    //TODO asociar con profesional a traves de una clase que no se como se llama

    private int id;

    private String nombre;

    private String descripcion;

    private boolean bien;



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



    public boolean isBien() {
        return bien;
    }

    public void setBien(boolean bien) {
        this.bien = bien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
