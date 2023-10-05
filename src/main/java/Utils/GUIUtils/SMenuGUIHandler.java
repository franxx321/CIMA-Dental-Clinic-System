package Utils.GUIUtils;

import GUIComponents.Frame;
import GUIComponents.MenuSecundarioTurnos;
import GUIComponents.MenuSecundarioVacio;
import GUIComponents.Panel;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;

public class SMenuGUIHandler implements GUIHandler{

    private static SMenuGUIHandler sMenuGUIHandler;

    public static String menuSecundarioVacio ="MSV", menuSecundarioTurnos ="MST";

    private HashMap<String, Panel> panels;

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
    public void changePanel(String panelKey, List <Object> objects) {
        Panel nextPanel = panels.get(panelKey);
        JPanel sMenu= Frame.getInstance().getMenuSecundario();
        sMenu.removeAll();
        sMenu.add(nextPanel);
        nextPanel.setup(objects);
        Frame.getInstance().repaint();
        Frame.getInstance().revalidate();

    }
}
