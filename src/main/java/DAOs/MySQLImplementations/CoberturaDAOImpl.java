package DAOs.MySQLImplementations;

import DAOs.Interfaces.ICoberturaDAO;
import Objetos.Cobertura;
import Utils.DBUtils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoberturaDAOImpl implements ICoberturaDAO {
    private static CoberturaDAOImpl coberturaDAO;
    public static CoberturaDAOImpl getInstance(){
        if(coberturaDAO == null){
            coberturaDAO = new CoberturaDAOImpl();
        }
        return coberturaDAO;
    }
    private CoberturaDAOImpl(){
    }
    private DBConnector DBConnection ;
    private Connection con = null;
    @Override
    public boolean register(Cobertura cobertura) {
        boolean register = false;
        PreparedStatement pstm;
        String sql = "INSERT INTO Coberturas(id_obrasocial, id_prestaciones, porcentaje, tope,codigo) VALUES (?,?,?,?,?)";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, cobertura.getIdObraSocial());
            pstm.setInt(2, cobertura.getIdPrestacion());
            pstm.setFloat(3, cobertura.getPorcentaje());
            pstm.setFloat(4, cobertura.getTope());
            pstm.setString(5,cobertura.getCodigo());
            pstm.executeUpdate();
            register = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase CoberturaDAOImpl, metodo register "+e.getMessage());
        }
        return register;
    }

    @Override
    public List<Cobertura> obtain() {
        PreparedStatement pstm ;
        ResultSet rs ;
        String sql = "SELECT * FROM Coberturas ";
        List<Cobertura> coberturaList = new ArrayList<>();
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            while(rs.next()){
                Cobertura c = new Cobertura();
                c.setIdObraSocial(rs.getInt(1));
                c.setIdPrestacion(rs.getInt(2));
                c.setPorcentaje(rs.getFloat(3));
                c.setTope(rs.getFloat(4));
                coberturaList.add(c);
            }
            pstm.close();
            rs.close();
            con.close();
        }catch (SQLException e){
            System.out.println("Error: Clase CoberturaDAOImpl, metodo obtain");
            e.printStackTrace();
        }
        return coberturaList;
    }

    @Override
    public boolean delete(Cobertura cobertura) {
        boolean delete = false;
        PreparedStatement pstm;
        String sql = "DELETE FROM Coberturas WHERE id_obrasocial = ? AND id_prestaciones = ?";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, cobertura.getIdObraSocial());
            pstm.setInt(2, cobertura.getIdPrestacion());
            pstm.executeUpdate();
            delete = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase CoberturaDAOImpl, metodo delete. " +e.getMessage());
        }
        return delete;
    }

    @Override
    public boolean modify(Cobertura cobertura, Cobertura aux) {
        boolean modify = false;
        PreparedStatement pstm;
        String sql = "UPDATE Coberturas SET id_obrasocial = ?, id_prestaciones = ?, porcentaje = ?, tope = ?, codigo = ? WHERE id_obrasocial = ? AND id_prestaciones =?";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, aux.getIdObraSocial());
            pstm.setInt(2, aux.getIdPrestacion());
            pstm.setFloat(3, aux.getPorcentaje());
            pstm.setFloat(4, aux.getTope());
            pstm.setString(5,aux.getCodigo());
            pstm.setInt(6, cobertura.getIdObraSocial());
            pstm.setInt(7,cobertura.getIdPrestacion());
            pstm.executeUpdate();
            modify = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase FichaClinicaDAOImpl, metodo modify. " +e.getMessage());
        }
        return modify;
    }


    @Override
    public List <Cobertura> getCoberturaByIdObraSocial(Cobertura cobertura){

        List <Cobertura> coberturaList = null;
        
        try{
            DBConnection=DBConnector.getInstance();
            DBConnection.startConnection();
            con=DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement("SELECT * FROM coberturas Where id_obrasocial = ?");
            ptsm.setInt(1,cobertura.getIdObraSocial());
            ResultSet rs = ptsm.executeQuery();
            coberturaList = new ArrayList<>();
            while(rs.next()){
                Cobertura c = new Cobertura();
                c.setIdObraSocial(rs.getInt(1));
                c.setIdPrestacion(rs.getInt(2));
                c.setPorcentaje(rs.getInt(3));
                c.setTope(rs.getInt(4));
                c.setCodigo(rs.getString(5));
                coberturaList.add(c);
            }
            con.close();
        }
        catch (SQLException e){
            System.out.println("Fallo Cobertura DAO IMPL get by id obra social"+e.getMessage());
        }

        return coberturaList ;

    }
}
