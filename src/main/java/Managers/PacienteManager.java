package Managers;

import DAOs.MySQLImplementations.PacienteDAOImpl;
import DAOs.MySQLImplementations.ProfesionalDAOImpl;
import Objetos.Paciente;
import Objetos.Profesional;

import java.util.List;

public class PacienteManager {

    private static PacienteManager pacienteManager;

    public static PacienteManager getInstance(){
        if(pacienteManager== null){
            pacienteManager= new PacienteManager();
        }
        return pacienteManager;
    }

    private PacienteManager() {
    }

    public Paciente getPatientByDni(long dni){
        return PacienteDAOImpl.getInstance().getByDni(dni);
    }

    public List<Paciente> getAll(){
        return PacienteDAOImpl.getInstance().obtain();
    }

    public Paciente getPacienteById(int pacienteId){
        Paciente p = new Paciente();
        p.setId(pacienteId);
        return PacienteDAOImpl.getInstance().getById(p);
    }

}
