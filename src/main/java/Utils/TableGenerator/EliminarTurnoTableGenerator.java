package Utils.TableGenerator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class EliminarTurnoTableGenerator implements TableGenerator{
    @Override
    public JTable generateTable(List<Object> arguments) {
        JTable table = new JTable();
        DefaultTableModel tm = resultToTable();
        long dni = (long)arguments.get(0);

        return null;
    }

    public DefaultTableModel resultToTable(){
        return null;

    }

}
