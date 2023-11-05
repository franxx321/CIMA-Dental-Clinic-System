package Managers;

import DAOs.MySQLImplementations.ObraSocialDAOImpl;
import DAOs.MySQLImplementations.ProfesionalDAOImpl;
import Objetos.ObraSocial;
import Objetos.Profesional;

import java.util.List;

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

    public List<ObraSocial> getAll(){
        return ObraSocialDAOImpl.getInstance().obtain();
    }
    
    
   public ObraSocial getByName(ObraSocial obrasocial){
       return ObraSocialDAOImpl.getInstance().getByName(obrasocial);
   }
}
