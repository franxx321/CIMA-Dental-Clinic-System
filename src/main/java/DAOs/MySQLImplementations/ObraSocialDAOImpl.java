package DAOs.MySQLImplementations;

import DAOs.Interfaces.IObraSocialDAO;
import Objetos.FichaClinica;
import Objetos.ObraSocial;
import Utils.DBUtils.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObraSocialDAOImpl implements IObraSocialDAO {
    DBConnector DBConnection ;
    Connection con = null;
    @Override
    public boolean register(ObraSocial obraSocial) {
        boolean register = false;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO ObraSocial VALUES (?,?,?,?,?)";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            //pstm.setInt(1, obraSocial.getId());
            pstm.setString(2, obraSocial.getNombre());
            //pstm.setString(3, obraSocial.getMail());
            pstm.setString(4, obraSocial.getTelefono());
            pstm.setString(5, obraSocial.getNombreRepresentante());
            pstm.execute(sql);
            register = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase ObraSocialDAOImpl, metodo register "+e.getMessage());
        }
        return register;
    }

    @Override
    public List<ObraSocial> obtain(ObraSocial obraSocial) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM ObraSocial ORDER BY id";
        List<ObraSocial> obraSocialList = new ArrayList<ObraSocial>();
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            while(rs.next()){
                ObraSocial os = new ObraSocial();
                //os.setId(rs.getInt(1));
                os.setNombre(rs.getString(2));
                //os.setDireccion(rs.getString(3));
                os.setTelefono(rs.getString(4));
                os.setNombreRepresentante(rs.getString(5));
                obraSocialList.add(os);
            }
            pstm.close();
            rs.close();
            con.close();
        }catch (SQLException e){
            System.out.println("Error: Clase ObraSocialDAOImpl, metodo obtain");
            e.printStackTrace();
        }
        return obraSocialList;
    }

    @Override
    public boolean delete(ObraSocial obraSocial) {
        boolean delete = false;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM ObraSocial WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            //pstm.setInt(1, os.getId());
            pstm.execute(sql);
            delete = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase ObraSocialDAOImpl, metodo delete" +e.getMessage());
        }
        return delete;
    }

    @Override
    public boolean modify(ObraSocial obraSocial, ObraSocial aux) {
        boolean modify = false;
        PreparedStatement pstm = null;
        String sql = "UPDATE ObraSocial SET id = ?, nombre = ?, direccion = ?, telefono = ?, nombrerep = ? WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            //pstm.setInt(1, aux.getId());
            //pstm.setString(2, aux.getDireccion());
            pstm.setString(3, aux.getTelefono());
            pstm.setString(4, aux.getNombreRepresentante());
            //pstm.setInt(5, obraSocial.getId());
            pstm.execute(sql);
            modify = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase ObraSocialDAOImpl, metodo modify. " +e.getMessage());
        }
        return modify;
    }
}
