package DAOs.MySQLImplementations;

import DAOs.Interfaces.IPacienteDAO;
import Objetos.Paciente;
import Utils.DBUtils.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOImpl implements IPacienteDAO {
    DBConnector DBConnection ;
    Connection con = null;

    @Override
    public boolean register(Paciente paciente) {
        boolean register = false;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO Paciente(dni, fechanac, direccion, sexo) VALUES (?,?,?,?)";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setLong(1, paciente.getDni());
            pstm.setDate(2, paciente.getFechaNacimiento());
            pstm.setString(3, paciente.getDireccion());
            pstm.setString(4, String.valueOf(paciente.getSexo()));
            pstm.execute(sql);
            register = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase PacienteDAOImpl, metodo register, "+e.getMessage());
        }
        return register;
    }

    @Override
    public boolean delete(Paciente paciente) {
        boolean delete = false;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM Paciente WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, paciente.getId());
            pstm.execute(sql);
            delete = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase PacienteDAOImpl. metodo delete. " +e.getMessage());
        }
        return delete;
    }

    @Override
    public boolean modify(Paciente paciente, Paciente aux) {
        boolean modify = false;
        PreparedStatement pstm = null;
        String sql = "UPDATE Paciente SET id = ?, dni = ?, fechaNac = ?, direccion = ?, sexo = ? WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, aux.getId());
            pstm.setLong(2, aux.getDni());
            pstm.setDate(3, aux.getFechaNacimiento());
            pstm.setString(4, aux.getDireccion());
            pstm.setString(5, String.valueOf(aux.getSexo()));
            pstm.setInt(6, paciente.getId());
            pstm.execute(sql);
            modify = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase PacienteDAOImpl, metodo modify. " +e.getMessage());
        }
        return modify;
    }

    //Look for PreparedStatement instead of Statement
    @Override
    public List<Paciente> obtain(Paciente paciente) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Paciente ORDER BY id";
        List<Paciente> pacienteList = new ArrayList<Paciente>();
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            while(rs.next()){
                Paciente p = new Paciente();
                p.setDni(rs.getInt(2));
                p.setNombre(rs.getString(3));
                p.setFechaNacimiento(rs.getDate(4));
                p.setDireccion(rs.getString(5));
                p.setSexo(rs.getString(6).charAt(0));
                pacienteList.add(p);
            }
            pstm.close();
            rs.close();
            con.close();
        }catch (SQLException e){
            System.out.println("Error: Clase PacienteDAOImpl, metodo obtain");
            e.printStackTrace();
        }
        return pacienteList;
    }
}
