package DAOs;

import Objetos.HistorialClinico;

import java.util.List;

public interface IHistorialClinicoDAO {
    public boolean register();
    public List<HistorialClinico> obtain();
    public boolean delete();
    public boolean modify();
}
