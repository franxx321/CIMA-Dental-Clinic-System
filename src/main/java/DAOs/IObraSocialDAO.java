package DAOs;

import Objetos.ObraSocial;

import java.util.List;

public interface IObraSocialDAO {
    public boolean register(ObraSocial obraSocial);
    public List<ObraSocial> obtain(ObraSocial obraSocial);
    public boolean delete();
    public boolean modify();
}
