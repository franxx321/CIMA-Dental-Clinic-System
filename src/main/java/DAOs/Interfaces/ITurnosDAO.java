package DAOs.Interfaces;

import Objetos.Turno;

import java.util.List;

public interface ITurnosDAO {
    public boolean register();
    public List<Turno> obtain();
    public boolean delete();
    public boolean modify();

}
