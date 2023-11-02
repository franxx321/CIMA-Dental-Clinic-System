package DAOs.MySQLImplementations;

import DAOs.Interfaces.IObraSocialDAO;
import Objetos.ObraSocial;
import Utils.DBUtils.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObraSocialDAOImpl implements IObraSocialDAO {
    private static ObraSocialDAOImpl obraSocialDAO;
    public static ObraSocialDAOImpl getInstance(){
        if(obraSocialDAO == null){
            obraSocialDAO = new ObraSocialDAOImpl();
        }
        return obraSocialDAO;
    }
    private ObraSocialDAOImpl(){
    }
    DBConnector DBConnection ;
    Connection con = null;
    @Override
    public boolean register(ObraSocial obraSocial) {
        boolean register = false;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO ObrasSociales (nombre, mail, telefono, nombrerep) VALUES (?,?,?,?)";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, obraSocial.getNombre());
            pstm.setString(2, obraSocial.getMail());
            pstm.setString(3, obraSocial.getTelefono());
            pstm.setString(4, obraSocial.getNombreRepresentante());
            pstm.executeUpdate();
            register = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase ObraSocialDAOImpl, metodo register "+e.getMessage());
        }
        return register;
    }

    @Override
    public List<ObraSocial> obtain() {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM ObrasSociales ORDER BY id";
        List<ObraSocial> obraSocialList = new ArrayList<ObraSocial>();
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            while(rs.next()){
                ObraSocial os = new ObraSocial();
                os.setId(rs.getInt(1));
                os.setNombre(rs.getString(2));
                os.setMail(rs.getString(3));
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
        String sql = "DELETE FROM ObrasSociales WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, obraSocial.getId());
            pstm.executeUpdate();
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
        String sql = "UPDATE ObrasSociales SET  nombre = ?, mail = ?, telefono = ?, nombrerep = ? WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(2, aux.getMail());
            pstm.setString(3, aux.getTelefono());
            pstm.setString(4, aux.getNombreRepresentante());
            pstm.setInt(5, obraSocial.getId());
            pstm.executeUpdate();
            modify = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase ObraSocialDAOImpl, metodo modify. " +e.getMessage());
        }
        return modify;
    }

    @Override
    public ObraSocial getByName(ObraSocial oS) {
        ObraSocial obraSocial = null;

        return null;
    }
}
