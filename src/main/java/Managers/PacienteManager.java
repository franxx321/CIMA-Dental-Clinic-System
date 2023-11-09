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
        Paciente paciente = new Paciente();
        paciente.setDni(dni);
        return PacienteDAOImpl.getInstance().getByDni(paciente);
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
