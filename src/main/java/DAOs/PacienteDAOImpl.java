package DAOs;

import Objetos.Paciente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOImpl implements IPacienteDAO {

    // Look for the date and make the connection with the DB
    @Override
    public boolean register(Paciente paciente) {
        boolean register = false;
        Statement stm = null;
        Connection con = null;
        String sql = "INSERT INTO Paciente values (NULL, "+paciente.getNombre()+", "+paciente.getApellido()+", "+", "+paciente.getDni()+")";
        return false;
    }

    @Override
    public boolean delete(Paciente paciente) {
        boolean delete = false;
        Statement stm = null;
        Connection con = null;
        String sql = "DELETE FROM Paciente";


        return false;
    }

    // Discuss the attributes and fix the UPDATE
    @Override
    public boolean modify(Paciente paciente, Paciente aux) {
        boolean modify = false;
        Statement stm = null;
        Connection con = null;
        String sql = "UPDATE Paciente SET ID = NULL, Nombre = "+aux.getNombre()+", Apellido = "+aux.getApellido()+", "+", Direccion = ";

        return false;
    }

    @Override
    public List<Paciente> obtain(Paciente paciente) {
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM paciente";
        List<Paciente> listPaciente = new ArrayList<Paciente>();

        /* try{

        }catch (SQLException e){
            System.out.println("Error: Clase PacienteDAOImpl, metodo obtain");
        } */

        return null;
    }
}
