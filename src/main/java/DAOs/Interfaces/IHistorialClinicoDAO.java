package DAOs.Interfaces;

import Objetos.HistorialClinico;

import java.util.List;

public interface IHistorialClinicoDAO {
    public boolean register(HistorialClinico historialClinico);
    public List<HistorialClinico> obtain(HistorialClinico historialClinico);
    public boolean delete(HistorialClinico historialClinico);
    public boolean modify(HistorialClinico historialClinico, HistorialClinico aux);
}
