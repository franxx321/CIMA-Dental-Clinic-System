package DAOs;

import Objetos.Profesional;

import java.util.List;

public interface IProfesionalDAO {
    public boolean register();
    public List<Profesional> obtain();
    public boolean delete();
    public boolean modify();
}
