package Managers;

import DAOs.MySQLImplementations.PacienteObraSocialDAOImpl;
import Objetos.PacienteObraSocial;

import java.util.List;

public class PacienteObraSocialManager {

    private static PacienteObraSocialManager pacienteObraSocialManager;

    public static PacienteObraSocialManager getInstance(){
        if(pacienteObraSocialManager==null){
            pacienteObraSocialManager = new PacienteObraSocialManager();
        }
        return pacienteObraSocialManager;
    }

    private PacienteObraSocialManager(){

    }

    public List<PacienteObraSocial> getByIdPaciente(int idPaciente){
        PacienteObraSocial pacienteObraSocial = new PacienteObraSocial();
        pacienteObraSocial.setIdPaciente(idPaciente);
        return PacienteObraSocialDAOImpl.getInstance().getByPacienteId(pacienteObraSocial);
    }
}
