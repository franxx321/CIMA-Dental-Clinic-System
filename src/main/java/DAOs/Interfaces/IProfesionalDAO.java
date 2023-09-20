package DAOs.Interfaces;

import Objetos.Profesional;

import java.util.List;

public interface IProfesionalDAO {
    public boolean register(Profesional profesional);
    public List<Profesional> obtain(Profesional profesional);
    public boolean delete(Profesional profesional);
    public boolean modify(Profesional profesional, Profesional aux);
}
