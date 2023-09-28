package DAOs.Interfaces;

import Objetos.PacienteObraSocial;

import java.util.List;

public interface IPacienteObraSocialDAO {
    public boolean register(PacienteObraSocial pacienteObraSocial);
    public List<PacienteObraSocial> obtain(PacienteObraSocial pacienteObraSocial);
    public boolean delete(PacienteObraSocial pacienteObraSocial);
    public boolean modify(PacienteObraSocial pacienteObraSocial, PacienteObraSocial aux);

}
