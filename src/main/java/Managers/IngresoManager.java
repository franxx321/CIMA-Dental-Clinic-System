package Managers;

import DAOs.MySQLImplementations.IngresoDAOImpl;
import DAOs.MySQLImplementations.TurnoDAOImpl;
import Objetos.*;
import Utils.TableGenerator.TurnosByProfesionalTableGenerator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class IngresoManager {

    private static IngresoManager ingresoManager;

    public static IngresoManager getInstance(){
        if(ingresoManager==null ){
            ingresoManager = new IngresoManager();
        }
        return ingresoManager;
    }
    private IngresoManager(){

    }
    public List<Profesional> getAllProfesional(){
        return ProfesionalManager.getInstance().getAll();
    }

    public JTable getTurnosByProfesionalTable(Profesional profesional){
        List<Turno> turnos = null;
        if(profesional !=null){
            turnos = TurnoManager.getInstance().getByProfesional(profesional.getId());
        }

        return TurnosByProfesionalTableGenerator.getInstance().generateTable(turnos,profesional);
    }

    public Profesional getProfesionalById(int profesionalId){
        return ProfesionalManager.getInstance().getProfesionalById(profesionalId);
    }
    public Paciente getPacienteById(int pacienteId){
        return PacienteManager.getInstance().getPacienteById(pacienteId);
    }

    public List<ObraSocial> getObraSocialByIdPaciente(int idPaciente){
        List<PacienteObraSocial> pacienteObraSocialList = PacienteObraSocialManager.getInstance().getByIdPaciente(idPaciente);
        List<ObraSocial> obraSocialList = new ArrayList<>();
        for (PacienteObraSocial pos: pacienteObraSocialList) {
            ObraSocial oS = ObraSocialManager.getInstance().getById(pos.getIdObraSocial());
            if(oS.getId()!=-1){
                obraSocialList.add(oS);
            }
        }
        return obraSocialList;
    }

    public void add(java.sql.Date fecha,int idPaciente,int idProfesional, int idTurno, int idObraSocial, float monto, String descripcion){
        Ingreso ingreso = new Ingreso();
        ingreso.setFecha(fecha);
        ingreso.setDescripcion(descripcion);
        ingreso.setIdPaciente(idPaciente);
        ingreso.setIdObraSocial(idObraSocial);
        ingreso.setIdProfesional(idProfesional);
        ingreso.setIdTurno(idTurno);
        ingreso.setMonto(monto);
        IngresoDAOImpl.getInstance().register(ingreso);
    }

    public List<Float> getIngresoByProfesional(Ingreso ingreso){
        return IngresoDAOImpl.getInstance().getIngresoByProfesional(ingreso);
    }

}
