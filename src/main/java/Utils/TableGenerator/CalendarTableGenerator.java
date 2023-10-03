package Utils.TableGenerator;

import DAOs.MySQLImplementations.TurnosDAOImpl;
import Objetos.Turno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultTextUI;
import java.awt.*;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class CalendarTableGenerator implements TableGenerator{

    private static CalendarTableGenerator calendarTableGenerator;

    private CalendarTableGenerator() {
    }

    public static CalendarTableGenerator getInstance(){
        if(calendarTableGenerator==null){
            calendarTableGenerator=new CalendarTableGenerator();
        }
        return calendarTableGenerator;
    }

    @Override
    public JTable generateTable(List<Object> arguments) {
        //List <Turno> turnos =TurnosDAOImpl.getInstance().profesionalFutureApointments((String)arguments.get(0),(Integer)arguments.get(1));
        JTable table = new JTable();
        DefaultTableModel tm = resultToTable();
        table.setModel(tm);
        // c = table.getCellRenderer(1,1).getTableCellRendererComponent(table)
        return table;
    }

    public DefaultTableModel resultToTable(){
        Date auxDate = new Date();
        auxDate.setHours(0);
        auxDate.setMinutes(0);
        auxDate.setSeconds(0);
        //todayDate.setDate();
        Vector<Vector<String>> data = new Vector<>();
        Vector<String> header = new Vector<>();
        header.add(0,"");
        header.add(1, Integer.toString(auxDate.getDate())+"/"+Integer.toString(auxDate.getMonth()));
        for (int i=2; i<=7; i++){
            auxDate.setDate(auxDate.getDate()+1);
            if(auxDate.getDate()==1){
                auxDate.setMonth(auxDate.getMonth()+1);
            }
            header.add(i,Integer.toString(auxDate.getDate())+"/"+Integer.toString(auxDate.getMonth()));
        }
        //data.add(header);
        int hora=8;
        int minutos=0;
        int hora2=8;
        int minutos2=30;
        for(int i = 1; i <= 22; i++) {
            Vector<String> vector = new Vector<>();
            vector.add(Integer.toString(hora) + ":" + Integer.toString(minutos) + "-" + Integer.toString(hora2) + ":" + Integer.toString(minutos2));
            for (int j = 1; i <= 7; i++) {
                vector.add("");
            }
            if (minutos == 0) {
                minutos = 30;
            } else {
                minutos = 0;
                hora++;
            }
            if (minutos2 == 0) {
                minutos2 = 30;
            } else {
                minutos2 = 0;
                hora2++;
            }
            data.add(vector);
        }
        return new DefaultTableModel(data,header) ;
    }
}
