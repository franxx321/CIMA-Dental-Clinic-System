package Managers;

import DAOs.MySQLImplementations.MontoDAOImpl;
import Objetos.Monto;

public class MontoManager {

    private static MontoManager montoManager;

    public static MontoManager getInstance(){
        if(montoManager==null){
            montoManager= new MontoManager();
        }
        return montoManager;
    }

    private MontoManager(){

    }

    public Monto getByIds(int prestacionId, int profesionalId){
        return MontoDAOImpl.getInstance().getByIds(profesionalId,prestacionId);
    }


}
