package Managers;

import DAOs.MySQLImplementations.CoberturaDAOImpl;
import DAOs.MySQLImplementations.MontoDAOImpl;
import Objetos.Cobertura;
import Objetos.Monto;

import java.util.List;

public class CoberturaManager {

    private static CoberturaManager coberturaManager;
    public static CoberturaManager getInstance(){
        if(coberturaManager == null){
            coberturaManager = new CoberturaManager();
        }
        return coberturaManager;
    }
    private CoberturaManager(){

    }

    public List<Cobertura> getCoberturaByIdProfesional(int profesionalId){

        return CoberturaDAOImpl.getInstance().getCoberturaByIdProfesional(profesionalId);

    }
    
    public List<Cobertura> getCoberturaByIdObraSocial(int obrasocialId){

        return CoberturaDAOImpl.getInstance().getCoberturaByIdObraSocial(obrasocialId);

    }

}
