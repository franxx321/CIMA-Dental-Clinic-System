package DAOs.Interfaces;

import Objetos.Turno;
import Objetos.TurnoPrestacion;

import java.util.List;

public interface ITurnoPrestacionDAO {

    public boolean register(TurnoPrestacion turnoPrestacion);

    public List<TurnoPrestacion> obtain();

    public boolean delete(TurnoPrestacion turnoPrestacion);

    public boolean modify (TurnoPrestacion turnoPrestacion, TurnoPrestacion aux);


    public List<TurnoPrestacion> getByTurnoId(int turnoId);


}
