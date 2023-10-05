package Managers;

import DAOs.MySQLImplementations.TurnoDAOImpl;
import Objetos.*;
import Utils.Exceptions.CantAddTurno;

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
            errorString= "\nEl paciente no esta cargado";
        }
        else{
            turno.setIdPaciente(paciente.getId());
        }

        idProfesional = ProfesionalManager.getInstance().getIdProfesionalManager(profesionalCB);
        if(idProfesional == -1){
            error = true;
            errorString = errorString + "\nEl profesional no esta cargado";
        } else{
            turno.setIdProfesional(idProfesional);
        }

        idPrestacion = PrestacionManager.getInstance().idByName(prestacion.getNombre());
        if(idPrestacion == -1){
            error = true;
            errorString = errorString + "\nLa prestacion no esta cargada";
        } else{
            turnoPrestacion.setIdPrestacion(idPrestacion);
            //turnoPrestacion.setIdTurno(turno.getId());
        }


        List<Turno> overlappingTurnos = TurnoDAOImpl.getInstance().getOverlappingturnos(idProfesional,horaInicio,horaFin);
        if(overlappingTurnos == null){
            //TODO si llegamos aca es por que hubo una excepcion, hay que ver que hacemos
        } else if (overlappingTurnos.get(0).getId()!=-1) {
            error=true;
            errorString= errorString+ "\nYa existe un turno en ese horario";
        }
        else{
            turno.setHoraInicio(horaInicio);
            turno.setHoraFin(horaFin);
        }
        if(error){
            throw new CantAddTurno(errorString);
        }
        else {
            TurnoDAOImpl.getInstance().register(turno);
            Turno turno1= this.getByDateProfesional(turno.getHoraInicio(),turno.getHoraFin(),turno.getIdProfesional());
            turnoPrestacion.setIdTurno(turno1.getId());
            TurnoPrestacionManager.getInstance().addTurnoPrestacion(turnoPrestacion);
        }
    }

    public void deleteTurno(int id_profesional,Date horaInicio,Date horaFin){
        TurnoDAOImpl.getInstance().delete(TurnoDAOImpl.getInstance().getByDateProfesional(horaInicio,horaFin,id_profesional));
    }

    public List<Prestacion> getAllPrestaciones(){
        return PrestacionManager.getInstance().getAllPrestacion();
    }

    public List<Profesional> getAllProfesional(){

        return ProfesionalManager.getInstance().getAll();
    }

    public Turno getByDateProfesional(Date horaInicio, Date horaFin,int idProfesional){
        return TurnoDAOImpl.getInstance().getByDateProfesional(horaInicio,horaFin,idProfesional);
    }


}
