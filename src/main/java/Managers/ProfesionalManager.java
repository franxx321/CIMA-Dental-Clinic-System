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
        return ProfesionalDAOImpl.getInstance().getProfesionalByName(profesional);
    }

    public List<Profesional> getAll(){
        return ProfesionalDAOImpl.getInstance().obtain();
    }

}
