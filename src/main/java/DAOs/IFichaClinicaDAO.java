package DAOs;

import Objetos.FichaClinica;
import java.util.List;

public interface IFichaClinicaDAO {
    public boolean register(FichaClinica fichaClinica);
    public List<FichaClinica> obtain(FichaClinica fichaClinica);
    public boolean delete();
    public boolean modify();

}
