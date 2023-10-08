package DAOs.MySQLImplementations;

import DAOs.Interfaces.ITurnoPrestacionDAO;
import Objetos.Prestacion;
import Objetos.Turno;
import Objetos.TurnoPrestacion;
import Utils.DBUtils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurnoPrestacionDAOImpl implements ITurnoPrestacionDAO {
    private static TurnoPrestacionDAOImpl turnoPrestacionDAO;
    public static TurnoPrestacionDAOImpl getInstance(){
        if(turnoPrestacionDAO == null){
            turnoPrestacionDAO = new TurnoPrestacionDAOImpl();
        }
        return turnoPrestacionDAO;
    }
    private TurnoPrestacionDAOImpl(){
    }
    DBConnector DBConnection ;
    Connection con = null;
    @Override
    public boolean register(TurnoPrestacion turnoPrestacion) {
        boolean register = false;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO TurnosPrestaciones(id_turno, id_prestacion) VALUES(?,?)";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement("INSERT INTO turnosprestaciones (id_turno, id_prestacion) Values (?,?)");
            pstm.setInt(1, turnoPrestacion.getIdTurno());
            pstm.setInt(2, turnoPrestacion.getIdPrestacion());
            pstm.executeUpdate();
            register = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase TurnoPrestacionDAOImpl, metodo register "+e.getMessage());
        }
        return register;
    }

    @Override
    public List<TurnoPrestacion> obtain() {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM TurnosPrestaciones ORDER BY id";
        List<TurnoPrestacion> turnoPrestacionList = new ArrayList<TurnoPrestacion>();
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
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
        String sql = "DELETE FROM TurnosPrestaciones WHERE id = ? ";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
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
        String sql = "UPDATE TurnosPrestaciones SET id_turno = ?, id_prestacion = ? WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
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

    @Override
    public List<TurnoPrestacion> getByTurnoId(int turnoId) {
        List <TurnoPrestacion>  turnoPrestacionList= null;
        try {
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement("SELECT * " +
                    "from turnosprestaciones" +
                    " where id_turno =? " +
                    "ORDER BY id_prestacion");
            ptsm.setInt(1,turnoId);
            ResultSet rs = ptsm.executeQuery();
            if(rs.next()){
                turnoPrestacionList= new ArrayList<>();
                do{
                    TurnoPrestacion turnoPrestacion = new TurnoPrestacion();
                    turnoPrestacion.setId(rs.getInt(1));
                    turnoPrestacion.setIdTurno(rs.getInt(2));
                    turnoPrestacion.setIdPrestacion(rs.getInt(3));
                    turnoPrestacionList.add(turnoPrestacion);
                }while (rs.next());
            }
        }
        catch (SQLException e){
            System.out.println("fallo get by turno id TurnoPrestacionDAOIMPL "+ e.getMessage());
        }
        return turnoPrestacionList;
    }

    public boolean deleteByTurno (int turnoId){
        boolean delete= true;
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement("DELETE FROM turnosprestaciones " +
                    "WHERE id_turno = ?");
            ptsm.setInt(1,turnoId);
            ptsm.executeUpdate();
            con.close();
            delete=true;
        }
        catch (SQLException e){
            System.out.println("fallo delete By Turno TurnoPrestacionDAO IMPL "+ e.getMessage());
        }

        return delete;
    }
}
