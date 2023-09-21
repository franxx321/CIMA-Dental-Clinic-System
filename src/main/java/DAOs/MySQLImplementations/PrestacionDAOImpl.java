package DAOs.MySQLImplementations;

import DAOs.Interfaces.IPrestacionDAO;
import Objetos.FichaClinica;
import Objetos.Prestacion;
import Utils.DBUtils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrestacionDAOImpl implements IPrestacionDAO {
    DBConnector DBConnection ;
    Connection con = null;
    @Override
    public boolean register(Prestacion prestacion) {
        boolean register = false;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO Prestacion VALUES (?,?,?,?)";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, prestacion.getId());
            pstm.setString(2, prestacion.getNombre());
            pstm.setBoolean(3, prestacion.isBien());
            pstm.setString(4, prestacion.getDescripcion());
            pstm.execute(sql);
            register = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase PrestacionDAOImpl, metodo register "+e.getMessage());
        }
        return register;
    }

    @Override
    public List<Prestacion> obtain(Prestacion prestacion) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Prestacion ORDER BY id";
        List<Prestacion> prestacionList = new ArrayList<Prestacion>();
        try{
            DBConnection = DBConnector.getInstance();
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
        String sql = "DELETE FROM Prestacion WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, prestacion.getId());
            pstm.execute(sql);
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
            String sql = "UPDATE Prestacion SET id = ?, nombre = ?, bien = ?, descripcion = ? WHERE id = ?";
            try{
                DBConnection = DBConnector.getInstance();
                con = DBConnection.getConnection();
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, aux.getId());
                pstm.setString(2, aux.getNombre());
                pstm.setBoolean(3, aux.isBien());
                pstm.setString(4, aux.getDescripcion());
                pstm.setInt(5, aux.getId());
                pstm.execute(sql);
                modify = true;
                pstm.close();
                con.close();
            } catch (SQLException e){
                System.out.println("Error: Clase PrestacionDAOImpl, metodo modify. " +e.getMessage());
            }
            return modify;
        }
    }
