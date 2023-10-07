package Utils.TableGenerator;

import Managers.PrestacionManager;
import Managers.TurnoPrestacionManager;
import Objetos.Prestacion;
import Objetos.Turno;
import Objetos.TurnoPrestacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.List;
import java.util.Vector;

public class PrestacionesByTurnoTableGenerator {

    private static PrestacionesByTurnoTableGenerator prestacionesByTurnoTableGenerator;

    public static PrestacionesByTurnoTableGenerator getInstance(){
        if(prestacionesByTurnoTableGenerator == null){
            prestacionesByTurnoTableGenerator= new PrestacionesByTurnoTableGenerator();
        }
        return prestacionesByTurnoTableGenerator;
    }

    private PrestacionesByTurnoTableGenerator() {

    }
    public JTable generateTable(Turno turno){
        List<Prestacion> prestaciones= PrestacionManager.getInstance().getAllPrestacion();
        List<TurnoPrestacion> turnoPrestacionList= TurnoPrestacionManager.getInstance().getByTurnoId(turno.getId());
        JTable table = new JTable();
        TableModel tm;
        Vector<Vector<String>> data = new Vector<>();
        Vector<String> header = new Vector<>();
        header.add("Nombre");
        header.add("Descripcion");
        if(turnoPrestacionList!=null){
            for (TurnoPrestacion turnoPrestacion: turnoPrestacionList){
                for (Prestacion prestacion: prestaciones){
                    if (turnoPrestacion.getIdPrestacion()==prestacion.getId()){
                        Vector<String> vector = new Vector<>();
                        vector.add(prestacion.getNombre());
                        vector.add(prestacion.getDescripcion());
                        data.add(vector);
                    }
                }
            }
        }
        tm= new DefaultTableModel(data,header);
        table.setModel(tm);
        return table;
    }

}
