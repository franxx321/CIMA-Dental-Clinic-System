package DAOs;

import Objetos.Ingreso;

import java.util.List;

public interface IIngresoDAO {
    public boolean register();
    public List<Ingreso> obtain();
    public boolean delete();
    public boolean modify();

}
