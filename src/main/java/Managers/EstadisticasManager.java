package Managers;

import DAOs.MySQLImplementations.TurnoDAOImpl;
import Objetos.Gasto;
import Objetos.Ingreso;
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

    public List<Integer> getTurnosAsistidosyNo(Turno turno) {
        List<Turno> turnosAsistList = TurnoManager.getInstance().getTurnosByProfesional(turno);
        List<Integer> turnos = new ArrayList<>();
        int turnosAsistidos = 0, turnosNoAsistidos = 0, turnosAsistidos30 = 0, turnosNoAsistidos30 = 0;
        Date fechaInicio = restarDias(FormatedDate.formatedDate(new Date()), 30);
        for (Turno t : turnosAsistList) {
            if (t.isAsistio()){
                turnosAsistidos++;
            }else
                turnosNoAsistidos++;
            if(t.getHoraInicio().after(fechaInicio) && t.isAsistio()){
                turnosAsistidos30++;
            } else if(t.getHoraInicio().after(fechaInicio) && !t.isAsistio()) {
                turnosNoAsistidos30++;
            }
        }
        turnos.add(turnosAsistidos);
        turnos.add(turnosNoAsistidos);
        turnos.add(turnosAsistidos30);
        turnos.add(turnosNoAsistidos30);
        return turnos;
    }

    public List<Float> getIngresoGastoByProfesional(Ingreso ingreso, Gasto gasto){
        List<Ingreso> ingresoList = IngresoManager.getInstance().getIngresoByProfesional(ingreso);
        List<Gasto> gastoList = GastoManager.getInstance().getGastoByProfesional(gasto);
        List<Float> ingrGasto = new ArrayList<>();
        Date fechaInicio = restarDias(FormatedDate.formatedDate(new Date()), 30);
        float ingresoTotal = 0, gastoTotal = 0, ingresoTotal30 = 0, gastoTotal30 = 0;
        for (Ingreso i:ingresoList) {
            ingresoTotal += i.getMonto();
            if(i.getFecha().after(fechaInicio)){
                ingresoTotal30 += i.getMonto();
            }
        }
        for (Gasto g:gastoList) {
            gastoTotal += g.getMonto();
            if(g.getFecha().after(fechaInicio)){
                gastoTotal30 += g.getMonto();
            }
        }
        ingrGasto.add(ingresoTotal);
        ingrGasto.add(gastoTotal);
        ingrGasto.add(ingresoTotal30);
        ingrGasto.add(gastoTotal30);
        return ingrGasto;
    }

    private Date restarDias(Date fecha, int dias) {
        long tiempo = fecha.getTime();
        tiempo -= dias * 24 * 60 * 60 * 1000L;
        return new Date(tiempo);
    }
}
