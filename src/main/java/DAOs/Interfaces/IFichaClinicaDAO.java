package DAOs.Interfaces;

import Objetos.FichaClinica;
import java.util.List;

public interface IFichaClinicaDAO {
    public boolean register(FichaClinica fichaClinica);
    public List<FichaClinica> obtain(FichaClinica fichaClinica);
    public boolean delete(FichaClinica fichaClinica);
    public boolean modify(FichaClinica fichaClinica, FichaClinica aux);

}
