package Objetos;

import java.util.List;

public class Profesional {

    // TODO asociar con prestacion a traves de una clase a la que no se que nombre ponerle

     private String nombre;

     private String apellido;

     private String telefono;

     private String matricula;

     private List<Gasto> gastos;

     private List<Turno> turnos;

     private List <Ingreso> ingresos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Gasto> getGastos() {
        return gastos;
    }


    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }

    public List<Ingreso> getIngresos() {
        return ingresos;
    }

    public void setIngresos(List<Ingreso> ingresos) {
        this.ingresos = ingresos;
    }


    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
