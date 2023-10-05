package Utils.TableGenerator;

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

public class EliminarTurnoTableGenerator {

    public JTable generateTable(List<Turno> turnos, long dni) {
        JTable table = new JTable();
        Vector<Vector<String>> data = new Vector<>();
        Vector<String> header = new Vector<>();
        Paciente paciente = PacienteManager.getInstance().getPatientByDni(dni);
        List<Profesional> profesionales = ProfesionalManager.getInstance().getAll();
        header.add(0,"Paciente");
        header.add(1,"DNI");
        header.add(2,"Profesional");
        header.add(3, "Hora de Inicio");
        header.add(4, "Hora de Fin");
        for (Turno turno:turnos) {
            Vector<String> vector = new Vector<>();
            vector.add(0,paciente.getNombre());
            vector.add(1,Long.toString(dni));
            for (Profesional profesional:profesionales) {
                if(profesional.getId()==turno.getIdProfesional()){
                    vector.add(2,profesional.getNombre());
                }
            }
            Date horaInicio = turno.getHoraInicio();
            Date horaFin = turno.getHoraFin();
            vector.add(Integer.toString(horaInicio.getDate())+"/"+Integer.toString(horaInicio.getMonth()+1)+"/"+Integer.toString(horaInicio.getHours())+":"+Integer.toString(horaInicio.getMinutes()));
            vector.add(Integer.toString(horaFin.getDate())+"/"+Integer.toString(horaFin.getMonth()+1)+"/"+Integer.toString(horaFin.getHours())+":"+Integer.toString(horaFin.getMinutes()));
            data.add(vector);
        }
        DefaultTableModel tm = new DefaultTableModel(data,header);
        table.setModel(tm);
        return table;
    }

    /*public DefaultTableModel resultToTable(){

        return null;
    }*/

}
