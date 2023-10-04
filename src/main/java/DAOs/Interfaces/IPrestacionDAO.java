package DAOs.Interfaces;

import Objetos.Prestacion;

import java.util.List;

public interface IPrestacionDAO {
    public boolean register(Prestacion prestacion);
    public List<Prestacion> obtain();
    public boolean delete(Prestacion prestacion);
    public boolean modify(Prestacion prestacion, Prestacion aux);
}
