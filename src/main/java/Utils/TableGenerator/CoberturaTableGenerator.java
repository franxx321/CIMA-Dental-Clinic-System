package Utils.TableGenerator;

import Managers.CoberturaManager;
import Managers.MontoManager;
import Managers.ObraSocialManager;
import Managers.PrestacionManager;
import Objetos.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

public class CoberturaTableGenerator {

    private static CoberturaTableGenerator coberturaTableGenerator;

    public static CoberturaTableGenerator getInstance(){
        if(coberturaTableGenerator ==null){
            coberturaTableGenerator= new CoberturaTableGenerator();
        }
        return coberturaTableGenerator;
    }

    private  CoberturaTableGenerator(){

    }

    public JTable generateTable(ObraSocial obrasocial) {
        JTable table = new JTable();
        Vector<Vector<String>> data = new Vector<>();
        Vector<String> header = new Vector<>();
        List<Cobertura> coberturaList = CoberturaManager.getInstance().getCoberturaByIdObraSocial(obrasocial.getId());
        List<ObraSocial> obraSocialList = ObraSocialManager.getInstance().getAll();

        header.add(0, "Prestacion");
        header.add(1, "Porcentaje");
        header.add(2,"Tope");
        header.add(3,"Codigo");

        if (coberturaList != null) {

            for (Cobertura cobertura : coberturaList) {
                Vector<String> vector = new Vector<>();
                for (ObraSocial obraSocial : obraSocialList) {
                    if (cobertura.getIdObraSocial() == obraSocial.getId()) {
                        vector.add(0, PrestacionManager.getInstance().nameById(cobertura.getIdPrestacion()).getNombre());
                        vector.add(1,"" + cobertura.getPorcentaje());
                        vector.add(2,"" + cobertura.getTope());
                        vector.add(3,cobertura.getCodigo());
                        break;
                    }
                }
                data.add(vector);
            }
        }
        
        DefaultTableModel tableModel = new DefaultTableModel(data, header) {
        @Override
        public boolean isCellEditable(int row, int column) {
            // Hace que las columnas "Prestacion" (columna 0) y "Codigo" (columna 3) no sean editables
            return column != 0 && column != 3;
        }
        };

        table.setModel(tableModel);
        return table;
    }
}

