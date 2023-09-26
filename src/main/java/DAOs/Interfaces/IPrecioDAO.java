package DAOs.Interfaces;

import Objetos.Precio;

import java.util.List;

public interface IPrecioDAO {

    public boolean register (Precio precio);

    public List<Precio> obtain();

    public boolean delete(Precio precio);

    public boolean modify (Precio precio, Precio aux);

}
