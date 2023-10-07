package Managers;

import DAOs.MySQLImplementations.TurnoPrestacionDAOImpl;
import Objetos.Turno;
import Objetos.TurnoPrestacion;

import java.util.List;

public class TurnoPrestacionManager {
    private static TurnoPrestacionManager turnoPrestacionManager;
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

    public List<TurnoPrestacion> getByTurnoId(int turnoId){
        return TurnoPrestacionManager.turnoPrestacionManager.getByTurnoId(turnoId);
    }


}
