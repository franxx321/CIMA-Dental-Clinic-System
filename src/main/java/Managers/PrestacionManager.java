package Managers;

import DAOs.MySQLImplementations.PrestacionDAOImpl;
import Objetos.Prestacion;


import java.util.List;

public class PrestacionManager {

    private static PrestacionManager prestacionManager;

    public static PrestacionManager getInstance(){
        if(prestacionManager==null){
            prestacionManager= new PrestacionManager();
        }
        return prestacionManager;
    }

    private PrestacionManager() {
    }

    public List<Prestacion> getAllPrestacion(){
        return PrestacionDAOImpl.getInstance().obtain();
    }

   public Prestacion idByName(String prestacion){
        return PrestacionDAOImpl.getInstance().idByName(prestacion);
   }
   
   public Prestacion nameById(int id){
       Prestacion pr = new Prestacion();
       pr.setId(id);
       return PrestacionDAOImpl.getInstance().nameById(pr);
   }

   public Prestacion getById(int id){
        Prestacion prestacion = new Prestacion();
        prestacion.setId(id);
        return PrestacionDAOImpl.getInstance().getById(prestacion);
    }

}
