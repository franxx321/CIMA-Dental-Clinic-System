package DAOs.Interfaces;

import Objetos.Cobertura;

import java.util.List;

public interface ICoberturaDAO {
    public boolean register();
    public List<Cobertura> obtain();
    public boolean delete();
    public boolean modify();
}
