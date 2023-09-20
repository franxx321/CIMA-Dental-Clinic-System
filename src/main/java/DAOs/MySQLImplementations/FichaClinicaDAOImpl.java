package DAOs.MySQLImplementations;

import DAOs.Interfaces.IFichaClinicaDAO;
import Objetos.FichaClinica;
import Objetos.Paciente;
import Utils.DBUtils.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FichaClinicaDAOImpl implements IFichaClinicaDAO {
    DBConnector DBConnection ;
    Connection con = null;
    @Override
    public boolean register(FichaClinica fichaClinica) {
        boolean register = false;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO FichaClinicas VALUES (?,?,?)";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            //pstm.setInt(1, fichaClinica.getId());
            pstm.setDate(2, fichaClinica.getFecha());
            pstm.setString(3, fichaClinica.getDescripcion());
            pstm.execute(sql);
            register = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase FichaClinicaDAOImpl, metodo register, "+e.getMessage());
        }
        return register;
    }

    // Review tables
    @Override
    public List<FichaClinica> obtain(FichaClinica fichaClinica) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM FichaClinica ORDER BY id";
        List<FichaClinica> fichaClinicaList = new ArrayList<FichaClinica>();
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            while(rs.next()){
                FichaClinica fc = new FichaClinica();
                //fc.setId(rs.getInt(1));
                fc.setFecha(rs.getDate(3));
                fc.setDescripcion(rs.getString(4));
                fichaClinicaList.add(fc);
                pstm.close();
                rs.close();
                con.close();
            }
        }catch (SQLException e){
            System.out.println("Error: Clase FichaClinicaDAOImpl, metodo obtain");
            e.printStackTrace();
        }
        return fichaClinicaList;
    }

    @Override
    public boolean delete(FichaClinica fichaClinica) {
        boolean delete = false;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM FichaClinica WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            //pstm.setInt(1, fichaClinica.getId());
            pstm.execute(sql);
            delete = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase FichaClinicaDAOImpl, metodo delete. " +e.getMessage());
        }
        return delete;
    }

    @Override
    public boolean modify(FichaClinica fichaClinica, FichaClinica aux) {
        boolean modify = false;
        PreparedStatement pstm = null;
        String sql = "UPDATE FichaClinica SET id = ?, fecha = ?, descripcion = ? WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            //pstm.setInt(1, aux.getId());
            pstm.setDate(2, aux.getFecha());
            pstm.setString(3, aux.getDescripcion());
            pstm.execute(sql);
            modify = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase FichaClinicaDAOImpl, metodo modify. " +e.getMessage());
        }
        return modify;
    }
}
