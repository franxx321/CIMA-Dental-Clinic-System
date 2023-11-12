package Utils.TableGenerator;

import Managers.MontoManager;
import Managers.PrestacionManager;
import Objetos.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Objetos.Monto;
import java.util.List;
import java.util.Vector;

public class ProfesionalTableGenerator {
    private static ProfesionalTableGenerator profesionalTableGenerator;

    public static ProfesionalTableGenerator getInstance(){
        if(profesionalTableGenerator ==null){
            profesionalTableGenerator= new ProfesionalTableGenerator();
        }
        return profesionalTableGenerator;
    }

    private  ProfesionalTableGenerator(){

    }

    public JTable generateTable(Profesional profesional) {
        JTable table = new JTable();
        Vector<Vector<String>> data = new Vector<>();
        Vector<String> header = new Vector<>();
        List<Monto> montoList =null;
        List<Prestacion> prestacionList = null;
        if(profesional !=null) {
            montoList = MontoManager.getInstance().getMontoByIdProfesional(profesional.getId());
            prestacionList = PrestacionManager.getInstance().getAllPrestacion();
        }

        header.add(0, "Prestacion");
        header.add(1, "Monto");
        header.add(2, "idPrestacion");

        if (montoList != null) {

            for (Monto monto : montoList) {
                Vector<String> vector = new Vector<>();
                for (Prestacion prestacion : prestacionList) {
                    if (monto.getIdPrestacion() == prestacion.getId()) {
                        vector.add(0, prestacion.getNombre());
                        vector.add(1,"" + monto.getPrecio());
                        vector.add(2,"" + monto.getIdPrestacion());
                        break;
                    }
                }
                data.add(vector);
            }
        }
        
        DefaultTableModel tableModel = new DefaultTableModel(data, header) {
        @Override
        public boolean isCellEditable(int row, int column) {
            // Hace que la columna "Prestacion" no sea editable
            return column != 0;
            }
        };

        table.setModel(tableModel);
        return table;
    }
}
