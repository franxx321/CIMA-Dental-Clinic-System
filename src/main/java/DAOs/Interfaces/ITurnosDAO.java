package DAOs.Interfaces;

import Objetos.Turno;

import java.util.List;

public interface ITurnosDAO {
    public boolean register(Turno turno);
    public List<Turno> obtain(Turno turno);
    public boolean delete(Turno turno);
    public boolean modify(Turno turno, Turno aux);

    public List<Turno> profesionalFutureApointments(String idprofesional, int week);

}
