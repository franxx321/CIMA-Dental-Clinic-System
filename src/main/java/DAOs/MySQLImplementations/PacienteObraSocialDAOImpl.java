package DAOs.MySQLImplementations;

import DAOs.Interfaces.IPacienteObraSocialDAO;
import Objetos.PacienteObraSocial;
import Objetos.TurnoPrestacion;
import Utils.DBUtils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteObraSocialDAOImpl implements IPacienteObraSocialDAO {
    private static PacienteObraSocialDAOImpl pacienteObraSocialDAO;
    public static PacienteObraSocialDAOImpl getInstance(){
        if(pacienteObraSocialDAO == null){
            pacienteObraSocialDAO = new PacienteObraSocialDAOImpl();
        }
        return pacienteObraSocialDAO;
    }
    private PacienteObraSocialDAOImpl(){
    }
    DBConnector DBConnection ;
    Connection con = null;
    @Override
    public boolean register(PacienteObraSocial pacienteObraSocial) {
        boolean register = false;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO PacienteObraSocial(id_ObraSocial, id_Paciente) VALUES (?,?)";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, pacienteObraSocial.getIdObraSocial());
            pstm.setInt(2, pacienteObraSocial.getIdPaciente());
            pstm.executeUpdate();
            register = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase PacienteObraSocialDAOImpl, metodo register "+e.getMessage());
        }
        return register;
    }

    @Override
    public List<PacienteObraSocial> obtain() {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM PacienteObraSocial ORDER BY id_obrasocial";
        List<PacienteObraSocial> pacienteObraSocialList = new ArrayList<PacienteObraSocial>();
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            while(rs.next()){
                PacienteObraSocial p = new PacienteObraSocial();
                p.setIdObraSocial(rs.getInt(1));
                p.setIdPaciente(rs.getInt(2));
                pacienteObraSocialList.add(p);
            }
            pstm.close();
            rs.close();
            con.close();
        }catch (SQLException e){
            System.out.println("Error: Clase PacienteObraSocialDAOImpl, metodo obtain");
            e.printStackTrace();
        }
        return pacienteObraSocialList;
    }

    @Override
    public boolean delete(PacienteObraSocial pacienteObraSocial) {
        boolean delete = false;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM PacienteObraSocial WHERE id_ObraSocial = ? AND id_Paciente = ?";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, pacienteObraSocial.getIdObraSocial());
            pstm.setInt(2, pacienteObraSocial.getIdPaciente());
            pstm.executeUpdate();
            delete = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase PacienteObraSocialDAOImpl, metodo delete. " +e.getMessage());
        }
        return delete;
    }

    @Override
    public boolean modify(PacienteObraSocial pacienteObraSocial, PacienteObraSocial aux) {
        boolean modify = false;
        PreparedStatement pstm = null;
        String sql = "UPDATE PacienteObraSocial SET id_ObraSocial = ?, id_Paciente = ? WHERE id_ObraSocial = ? AND id_Paciente = ?";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, aux.getIdObraSocial());
            pstm.setInt(2, aux.getIdPaciente());
            pstm.setInt(3, pacienteObraSocial.getIdObraSocial());
            pstm.setInt(4, pacienteObraSocial.getIdPaciente());
            pstm.executeUpdate();
            modify = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase PacienteObraSocialDAOImpl, metodo modify. " +e.getMessage());
        }
        return modify;
    }
}
