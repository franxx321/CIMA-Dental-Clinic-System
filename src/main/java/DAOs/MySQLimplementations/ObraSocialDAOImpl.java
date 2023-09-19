package DAOs.MySQLimplementations;

import DAOs.Interfaces.IObraSocialDAO;
import Objetos.ObraSocial;
import Utils.DBUtils.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ObraSocialDAOImpl implements IObraSocialDAO {
    DBConnector DBConnection ;
    Connection con = null;
    @Override
    public boolean register(ObraSocial obraSocial) {
        boolean register = false;
        
        Statement stm = null;
        String sql = "INSERT INTO ObraSocial values (NULL, "+obraSocial.getNombre()+", "+", "+obraSocial.getTelefono()+","+obraSocial.getNombreRepresentante()+")";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            stm = con.createStatement();
            stm.execute(sql);
            register = true;
            stm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase ObraSocialDAOImpl, metodo register");
        }
        return register;
    }

    @Override
    public List<ObraSocial> obtain(ObraSocial obraSocial) {
        
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM ObraSocial ORDER BY id";
        List<ObraSocial> obraSocialList = new ArrayList<>();
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                ObraSocial o = new ObraSocial();
                o.setNombre(rs.getString(2));
                o.setTelefono(rs.getString(3));
                o.setNombreRepresentante(rs.getString(4));
                obraSocialList.add(o);
                stm.close();
                con.close();
                rs.close();
            }
        } catch (SQLException e){
            System.out.println("Error: Clase ObraSocialImpl, metodo obtain");
            e.printStackTrace();
        }
        return null;
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
