package DAOs.Interfaces;

import Objetos.PacienteObraSocial;

import java.util.List;

public interface IPacienteObraSocialDAO {
    public boolean register(PacienteObraSocial pacienteObraSocial);
    public List<PacienteObraSocial> obtain();
    public boolean delete(PacienteObraSocial pacienteObraSocial);
    public boolean modify(PacienteObraSocial pacienteObraSocial, PacienteObraSocial aux);
    public List<PacienteObraSocial> getByPacienteId(PacienteObraSocial pacienteObraSocial);
}
