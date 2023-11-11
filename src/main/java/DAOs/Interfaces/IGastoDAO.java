package DAOs.Interfaces;

import Objetos.Gasto;

import java.util.List;

public interface IGastoDAO {
    public boolean register(Gasto gasto);
    public List<Gasto> obtain();
    public boolean delete(Gasto gasto);
    public boolean modify(Gasto gasto, Gasto aux);
    public List<Gasto> getGastoByProfesional(Gasto gasto);
}
