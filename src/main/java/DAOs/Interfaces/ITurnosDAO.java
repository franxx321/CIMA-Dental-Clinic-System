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

    public List <Turno> getByIdProfesional(int idProfesional);

    public List <Turno> getOverlappingturnos(int idProfesional , Date horaInicio, Date horaFin);

    public Turno getByDateProfesional(Date horaInicio, Date HoraFin, int idProfesional);

    public List<Turno> getPatientFutureApointments(int idPaciente);


}
