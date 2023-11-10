package Managers;
import DAOs.MySQLImplementations.ProfesionalDAOImpl;
import Objetos.Profesional;

import java.util.List;

public class ProfesionalManager {
    private static ProfesionalManager profesionalManager;
    public static ProfesionalManager getInstance(){
        if(profesionalManager == null){
            profesionalManager = new ProfesionalManager();
        }
        return profesionalManager;
    }

    private ProfesionalManager(){
    }

    public Profesional getProfesionalByName(String profesional){
        Profesional profesional1 = new Profesional();
        profesional1.setNombre(profesional);
        return ProfesionalDAOImpl.getInstance().getProfesionalByName(profesional1);
    }

    public Profesional getProfesionalById(int profesionalId){
        Profesional pr = new Profesional();
        pr.setId(profesionalId);
        return ProfesionalDAOImpl.getInstance().getProfesionalById(pr);
    }

    public List<Profesional> getAll(){
        return ProfesionalDAOImpl.getInstance().obtain();
    }

}
