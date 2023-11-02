package DAOs.MySQLImplementations;

import DAOs.Interfaces.IMontoDAO;
import Objetos.Monto;
import Objetos.Turno;
import Utils.DBUtils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MontoDAOImpl implements IMontoDAO {
    private static MontoDAOImpl montoDAO;
    public static MontoDAOImpl getInstance(){
        if(montoDAO == null){
            montoDAO = new MontoDAOImpl();
        }
        return montoDAO;
    }
    private MontoDAOImpl(){
    }
    DBConnector DBConnection ;
    Connection con = null;
    @Override
    public boolean register(Monto monto) {
        boolean register = false;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO precios(precio, id_prestacion, id_profesional) VALUES (?,?,?)";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setFloat(1, monto.getPrecio());
            pstm.setInt(2, monto.getIdPrestacion());
            pstm.setInt(3, monto.getIdProfesional());
            pstm.executeUpdate();
            register = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase MontoDAOImpl, metodo register "+e.getMessage());
        }
        return register;
    }

    @Override
    public List<Monto> obtain() {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM precios ORDER BY id";
        List<Monto> montoList = new ArrayList<Monto>();
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            while(rs.next()){
                Monto m = new Monto();
                m.setPrecio(rs.getFloat(1));
                m.setIdPrestacion(rs.getInt(2));
                m.setIdProfesional(rs.getInt(3));
                montoList.add(m);
            }
            pstm.close();
            rs.close();
            con.close();
        }catch (SQLException e){
            System.out.println("Error: Clase MontoDAOImpl, metodo obtain");
            e.printStackTrace();
        }
        return montoList;
    }

    @Override
    public boolean delete(Monto monto) {
        boolean delete = false;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM precios WHERE id_prestacion = ? AND id_profesional = ?";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, monto.getIdPrestacion());
            pstm.setInt(2, monto.getIdProfesional());
            pstm.executeUpdate();
            delete = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase MontoDAOImpl, metodo delete. " +e.getMessage());
        }
        return delete;
    }

    @Override
    public boolean modify(Monto monto, Monto aux) {
        boolean modify = false;
        PreparedStatement pstm = null;
        String sql = "UPDATE precios SET precio = ?, id_prestacion = ?, id_profesional = ? WHERE id_prestacion = ? AND id_profesional = ?";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setFloat(1, aux.getPrecio());
            pstm.setInt(2, aux.getIdPrestacion());
            pstm.setInt(3, aux.getIdProfesional());
            pstm.setInt(4, monto.getIdPrestacion());
            pstm.setInt(5, monto.getIdProfesional());
            pstm.executeUpdate();
            modify = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase MontoDAOImpl, metodo modify. " +e.getMessage());
        }
        return modify;
    }

    @Override
    public Monto getByIds(int idProfesional, int idPrestacion) {
        return null;
    }

    public List <Monto> getMontoByIdProfesional(int profesionalId){
        List <Monto> montoList = null;
        try{
            DBConnection=DBConnector.getInstance();
            DBConnection.startConnection();
            con=DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement("SELECT * FROM precios Where id_profesional = ?");
            ptsm.setInt(1,profesionalId);
            ResultSet rs = ptsm.executeQuery();
            montoList= new ArrayList<>();
            while(rs.next()){
                Monto m = new Monto();
                m.setPrecio(rs.getFloat(1));
                m.setIdPrestacion(rs.getInt(2));
                m.setIdProfesional(rs.getInt(3));
                montoList.add(m);
            }
            con.close();
        }
        catch (SQLException e){
            System.out.println("Fallo Monto DAO IMPL get by id profesional"+e.getMessage());
        }

        return montoList;

    }
}
