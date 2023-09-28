package DAOs.MySQLImplementations;

import DAOs.Interfaces.IHistorialClinicoDAO;
import Objetos.HistorialClinico;
import Utils.DBUtils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistorialClinicoDAOImpl implements IHistorialClinicoDAO {
    DBConnector DBConnection ;
    Connection con = null;
    @Override
    public boolean register(HistorialClinico historialClinico) {
        boolean register = false;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO HistorialClinico(id_paciente, id_fichasclinicas) VALUES (?,?)";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, historialClinico.getIdPaciente());
            //pstm.setInt(2, historialClinico.getIdFichasClinicas());
            pstm.execute(sql);
            register = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase HistorialClinicoDAOImpl, metodo register, "+e.getMessage());
        }
        return register;
    }

    @Override
    public List<HistorialClinico> obtain(HistorialClinico historialClinico) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM HistorialClinico ORDER BY id";
        List<HistorialClinico> historialClinicoList = new ArrayList<HistorialClinico>();
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            while(rs.next()){
                HistorialClinico hs = new HistorialClinico();
                hs.setIdPaciente(rs.getInt(1));
                //hs.setId_FichasClinica(rs.getInt(2));
                historialClinicoList.add(hs);
            }
            pstm.close();
            rs.close();
            con.close();
        }catch (SQLException e){
            System.out.println("Error: Clase HistorialClinicoDAOImpl, metodo obtain");
            e.printStackTrace();
        }
        return historialClinicoList;
    }

    @Override
    public boolean delete(HistorialClinico historialClinico) {
        boolean delete = false;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM HistorialClinico WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, historialClinico.getIdPaciente());
            //pstm.setInt(2, historialClinico.getId_FichasClinicas());
            pstm.execute(sql);
            delete = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase HistorialClinicoDAOImpl, metodo delete. " +e.getMessage());
        }
        return delete;
    }

    @Override
    public boolean modify(HistorialClinico historialClinico, HistorialClinico aux) {
        boolean modify = false;
        PreparedStatement pstm = null;
        String sql = "UPDATE HistorialClinico SET id_paciente = ?, id_fichasclinica = ? WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, aux.getIdPaciente());
            //pstm.setInt(2, aux.getId_FichasClinica());
            pstm.execute(sql);
            modify = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase HistorialClinicoDAOImpl, metodo modify. " +e.getMessage());
        }
        return modify;
    }
}
