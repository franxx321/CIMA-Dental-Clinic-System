package DAOs.Managers;
import DAOs.MySQLImplementations.ProfesionalDAOImpl;

public class ProfesionalManager {
    public static ProfesionalManager profesionalManager;
    public static ProfesionalManager getInstance(){
        if(profesionalManager == null){
            profesionalManager = new ProfesionalManager();
        }
        return profesionalManager;
    }



    private ProfesionalManager(){
    }
    public int getIdProfesionalManager(String profesional){
        return ProfesionalDAOImpl.getInstance().getIdProfesional(profesional);
    }

}
