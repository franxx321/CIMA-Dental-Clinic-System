package Objetos;

import java.sql.Date;
import java.util.List;

public class Ingreso {

    private float monto;

    private String descripcion;

    private Date fecha;

    private Profesional profesional;

    private List< Turno> turnos;

    private Paciente paciente;

    private ObraSocial obraSocial;
}
