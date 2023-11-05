package DAOs.Interfaces;

import Objetos.Cobertura;
import Objetos.Monto;

import java.util.List;

public interface ICoberturaDAO {
    public boolean register(Cobertura cobertura);
    public List<Cobertura> obtain();
    public boolean delete(Cobertura cobertura);
    public boolean modify(Cobertura cobertura, Cobertura aux);

    public List <Cobertura> getCoberturaByIdProfesional(int profesionalId);
    
    public List <Cobertura> getCoberturaByIdObraSocial(int obrasocialId);
}
