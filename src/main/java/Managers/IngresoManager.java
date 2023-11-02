package Managers;

import Objetos.Paciente;
import Objetos.Profesional;
import Objetos.Turno;
import Objetos.TurnoPrestacion;
import Utils.TableGenerator.TurnosByProfesionalTableGenerator;

import javax.swing.*;
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

    public JTable getTurnosByProfesionalTable(String profesional){
        Profesional auxprofesional =ProfesionalManager.getInstance().getProfesionalByName(profesional);
        List<Turno> turnos = TurnoManager.getInstance().getByProfesional(auxprofesional.getId());
        return TurnosByProfesionalTableGenerator.getInstance().generateTable(turnos,auxprofesional);
    }

    public Profesional getProfesionalById(int profesionalId){
        return ProfesionalManager.getInstance().getProfesionalById(profesionalId);
    }
    public Paciente getPacienteById(int pacienteId){
        return PacienteManager.getInstance().getPacienteById(pacienteId);
    }
}
