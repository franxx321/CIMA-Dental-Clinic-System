package DAOs.Interfaces;

import Objetos.Monto;

import java.util.List;

public interface IMontoDAO {

    public boolean register (Monto monto);

    public List<Monto> obtain();

    public boolean delete(Monto monto);

    public boolean modify (Monto monto, Monto aux);

    public Monto getByIds(int idProfesional, int idPrestacion);

    public List <Monto> getMontoByIdProfesional(int profesionalId);

}
