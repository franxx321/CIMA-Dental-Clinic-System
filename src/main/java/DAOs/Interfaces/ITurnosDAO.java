package DAOs.Interfaces;

import Objetos.Turno;

import java.util.Date;
import java.util.List;

public interface ITurnosDAO {
    public boolean register(Turno turno);
    public List<Turno> obtain();
    public boolean delete(Turno turno);
    public boolean modify(Turno turno, Turno aux);

    public List<Turno> profesionalFutureApointments(int idprofesional, int week);

    public List <Turno> getByIdProfesional(Turno turno);

    public List <Turno> getOverlappingturnos(Turno turno);

    public Turno getByDateProfesional(Turno turno);

    public List<Turno> getPatientFutureApointments(Turno t);

    public Turno getById(Turno t);




}
