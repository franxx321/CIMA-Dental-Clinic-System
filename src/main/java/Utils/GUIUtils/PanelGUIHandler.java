package Utils.GUIUtils;

import GUIComponents.*;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;

public class PanelGUIHandler  implements  GUIHandler{

    private static PanelGUIHandler panelGUIHandler;

    public static String panelInicio ="Panel Inicio",panelTurnos="Panel Turnos",agregarTurno= "Agregar Turno",modificarTurno="Modificar Turno",modificarPrestacion = "ModificarPrestaciones",eliminarTurno= "eliminarTurno";

    private HashMap<String, Panel> panels;

    private  Panel currentPanel;

    public static PanelGUIHandler getinstance(){
        if (panelGUIHandler==null){
            panelGUIHandler = new PanelGUIHandler();
        }
        return panelGUIHandler;
    }

    private PanelGUIHandler(){
        panels= new HashMap<>();
        panels.put(panelInicio, PanelInicio.getInstance());
        panels.put(panelTurnos, PanelTurnos.getInstance());
        panels.put(agregarTurno, PanelAgregarTurno.getInstance());
        panels.put(modificarTurno, MenuModificarTurno.getInstance());
        panels.put(eliminarTurno,EliminarTurno.getInstance());
    }

    @Override
    public void changePanel(String panelKey, List <Object> objects) {
        Panel nextPanel= panels.get(panelKey);
        JPanel panel=Frame.getInstance().getPanelPrincipal();
        panel.removeAll();
        panel.add(nextPanel);
        nextPanel.setup(objects);
        currentPanel=nextPanel;
        Frame.getInstance().repaint();
        Frame.getInstance().revalidate();
    }
}
