package Managers;

import Objetos.Paciente;
import Objetos.Prestacion;
import Objetos.Turno;
import java.util.Date;
import java.util.List;

public class TurnoManager {

    private static TurnoManager turnoManager;

    public static TurnoManager getInstance(){
        if(turnoManager== null){
            turnoManager = new TurnoManager();
        }
        return turnoManager;
    }

    private TurnoManager() {
    }

    public void addTurno(Prestacion prestacion, String profesionalCB, long pacienteDni, long horaInicioTF, long horaFinTF, Date fecha){
        Turno turno = new Turno();
        int idProfesional = 0;
        boolean error = false;
        String errorString = "";
        Paciente paciente = PacienteManager.getInstance().getPatientByDni(pacienteDni);
        if(paciente.getId()==-1){
            error = true;
            errorString= "El paciente no esta cargado";
        }
        else{
            turno.setIdPaciente(paciente.getId());
        }

        idProfesional = ProfesionalManager.getInstance().getIdProfesionalManager(profesionalCB);
        if(idProfesional == -1){
            error = true;
            errorString = errorString + "El profesional no esta cargado";
        } else{
            turno.setIdProfesional(idProfesional);
        }

    }

    public List<Prestacion> getAllPrestaciones(){
        return PrestacionManager.getInstance().getAllPrestacion();

    }


}
