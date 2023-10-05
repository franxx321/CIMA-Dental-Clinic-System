package Managers;

import DAOs.MySQLImplementations.TurnoDAOImpl;
import Objetos.Paciente;
import Objetos.Prestacion;
import Objetos.Turno;
import Objetos.TurnoPrestacion;

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

    public void addTurno(Prestacion prestacion, String profesionalCB, long pacienteDni, long horaInicioLong, long horaFinLong){
        Turno turno = new Turno();
        TurnoPrestacion turnoPrestacion = new TurnoPrestacion();
        Date horaInicio = new Date(horaInicioLong);
        Date horaFin = new Date(horaFinLong);
        int idProfesional = 0;
        int idPrestacion = 0;
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

        idPrestacion = PrestacionManager.getInstance().idByName(prestacion.getNombre());
        if(idPrestacion == -1){
            error = true;
            errorString = errorString + "La prestacion no esta cargada";
        } else{
            turnoPrestacion.setIdPrestacion(idPrestacion);
            turnoPrestacion.setIdTurno(turno.getId());
        }

        TurnoPrestacionManager.getInstance().addTurnoPrestacion(turnoPrestacion);
        TurnoDAOImpl.getInstance().register(turno);

        List<Turno> overlappingTurnos = TurnoDAOImpl.getInstance().getOverlappingturnos(idProfesional,horaInicio,horaFin);
        if(overlappingTurnos == null){
            //TODO si llegamos aca es por que hubo una excepcion, hay que ver que hacemos
        } else if (overlappingTurnos.get(0).getId()==-1) {
            //OK para agregar el turno
        }


    }

    public void deleteTurno(long dni){

    }

    public List<Prestacion> getAllPrestaciones(){
        return PrestacionManager.getInstance().getAllPrestacion();

    }

}
