package DAOs.Interfaces;

import Objetos.Paciente;

import java.util.List;

public interface IPacienteDAO {
    public List<Paciente> obtain(Paciente paciente);

    // Ver fecha
    boolean register(Paciente paciente);

    public boolean delete(Paciente paciente);
    public boolean modify(Paciente paciente, Paciente aux);
}
