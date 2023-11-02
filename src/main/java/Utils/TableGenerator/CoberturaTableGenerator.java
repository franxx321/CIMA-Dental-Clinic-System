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

    public JTable generateTable(Profesional profesional) {
        JTable table = new JTable();
        Vector<Vector<String>> data = new Vector<>();
        Vector<String> header = new Vector<>();
        List<Cobertura> coberturaList = CoberturaManager.getInstance().getCoberturaByIdProfesional(profesional.getId());
        List<ObraSocial> obraSocialList = ObraSocialManager.getInstance().getAll();

        header.add(0, "Obra Social");
        header.add(1, "Porcentaje");
        header.add(2,"Tope");
        header.add(3,"Codigo");

        if (coberturaList != null) {

            for (Cobertura cobertura : coberturaList) {
                Vector<String> vector = new Vector<>();
                for (ObraSocial obraSocial : obraSocialList) {
                    if (cobertura.getIdObraSocial() == obraSocial.getId()) {
                        vector.add(0, obraSocial.getNombre());
                        vector.add(1,"" + cobertura.getPorcentaje());
                        vector.add(1,"" + cobertura.getTope());
                        vector.add(1,cobertura.getCodigo());
                        break;
                    }
                }
                data.add(vector);
            }
        }
        DefaultTableModel tableCobertura = new DefaultTableModel(data,header);
        table.setModel(tableCobertura);
        return table;
    }
}

