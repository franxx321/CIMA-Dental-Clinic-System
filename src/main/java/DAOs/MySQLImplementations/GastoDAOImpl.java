package DAOs.MySQLImplementations;

import DAOs.Interfaces.IGastoDAO;
import Objetos.FichaClinica;
import Objetos.Gasto;
import Utils.DBUtils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GastoDAOImpl implements IGastoDAO {
    DBConnector DBConnection ;
    Connection con = null;
    @Override
    public boolean register(Gasto gasto) {
        boolean register = false;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO Gasto (monto, descripcion, fecha, id_profesional) VALUES (?,?,?,?,?)";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setFloat(1, gasto.getMonto());
            pstm.setString(2, gasto.getDescripcion());
            pstm.setDate(3, gasto.getFecha());
            pstm.setInt(4, gasto.getIdProfesional());
            pstm.execute(sql);
            register = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase GastoDAOImpl, metodo register, "+e.getMessage());
        }
        return register;
    }

    @Override
    public List<Gasto> obtain(Gasto gasto) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Gasto ORDER BY id";
        List<Gasto> gastoList = new ArrayList<>();
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            while(rs.next()){
                Gasto g = new Gasto();
                g.setId(rs.getInt(1));
                g.setMonto(rs.getInt(2));
                g.setDescripcion(rs.getString(3));
                g.setFecha(rs.getDate(4));
                g.setIdProfesional(rs.getInt(5));
                gastoList.add(g);
            }
            pstm.close();
            rs.close();
            con.close();
        }catch (SQLException e){
            System.out.println("Error: Clase GastoDAOImpl, metodo obtain");
            e.printStackTrace();
        }
        return gastoList;
    }

    @Override
    public boolean delete(Gasto gasto) {
        boolean delete = false;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM Gasto WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, gasto.getId());
            pstm.execute(sql);
            delete = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase GastoDAOImpl, metodo delete. " +e.getMessage());
        }
        return delete;
    }

    @Override
    public boolean modify(Gasto gasto, Gasto aux) {
        boolean modify = false;
        PreparedStatement pstm = null;
        String sql = "UPDATE Gasto SET id = ?, monto = ?, descripcion = ?, fecha = ?, id_profesional = ? WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, aux.getId());
            pstm.setFloat(2, aux.getMonto());
            pstm.setString(3, aux.getDescripcion());
            pstm.setDate(4, aux.getFecha());
            pstm.setInt(5, aux.getIdProfesional());
            pstm.setInt(6, gasto.getId());
            pstm.execute(sql);
            modify = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase GastoDAOImpl, metodo modify. " +e.getMessage());
        }
        return modify;
    }
}
