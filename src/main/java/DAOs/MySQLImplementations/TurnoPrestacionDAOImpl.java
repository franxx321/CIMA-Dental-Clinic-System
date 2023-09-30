package DAOs.MySQLImplementations;

import DAOs.Interfaces.ITurnoPrestacionDAO;
import Objetos.TurnoPrestacion;
import Utils.DBUtils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurnoPrestacionDAOImpl implements ITurnoPrestacionDAO {
    DBConnector DBConnection ;
    Connection con = null;
    @Override
    public boolean register(TurnoPrestacion turnoPrestacion) {
        boolean register = false;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO TurnoPrestacion(id_turno, id_prestacion) VALUES (?,?)";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, turnoPrestacion.getIdTurno());
            pstm.setInt(2, turnoPrestacion.getIdPrestacion());
            pstm.execute(sql);
            register = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase TurnoPrestacionDAOImpl, metodo register "+e.getMessage());
        }
        return register;
    }

    @Override
    public List<TurnoPrestacion> obtain(TurnoPrestacion turnoPrestacion) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM TurnoPrestacion ORDER BY id";
        List<TurnoPrestacion> turnoPrestacionList = new ArrayList<TurnoPrestacion>();
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            while(rs.next()){
                TurnoPrestacion tp = new TurnoPrestacion();
                tp.setId(rs.getInt(1));
                tp.setIdTurno(rs.getInt(2));
                tp.setIdPrestacion(rs.getInt(3));
                turnoPrestacionList.add(tp);
            }
            pstm.close();
            rs.close();
            con.close();
        }catch (SQLException e){
            System.out.println("Error: Clase TurnoPrestacionDAOImpl, metodo obtain");
            e.printStackTrace();
        }
        return turnoPrestacionList;
    }

    @Override
    public boolean delete(TurnoPrestacion turnoPrestacion) {
        boolean delete = false;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM TurnoPrestacion WHERE id = ? ";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, turnoPrestacion.getId());
            pstm.execute(sql);
            delete = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase TurnoPrestacionDAOImpl, metodo delete. " +e.getMessage());
        }
        return delete;
    }

    @Override
    public boolean modify(TurnoPrestacion turnoPrestacion, TurnoPrestacion aux) {
        boolean modify = false;
        PreparedStatement pstm = null;
        String sql = "UPDATE TurnoPrestacion SET id_turno = ?, id_prestacion = ? WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, aux.getIdTurno());
            pstm.setInt(2, aux.getIdPrestacion());
            pstm.setInt(3, turnoPrestacion.getId());
            pstm.execute(sql);
            modify = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase TurnoPrestacionDAOImpl, metodo modify. " +e.getMessage());
        }
        return modify;
    }
}
