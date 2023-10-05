package DAOs.Interfaces;

import Objetos.Turno;

import java.util.Date;
import java.util.List;

public interface ITurnosDAO {
    public boolean register(Turno turno);
    public List<Turno> obtain();
    public boolean delete(Turno turno);
    public boolean modify(Turno turno, Turno aux);

    public List<Turno> profesionalFutureApointments(String idprofesional, int week);

    public List <Turno> getByIdProfesional(int idProfesional);

    public List <Turno> getOverlappingturnos(int idProfesional , Date horaInicio, Date horaFin);


}
