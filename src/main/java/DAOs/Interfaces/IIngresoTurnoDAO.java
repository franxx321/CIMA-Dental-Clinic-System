package DAOs.Interfaces;

import Objetos.IngresoTurno;

import java.util.List;

public interface IIngresoTurnoDAO {

    public boolean register(IngresoTurno ingresoTurno);

    public List<IngresoTurno> obtain ();

    public boolean delete (IngresoTurno ingresoTurno);

    public boolean modify ( IngresoTurno ingresoTurno, IngresoTurno aux);
}
