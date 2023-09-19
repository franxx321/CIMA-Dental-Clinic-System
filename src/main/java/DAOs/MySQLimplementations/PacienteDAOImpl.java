package DAOs.MySQLimplementations;

import DAOs.Interfaces.IPacienteDAO;
import Objetos.Paciente;
import Utils.DBUtils.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOImpl implements IPacienteDAO {
    DBConnector DBConnection = new DBConnector();

    // Look for the date and make the connection with the DB
    @Override
    public boolean register(Paciente paciente) {
        boolean register = false;
        Statement stm = null;
        Connection con = null;
        String sql = "INSERT INTO Paciente values (NULL, "+paciente.getNombre()+", "+paciente.getApellido()+", "+", "+paciente.getDni()+")";
        try{
            con =  DBConnection.conexion;
            stm = con.createStatement();
            stm.execute(sql);
            register = true;
            stm.close();
            con.close();

        } catch (SQLException e){
            System.out.println("Error: Clase PacienteDAOImpl, metodo register");
        }
        return register;
    }

    @Override
    public boolean delete(Paciente paciente) {
        boolean delete = false;
        Statement stm = null;
        Connection con = null;
        String sql = "DELETE FROM Paciente WHERE id = ";


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
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM paciente ORDER BY id";
        List<Paciente> pacienteList = new ArrayList<Paciente>();
        try{
            con = DBConnection.conexion;
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                Paciente p = new Paciente();
                p.setDni(rs.getInt(2));
                p.setNombre(rs.getString(3));
                //p.getFechaNacimiento(rs.getDate(4, Date.));
                p.setDireccion(rs.getString(5));
                //p.setSexo(rs.get(6));
                pacienteList.add(p);
                stm.close();
                rs.close();
                con.close();
            }
        }catch (SQLException e){
            System.out.println("Error: Clase PacienteDAOImpl, metodo obtain");
            e.printStackTrace();
        }
        return pacienteList;
    }
}
