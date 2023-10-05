package Utils.TableGenerator;

import Objetos.Turno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class CalendarTableGenerator {

    private static CalendarTableGenerator calendarTableGenerator;

    private CalendarTableGenerator() {
    }

    public static CalendarTableGenerator getInstance(){
        if(calendarTableGenerator==null){
            calendarTableGenerator=new CalendarTableGenerator();
        }
        return calendarTableGenerator;
    }


    public JTable generateTable(List<Turno> turnosList, int week) {
        JTable table = new JTable();
        DefaultTableModel tm;


        Date auxDate = new Date();

        auxDate.setHours(0);
        auxDate.setMinutes(0);
        auxDate.setSeconds(0);
        //todayDate.setDate();
        Vector<Vector<String>> data = new Vector<>();
        Vector<String> header = new Vector<>();
        header.add(0,"");
        header.add(1, Integer.toString(auxDate.getDate())+"/"+Integer.toString(auxDate.getMonth()+1));
        for (int i=2; i<=7; i++){
            auxDate.setDate(auxDate.getDate()+1);
            if(auxDate.getDate()==1){
                auxDate.setMonth(auxDate.getMonth()+1);
            }
            header.add(i,Integer.toString(auxDate.getDate())+"/"+Integer.toString(auxDate.getMonth()+1));
        }
        int hora=8;
        int minutos=0;
        int hora2=8;
        int minutos2=30;
        for(int i = 0; i <= 21; i++) {
            Vector<String> vector = new Vector<>();
            vector.add(Integer.toString(hora) + ":" + Integer.toString(minutos) + "-" + Integer.toString(hora2) + ":" + Integer.toString(minutos2));
            for (int j = 1; i <= 7; i++) {
                vector.add("");
                int dia = Integer.parseInt(header.get(i).substring(0,header.get(1).indexOf("/")));
                int mes = Integer.parseInt(header.get(i).substring(header.get(1).indexOf("/")+1));
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
        return table;
    }

}
