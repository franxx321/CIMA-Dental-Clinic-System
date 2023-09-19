package DAOs.Interfaces;

import Objetos.Prestacion;

import java.util.List;

public interface IPrestacionDAO {
    public boolean register();
    public List<Prestacion> obtain();
    public boolean delete();
    public boolean modify();
}
