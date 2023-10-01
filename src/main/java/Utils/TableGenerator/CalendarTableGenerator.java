package Utils.TableGenerator;

import DAOs.MySQLImplementations.TurnosDAOImpl;
import Objetos.Turno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultTextUI;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

public class CalendarTableGenerator implements TableGenerator{

    @Override
    public JTable generateTable(List<Object> arguments) {
        List <Turno> turnos =TurnosDAOImpl.getInstance().profesionalFutureApointments((String)arguments.get(1),(Integer)arguments.get(2));
        JTable table = new JTable();
        return null;
    }

    public DefaultTableModel resultToTable(ResultSet resultSet){
        Date todayDate = new Date();
        todayDate.setHours(0);
        todayDate.setMinutes(0);
        todayDate.setSeconds(0);
        todayDate.setDate(todayDate.getDate()+1);
        todayDate.getMonth();
        return null;
    }
}
