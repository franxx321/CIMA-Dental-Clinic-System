package DAOs.MySQLImplementations;

import DAOs.Interfaces.IPrestacionDAO;
import Objetos.Prestacion;
import Utils.DBUtils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrestacionDAOImpl implements IPrestacionDAO {
    private static PrestacionDAOImpl prestacionDAO;
    public static PrestacionDAOImpl getInstance(){
        if(prestacionDAO ==null){
            prestacionDAO= new PrestacionDAOImpl();
        }
        return prestacionDAO;
    }
    private PrestacionDAOImpl() {
    }
    DBConnector DBConnection ;
    Connection con = null;
    @Override
    public boolean register(Prestacion prestacion) {
        boolean register = false;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO Prestaciones(nombre, bien, descripcion) VALUES (?,?,?)";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, prestacion.getNombre());
            pstm.setBoolean(2, prestacion.isBien());
            pstm.setString(3, prestacion.getDescripcion());
            pstm.executeUpdate();
            register = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase PrestacionDAOImpl, metodo register "+e.getMessage());
        }
        return register;
    }

    @Override
    public List<Prestacion> obtain() {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Prestaciones ORDER BY id";
        List<Prestacion> prestacionList = new ArrayList<Prestacion>();
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            while(rs.next()){
                Prestacion p = new Prestacion();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setBien(rs.getBoolean(3));
                p.setDescripcion(rs.getString(4));
                prestacionList.add(p);
            }
            pstm.close();
            rs.close();
            con.close();
        }catch (SQLException e){
            System.out.println("Error: Clase PrestacionDAOImpl, metodo obtain");
            e.printStackTrace();
        }
        return prestacionList;
    }

    @Override
    public boolean delete(Prestacion prestacion) {
        boolean delete = false;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM Prestaciones WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, prestacion.getId());
            pstm.executeUpdate();
            delete = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase PrestacionDAOImpl, metodo delete. " +e.getMessage());
        }
        return delete;
    }

    @Override
    public boolean modify(Prestacion prestacion, Prestacion aux) {
            boolean modify = false;
            PreparedStatement pstm = null;
            String sql = "UPDATE Prestaciones SET id = ?, nombre = ?, bien = ?, descripcion = ? WHERE id = ?";
            try{
                DBConnection = DBConnector.getInstance();
                DBConnection.startConnection();
                con = DBConnection.getConnection();
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, aux.getId());
                pstm.setString(2, aux.getNombre());
                pstm.setBoolean(3, aux.isBien());
                pstm.setString(4, aux.getDescripcion());
                pstm.setInt(5, aux.getId());
                pstm.executeUpdate();
                modify = true;
                pstm.close();
                con.close();
            } catch (SQLException e){
                System.out.println("Error: Clase PrestacionDAOImpl, metodo modify. " +e.getMessage());
            }
            return modify;
        }

    @Override
    public int idByName(String nombre) {
        int id = 0;
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement("SELECT id FROM prestaciones WHERE nombre = ?");
            ptsm.setString(1, nombre);
            ResultSet rs = ptsm.executeQuery();
            if(!rs.next()){
                id = -1;
            }else{
                id = rs.getInt(1);
            }
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase PrestacionDAOImpl, metodo idByName" + e.getMessage()
            );
        }
        return id;
    }
}
