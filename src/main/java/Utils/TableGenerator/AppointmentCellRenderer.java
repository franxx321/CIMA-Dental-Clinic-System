package Utils.TableGenerator;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.HashMap;

public class AppointmentCellRenderer extends DefaultTableCellRenderer {

     private HashMap <Integer,HashMap<Integer,Boolean>> paintedcell;

    public AppointmentCellRenderer (HashMap <Integer,HashMap<Integer,Boolean>> paintedcell ){
        this.paintedcell=paintedcell;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(column>1){
            if(paintedcell.get(row).get(column)) {
                c.setBackground(Color.RED);
            }
        }
        return c;
    }
}
