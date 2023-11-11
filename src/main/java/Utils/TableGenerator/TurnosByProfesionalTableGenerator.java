package Utils.TableGenerator;

import GUIComponents.TurnoIngresoPanel;
import Managers.PacienteManager;
import Managers.ProfesionalManager;
import Objetos.Paciente;
import Objetos.Profesional;
import Objetos.Turno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class TurnosByProfesionalTableGenerator {
    private static TurnosByProfesionalTableGenerator turnosByProfesionalTableGenerator;

    public static TurnosByProfesionalTableGenerator getInstance(){
        if (turnosByProfesionalTableGenerator==null){
            turnosByProfesionalTableGenerator = new TurnosByProfesionalTableGenerator();
        }
        return turnosByProfesionalTableGenerator;
    }

    private TurnosByProfesionalTableGenerator() {

    }
    public JTable generateTable(List<Turno> turnos, Profesional profesional){
        JTable table = new JTable();
        Vector<Vector<String>> data = new Vector<>();
        Vector<String> header = new Vector<>();
        List<Paciente> pacientes = PacienteManager.getInstance().getAll();
        header.add(0,"Paciente");
        header.add(1,"DNI");
        header.add(2,"Profesional");
        header.add(3, "Hora de Inicio");
        header.add(4, "Hora de Fin");
        header.add(5,"idsTurnos");
        if(turnos != null){
            for (Turno turno:turnos) {
                Vector<String> vector = new Vector<>();
                for (Paciente paciente:pacientes) {
                    if(paciente.getId()==turno.getIdPaciente()){
                        vector.add(paciente.getNombre());
                        vector.add(Long.toString(paciente.getDni()));
                    }
                }
                vector.add(profesional.getNombre());
                Date horaInicio = turno.getHoraInicio();
                Date horaFin = turno.getHoraFin();
                vector.add(Integer.toString(horaInicio.getDate())+"/"+Integer.toString(horaInicio.getMonth()+1)+"/"+Integer.toString(horaInicio.getYear()+1900)+" "+Integer.toString(horaInicio.getHours())+":"+Integer.toString(horaInicio.getMinutes()));
                vector.add(Integer.toString(horaFin.getDate())+"/"+Integer.toString(horaFin.getMonth()+1) +"/"+Integer.toString(horaFin.getYear()+1900)+" "+ Integer.toString(horaFin.getHours())+":"+Integer.toString(horaFin.getMinutes()));
                vector.add(Integer.toString(turno.getId()));
                data.add(vector);
            }
        }
        DefaultTableModel tm = new DefaultTableModel(data,header);
        table.setModel(tm);
        return table;

    }

}
