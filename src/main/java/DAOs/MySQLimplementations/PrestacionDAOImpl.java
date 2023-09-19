package DAOs.MySQLimplementations;

import DAOs.Interfaces.IPrestacionDAO;
import Objetos.Prestacion;

import java.util.List;

public class PrestacionDAOImpl implements IPrestacionDAO {
    @Override
    public boolean register() {
        return false;
    }

    @Override
    public List<Prestacion> obtain() {
        return null;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean modify() {
        return false;
    }
}
