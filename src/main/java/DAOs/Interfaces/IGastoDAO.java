package DAOs.Interfaces;

import Objetos.Gasto;

import java.util.List;

public interface IGastoDAO {
    public boolean register(Gasto gasto);
    public List<Gasto> obtain(Gasto gasto);
    public boolean delete(Gasto gasto);
    public boolean modify(Gasto gasto, Gasto aux);
}
