package Managers;

import DAOs.MySQLImplementations.GastoDAOImpl;
import DAOs.MySQLImplementations.TurnoDAOImpl;
import Objetos.Gasto;
import Objetos.Profesional;

import java.util.Date;

public class GastoManager {
    private static GastoManager gastoManager;
    public static GastoManager getInstance(){
        if(gastoManager == null){
            gastoManager = new GastoManager();
        }
        return gastoManager;
    }
    private GastoManager(){
    }

    public void addGasto(float monto, String descripcion, java.sql.Date fecha, String profesionalCB){
        Gasto gasto = new Gasto();
        boolean error = false;
        String errorString = "";
        int idProfesional = 0;
        if(monto < 0){
            error = true;
            errorString = "El monto no puede ser negativo.";
        }else{
            gasto.setMonto(monto);
        }

        gasto.setDescripcion(descripcion);
        gasto.setFecha(fecha);

        Profesional profesional = ProfesionalManager.getInstance().getProfesionalByName(profesionalCB);
        idProfesional = profesional.getId();
        if (idProfesional == -1) {
            error = true;
            errorString = errorString + "\nEl profesional no esta cargado";
        }else {
            gasto.setIdProfesional(idProfesional);
        }

        GastoDAOImpl.getInstance().register(gasto);
    }


}
