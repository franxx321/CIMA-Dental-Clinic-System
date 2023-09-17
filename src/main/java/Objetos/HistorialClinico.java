package Objetos;

import java.util.List;

public class HistorialClinico {

    private Paciente paciente;

    private List<FichaClinica> fichasClinicas;


    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<FichaClinica> getFichasClinicas() {
        return fichasClinicas;
    }

    public void setFichasClinicas(List<FichaClinica> fichasClinicas) {
        this.fichasClinicas = fichasClinicas;
    }

}
