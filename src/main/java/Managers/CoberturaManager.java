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


    
    public List<Cobertura> getCoberturaByIdObraSocial(int obrasocialId){
        Cobertura c = new Cobertura();
        c.setIdObraSocial(obrasocialId);

        return CoberturaDAOImpl.getInstance().getCoberturaByIdObraSocial(c);

    }
    
    public boolean modify(Cobertura cobertura, Cobertura aux){
        return CoberturaDAOImpl.getInstance().modify(cobertura, aux);
    }

}
