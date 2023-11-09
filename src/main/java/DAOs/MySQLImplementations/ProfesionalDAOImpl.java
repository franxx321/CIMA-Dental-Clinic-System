package DAOs.MySQLImplementations;

import DAOs.Interfaces.IProfesionalDAO;
import Objetos.Paciente;
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

    private static ProfesionalDAOImpl profesionalDAO;

    public static ProfesionalDAOImpl getInstance(){
        if(profesionalDAO==null){
            profesionalDAO = new ProfesionalDAOImpl();
        }
        return profesionalDAO;
    }
    private ProfesionalDAOImpl(){

    }

    @Override
    public boolean register(Profesional profesional) {
        boolean register = false;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO Profesionales (nombreyapellido, telefono, matricula) VALUES (?,?,?,?)";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, profesional.getNombre());
            pstm.setString(2, profesional.getTelefono());
            pstm.setString(3, profesional.getMatricula());
            pstm.executeUpdate();
            register = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase ProfesionalDAOImpl, metodo register" +e.getMessage());
        }
        return register;
    }

    @Override
    public List<Profesional> obtain() {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Profesionales ORDER BY id";
        List<Profesional> profesionalList = new ArrayList<Profesional>();
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            while(rs.next()){
                Profesional p = new Profesional();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setTelefono(rs.getString(3));
                p.setMatricula(rs.getString(4));
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
        String sql = "UPDATE Profesionales SET id = ?, nombreyapellido = ?,  telefono = ?, matricula = ? WHERE id = ?";
        try {
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, profesional.getId());
            pstm.setString(2, profesional.getNombre());
            pstm.setString(3, profesional.getTelefono());
            pstm.setString(4, profesional.getMatricula());
            pstm.setInt(5, profesional.getId());
            pstm.executeUpdate();
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
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, profesional.getId());
            pstm.executeUpdate();
            delete = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase PacienteDAOImpl. metodo delete. " +e.getMessage());
        }
        return delete;
    }

    @Override
    public Profesional getProfesionalByName(Profesional profesional) {
        int id = 0;
        Profesional profesional1= null;
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement("SELECT * FROM profesionales WHERE nombreyapellido =  ?");
            ptsm.setString(1, profesional.getNombre());
            ResultSet rs = ptsm.executeQuery();
            profesional = new Profesional();
            if(!rs.next()){
                profesional.setId(-1);
            } else{
                profesional.setId(rs.getInt(1));
                profesional.setNombre(rs.getString(2));
                profesional.setTelefono(rs.getString(3));
                profesional.setMatricula(rs.getString(4));
            }
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase ProfesionalDAOImpl, metodo getIdProfesional" + e.getMessage());
        }
        return profesional;
    }

    @Override
    public Profesional getProfesionalById(Profesional profesional) {
        Profesional profesional1= null;
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement("SELECT * FROM profesionales WHERE id =  ?");
            ptsm.setInt(1, profesional.getId());
            ResultSet rs = ptsm.executeQuery();
            profesional1 = new Profesional();
            if(!rs.next()){
                profesional1.setId(-1);
            } else{
                profesional1.setId(rs.getInt(1));
                profesional1.setNombre(rs.getString(2));
                profesional1.setTelefono(rs.getString(3));
                profesional1.setMatricula(rs.getString(4));
            }
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase ProfesionalDAOImpl, metodo getProfesionalById" + e.getMessage());
        }
        return profesional1;
    }
}
