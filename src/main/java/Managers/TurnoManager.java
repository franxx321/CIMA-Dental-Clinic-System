package Managers;

import DAOs.MySQLImplementations.TurnoDAOImpl;
import Objetos.*;
import Utils.Exceptions.CantAddTurno;
import Utils.TableGenerator.CalendarTableGenerator;
import Utils.TableGenerator.PrestacionesByTurnoTableGenerator;
import Utils.TableGenerator.TurnosbyPacienteTableGenerator;

import javax.swing.*;
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

        Profesional profesional = ProfesionalManager.getInstance().getProfesionalByName(profesionalCB);
        idProfesional= profesional.getId();
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

    public void modifyTurno(Turno turno, Turno aux){
        TurnoDAOImpl.getInstance().modify(turno, aux);
    }

    public void deleteTurno(Turno turno){
        TurnoPrestacionManager.getInstance().deleteByTurno(turno);
        TurnoDAOImpl.getInstance().delete(turno);
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

    public JTable getCalendar (String nombreProfesional, int week){
        List<Turno> turnosList= null;
        if (nombreProfesional != null){
            Profesional profesional = ProfesionalManager.getInstance().getProfesionalByName(nombreProfesional);
            turnosList = TurnoDAOImpl.getInstance().profesionalFutureApointments(profesional.getId() ,week);
        }
        return CalendarTableGenerator.getInstance().generateTable(turnosList,week);
    }

    public JTable getFuturePatientAppointments(long dniPaciente){
        List<Turno> turnoList = null;
        Paciente paciente = null;
        if(dniPaciente!=-1){
            paciente = PacienteManager.getInstance().getPatientByDni(dniPaciente);
            turnoList = TurnoDAOImpl.getInstance().getPatientFutureApointments(paciente.getId());
        }
        return TurnosbyPacienteTableGenerator.getInstance().generateTable(turnoList,paciente);
    }

    public JTable getPrestacionesByTurnoTable(Turno turno){
        return PrestacionesByTurnoTableGenerator.getInstance().generateTable(turno);
    }

    public Profesional getProfesionalByName(String profesional){
        return ProfesionalManager.getInstance().getProfesionalByName(profesional);
    }

    public List<TurnoPrestacion> getPrestacionesByTurno(Turno turno){
        return TurnoPrestacionManager.getInstance().getByTurnoId(turno.getId());
    }

    public  void deleteTurnoPrestacionByTurno(Turno turno){
        TurnoPrestacionManager.getInstance().deleteByTurno(turno);
    }

    public void addTurnoPrestacion(TurnoPrestacion turnoPrestacion){
        TurnoPrestacionManager.getInstance().addTurnoPrestacion(turnoPrestacion);
    }




}
