package DAOs.MySQLImplementations;

import DAOs.Interfaces.IPacienteDAO;
import Objetos.Paciente;
import Utils.DBUtils.DBConnector;

import java.net.UnknownHostException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOImpl implements IPacienteDAO {
    DBConnector DBConnection ;
    Connection con = null;

    private static PacienteDAOImpl pacienteDAO;

    public static PacienteDAOImpl getInstance(){
        if (pacienteDAO==null){
            pacienteDAO = new PacienteDAOImpl();
        }
        return pacienteDAO;
    }

    @Override
    public boolean register(Paciente paciente) {
        boolean register = false;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO Pacientes(nombreyapellido,dni, fechanac, direccion, sexo) VALUES (?,?,?,?,?)";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            DBConnection.startConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1,paciente.getNombre());
            pstm.setLong(2, paciente.getDni());
            pstm.setDate(3, paciente.getFechaNacimiento());
            pstm.setString(4, paciente.getEmail());
            pstm.setString(5, String.valueOf(paciente.getSexo()));
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
        String sql = "DELETE FROM Pacientes WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
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
        String sql = "UPDATE Pacientes SET nombreyapellido = ?, id = ?, dni = ?, fechaNac = ?, direccion = ?, sexo = ? WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1,aux.getNombre());
            pstm.setInt(2, aux.getId());
            pstm.setLong(3, aux.getDni());
            pstm.setDate(4, aux.getFechaNacimiento());
            pstm.setString(5, aux.getEmail());
            pstm.setString(6, String.valueOf(aux.getSexo()));
            pstm.setInt(7, paciente.getId());
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
    public List<Paciente> obtain() {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Pacientes ORDER BY id";
        List<Paciente> pacienteList = new ArrayList<Paciente>();
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            while(rs.next()){
                Paciente p = new Paciente();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setDni(rs.getInt(3));
                p.setFechaNacimiento(rs.getDate(4));
                p.setEmail(rs.getString(5));
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


    @Override
    public Paciente getByDni(long dni){
        Paciente paciente = new Paciente();
        try{
        DBConnector connector = DBConnector.getInstance();
        connector.startConnection();
        Connection conn = connector.getConnection();
        PreparedStatement ptsm = conn.prepareStatement("SELECT * FROM pacientes WHERE dni = ?");
        ptsm.setLong(1,dni);
        ResultSet rs = ptsm.executeQuery();
        if(!rs.next()){
            paciente.setDni(-1);
        }
        else {
            paciente.setId(rs.getInt(1));
            paciente.setNombre(rs.getString(2));
            paciente.setDni(rs.getLong(3));
            paciente.setFechaNacimiento(rs.getDate(4));
            paciente.setEmail(rs.getString(5));
            paciente.setSexo(rs.getString(6).charAt(0));
        }
        conn.close();
        }
        catch  (SQLException e){
            System.out.println("fallo paciente Dao"+e.getMessage());
        }

        return paciente;
    }

    @Override
    public Paciente getById(Paciente paciente) {
        Paciente paciente1 = null;
        try{
            DBConnector connector = DBConnector.getInstance();
            connector.startConnection();
            Connection conn = connector.getConnection();
            PreparedStatement ptsm = conn.prepareStatement("SELECT * FROM pacientes WHERE id = ?");
            ptsm.setInt(1,paciente.getId());
            ResultSet rs = ptsm.executeQuery();
            paciente1 = new Paciente();
            if(!rs.next()){
                paciente1.setDni(-1);
            }
            else {
                paciente1.setId(rs.getInt(1));
                paciente1.setNombre(rs.getString(2));
                paciente1.setDni(rs.getLong(3));
                paciente1.setFechaNacimiento(rs.getDate(4));
                paciente1.setEmail(rs.getString(5));
                paciente1.setSexo(rs.getString(6).charAt(0));
            }
            conn.close();
        }
        catch  (SQLException e){
            System.out.println("fallo paciente Dao"+e.getMessage());
        }
        return paciente1 ;
    }
}
