package Utils.GUIUtils;

import GUIComponents.Frame;
import GUIComponents.MenuSecundarioTurnos;
import GUIComponents.MenuSecundarioVacio;
import GUIComponents.Panel;

import javax.swing.*;
import java.util.HashMap;

public class SMenuGUIHandler implements GUIHandler{

    private static SMenuGUIHandler sMenuGUIHandler;

    public static String menuSecundarioVacio ="MSV", menuSecundarioTurnos ="MST";

    public HashMap<String, Panel> panels;

    public static SMenuGUIHandler getInstance(){
        if(sMenuGUIHandler ==null){
            sMenuGUIHandler = new SMenuGUIHandler();
        }
        return sMenuGUIHandler;
    }

    private  SMenuGUIHandler(){
        panels= new HashMap<>();
        panels.put(menuSecundarioTurnos, MenuSecundarioTurnos.getInstance());
        panels.put(menuSecundarioVacio, MenuSecundarioVacio.getInstance());

    }
    @Override
    public void changePanel(String panelKey) {
        Panel nextPanel = panels.get(panelKey);
        JPanel sMenu= Frame.getInstance().getMenuSecundario();
        sMenu.removeAll();
        sMenu.add(nextPanel);
        Frame.getInstance().repaint();
        Frame.getInstance().revalidate();

    }
}
