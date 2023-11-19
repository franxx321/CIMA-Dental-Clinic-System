package DAOs.Interfaces;

import Objetos.Ingreso;

import java.util.List;

public interface IIngresoDAO {
    public boolean register(Ingreso ingreso);
    public List<Ingreso> obtain();
    public boolean delete(Ingreso ingreso);
    public boolean modify(Ingreso ingreso, Ingreso aux);
    public List<Float> getIngresoByProfesional(Ingreso ingreso);

}
