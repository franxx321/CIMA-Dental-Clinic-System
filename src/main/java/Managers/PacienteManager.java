package Managers;

import DAOs.MySQLImplementations.PacienteDAOImpl;
import Objetos.Paciente;

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


}
