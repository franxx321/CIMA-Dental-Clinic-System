package DAOs;

import Objetos.Gasto;

import java.util.List;

public interface IGastoDAO {
    public boolean register();
    public List<Gasto> obtain();
    public boolean delete();
    public boolean modify();
}
