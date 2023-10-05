package Managers;

import DAOs.MySQLImplementations.TurnoPrestacionDAOImpl;
import Objetos.TurnoPrestacion;

public class TurnoPrestacionManager {
    public static TurnoPrestacionManager turnoPrestacionManager;
    public static TurnoPrestacionManager getInstance(){
        if(turnoPrestacionManager == null){
            turnoPrestacionManager = new TurnoPrestacionManager();
        }
        return turnoPrestacionManager;
    }
    private TurnoPrestacionManager(){
    }

    public void addTurnoPrestacion (TurnoPrestacion turnoPrestacion){
        TurnoPrestacionDAOImpl.getInstance().register(turnoPrestacion);
    }


}
