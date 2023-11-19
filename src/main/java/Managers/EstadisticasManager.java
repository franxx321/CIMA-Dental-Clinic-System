package Managers;

import DAOs.MySQLImplementations.TurnoDAOImpl;
import Objetos.Gasto;
import Objetos.Ingreso;
import Objetos.Profesional;
import Objetos.Turno;
import Utils.FormatedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EstadisticasManager {
    private static EstadisticasManager estadisticasManager;

    public static EstadisticasManager getInstance() {
        if (estadisticasManager == null) {
            estadisticasManager = new EstadisticasManager();
        }
        return estadisticasManager;
    }

    private EstadisticasManager() {

    }
    public List<Integer> getAssistedTurnos(Turno turno){
        List<Integer> turnos = new ArrayList<>();
        turnos = TurnoManager.getInstance().getAssistedTurnos(turno);
        return turnos;
    }

    public List<Float> getIngresoGastoByProfesional(Ingreso ingreso, Gasto gasto){
        List<Float> ingresos = new ArrayList<>();
        List<Float> gastos = new ArrayList<>();
        List<Float> ingrGastos = new ArrayList<>();
        ingresos = IngresoManager.getInstance().getIngresoByProfesional(ingreso);
        gastos = GastoManager.getInstance().getGastoByProfesional(gasto);
        ingrGastos.add(ingresos.get(0));
        ingrGastos.add(ingresos.get(1));
        ingrGastos.add(gastos.get(0));
        ingrGastos.add(gastos.get(1));
        return ingrGastos;
    }

    public List<Profesional> getAllProfesional(){
        return ProfesionalManager.getInstance().getAll();
    }

}
