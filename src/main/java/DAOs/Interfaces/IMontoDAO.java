package DAOs.Interfaces;

import Objetos.Monto;

import java.util.List;

public interface IMontoDAO {

    public boolean register (Monto monto);

    public List<Monto> obtain(Monto monto);

    public boolean delete(Monto monto);

    public boolean modify (Monto monto, Monto aux);

}
