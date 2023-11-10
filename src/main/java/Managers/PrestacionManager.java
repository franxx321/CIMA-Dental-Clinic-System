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
       return PrestacionDAOImpl.getInstance().nameById(id);
   }

}
