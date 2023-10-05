package DAOs.Interfaces;

import Objetos.ObraSocial;

import java.util.List;

public interface IObraSocialDAO {
    public boolean register(ObraSocial obraSocial);
    public List<ObraSocial> obtain();
    public boolean delete(ObraSocial obraSocial);
    public boolean modify(ObraSocial obraSocial, ObraSocial aux);
}
