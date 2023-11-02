package Managers;

import DAOs.MySQLImplementations.ObraSocialDAOImpl;
import Objetos.ObraSocial;

public class ObraSocialManager {

    private static ObraSocialManager obraSocialManager;

    public static ObraSocialManager getInstance(){
        if(obraSocialManager==null){
            obraSocialManager= new ObraSocialManager();
        }
        return obraSocialManager;
    }

    private ObraSocialManager(){}

    public ObraSocial getById(int idOs){
        ObraSocial obraSocial= new ObraSocial();
        obraSocial.setId(idOs);
        return ObraSocialDAOImpl.getInstance().getById(obraSocial);
    }
}
