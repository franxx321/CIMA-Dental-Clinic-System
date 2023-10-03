package DAOs.MySQLImplementations;

import DAOs.Interfaces.IProfesionalDAO;
import Objetos.Profesional;
import Utils.DBUtils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfesionalDAOImpl implements IProfesionalDAO {
    DBConnector DBConnection;
    Connection con = null;
    @Override
    public boolean register(Profesional profesional) {
        boolean register = false;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO Profesionales (nombre, apellido, telefono, matricula) VALUES (?,?,?,?)";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, profesional.getNombre());
            pstm.setString(2, profesional.getApellido());
            pstm.setString(3, profesional.getTelefono());
            pstm.setString(4, profesional.getMatricula());
            pstm.execute(sql);
            register = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase ProfesionalDAOImpl, metodo register" +e.getMessage());
        }
        return register;
    }

    @Override
    public List<Profesional> obtain(Profesional profesional) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Profesionales ORDER BY id";
        List<Profesional> profesionalList = new ArrayList<Profesional>();
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            while(rs.next()){
                Profesional p = new Profesional();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setApellido(rs.getString(3));
                p.setTelefono(rs.getString(4));
                p.setMatricula(rs.getString(5));
                profesionalList.add(p);
            }
            pstm.close();
            rs.close();
            con.close();
        }catch (SQLException e){
            System.out.println("Error: Clase ProfesionalDAOImpl, metodo obtain");
            e.printStackTrace();
        }
        return profesionalList;
    }

    @Override
    public boolean modify(Profesional profesional, Profesional aux) {
        boolean modify = false;
        PreparedStatement pstm = null;
        String sql = "UPDATE Profesionales SET id = ?, nombre = ?, apellido = ?, telefono = ?, matricula = ? WHERE id = ?";
        try {
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, profesional.getId());
            pstm.setString(2, profesional.getNombre());
            pstm.setString(3, profesional.getApellido());
            pstm.setString(4, profesional.getTelefono());
            pstm.setString(5, profesional.getMatricula());
            pstm.setInt(6, profesional.getId());
            pstm.execute(sql);
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase ProfesionalDAOImpl, metodo modify" +e.getMessage());
        }
        return modify;
    }

    @Override
    public boolean delete(Profesional profesional) {
        boolean delete = false;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM Profesionales WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, profesional.getId());
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
    public int getIdProfesional(String nombre) {
        int id = 0;
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement("SELECT id FROM profesional WHERE nombreyapellido =  ?");
            ptsm.setString(1, nombre);
            ResultSet rs = ptsm.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) {
                id = -1;
            } else{
                id = rs.getInt(1);
            }
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase ProfesionalDAOImpl, metodo getIdProfesional" + e.getMessage());
        }
        return id;
    }
}
