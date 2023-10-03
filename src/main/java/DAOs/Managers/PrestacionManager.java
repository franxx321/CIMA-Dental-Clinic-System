package DAOs.Managers;

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

   // public int idByName

}
