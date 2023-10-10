package Managers;

import DAOs.MySQLImplementations.GastoDAOImpl;
import DAOs.MySQLImplementations.TurnoDAOImpl;
import Objetos.Gasto;
import Objetos.Profesional;
import Utils.Exceptions.CantAddGasto;
import Utils.Exceptions.CantAddTurno;

import java.util.Date;
import java.util.List;

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
            errorString = "El monto es invalido.";
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

        if(error){
            throw new CantAddGasto(errorString);
        }else{
            GastoDAOImpl.getInstance().register(gasto);
        }

    }

    public List<Profesional> getAllProfesional(){
        return ProfesionalManager.getInstance().getAll();
    }
}
