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

    public Turno getById(int idTurno){
        Turno turno = new Turno();
        turno.setId(idTurno);
        return TurnoDAOImpl.getInstance().getById(turno);
    }

    public void addTurno(Prestacion prestacion, Profesional profesional, long pacienteDni, long horaInicioLong, long horaFinLong){
        Turno turno = new Turno();
        TurnoPrestacion turnoPrestacion = new TurnoPrestacion();
        Date horaInicio = new Date(horaInicioLong);
        Date horaFin = new Date(horaFinLong);
        int idProfesional = profesional.getId();
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
        turno.setIdProfesional(idProfesional);
        idPrestacion = PrestacionManager.getInstance().idByName(prestacion.getNombre()).getId();
        if(idPrestacion == -1){
            error = true;
            errorString = errorString + "\nLa prestacion no esta cargada";
        } else{
            turnoPrestacion.setIdPrestacion(idPrestacion);
        }
        Turno t1 = new Turno();
        t1.setIdProfesional(idProfesional);
        t1.setHoraInicio(horaInicio);
        t1.setHoraFin(horaFin);
        List<Turno> overlappingTurnos = TurnoDAOImpl.getInstance().getOverlappingturnos(t1);
        t1=null;
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
            Turno turno1= TurnoDAOImpl.getInstance().getByDateProfesional(turno);
            turnoPrestacion.setIdTurno(turno1.getId());
            TurnoPrestacionManager.getInstance().addTurnoPrestacion(turnoPrestacion);
        }
    }

    public void modifyTurno(Turno turno, Turno aux){
        String errString = "";
        boolean error= false;

        List<Turno> turnos= TurnoDAOImpl.getInstance().getOverlappingturnos(turno);
        if(turnos==null){
            //Exepcion
        }
        else if(turnos.get(0).getId()==-1){
            errString= errString + "Ya existe un turno en esa fecha \n";
            error=true;
        }
        if(error){
            throw new CantAddTurno(errString);
        }
        else {
            TurnoDAOImpl.getInstance().modify(turno, aux);
        }
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


    public JTable getCalendar (Profesional profesional, int week){
        List<Turno> turnosList= null;
        if (profesional != null){
            turnosList = TurnoDAOImpl.getInstance().profesionalFutureApointments(profesional.getId() ,week);
        }
        return CalendarTableGenerator.getInstance().generateTable(turnosList,week);
    }

    public JTable getFuturePatientAppointments(long dniPaciente){
        List<Turno> turnoList = null;
        Paciente paciente = null;
        if(dniPaciente!=-1){
            paciente = PacienteManager.getInstance().getPatientByDni(dniPaciente);
            Turno t = new Turno();
            t.setIdPaciente(paciente.getId());
            turnoList = TurnoDAOImpl.getInstance().getPatientFutureApointments(t);
        }
        return TurnosbyPacienteTableGenerator.getInstance().generateTable(turnoList,paciente);
    }

    public JTable getPrestacionesByTurnoTable(Turno turno){
        return PrestacionesByTurnoTableGenerator.getInstance().generateTable(turno);
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
    public List<Turno> getByProfesional(int idProfesional){
        Turno turno = new Turno();
        turno.setIdProfesional(idProfesional);
        return TurnoDAOImpl.getInstance().getByIdProfesional(turno);
    }


}
