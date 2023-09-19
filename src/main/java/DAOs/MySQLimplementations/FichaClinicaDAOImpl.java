package DAOs.MySQLimplementations;

import DAOs.Interfaces.IFichaClinicaDAO;
import Objetos.FichaClinica;
import Utils.DBUtils.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FichaClinicaDAOImpl implements IFichaClinicaDAO {
    DBConnector DBConnection ;
    Connection con = null;
    @Override
    public boolean register(FichaClinica fichaClinica) {
        boolean register = false;
        
        Statement stm = null;
        String sql = "INSERT INTO FichaClinica values (NULL, "+", "+fichaClinica.getDescripcion()+")";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            stm = con.createStatement();
            stm.execute(sql);
            register = true;
            stm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase FiclaClinicaDAOImpl, metodo register");
        }
        return register;
    }

    // Review tables
    @Override
    public List<FichaClinica> obtain(FichaClinica fichaClinica) {
        
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM FichaClinica ORDER BY id";
        List<FichaClinica> fichaClinicaList = new ArrayList<>();
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                FichaClinica f = new FichaClinica();
                //
                f.setDescripcion(rs.getString(3));
                //
                fichaClinicaList.add(f);
                stm.close();
                con.close();
                rs.close();
            }
        } catch (SQLException e){
            System.out.println("Error: Clase FichaClinicaDAOImpl, metodo obtain");
            e.printStackTrace();
        }
        return fichaClinicaList;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean modify() {
        return false;
    }
}
