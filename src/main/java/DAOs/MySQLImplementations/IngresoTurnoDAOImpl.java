package DAOs.MySQLImplementations;

import DAOs.Interfaces.IIngresoTurnoDAO;
import Objetos.FichaClinica;
import Objetos.IngresoTurno;
import Utils.DBUtils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngresoTurnoDAOImpl implements IIngresoTurnoDAO {
    private IngresoTurnoDAOImpl ingresoTurnoDAO;
    public IngresoTurnoDAOImpl getInstance(){
        if(ingresoTurnoDAO == null){
            ingresoTurnoDAO = new IngresoTurnoDAOImpl();
        }
        return ingresoTurnoDAO;
    }
    private IngresoTurnoDAOImpl(){
    }
    DBConnector DBConnection ;
    Connection con = null;
    @Override
    public boolean register(IngresoTurno ingresoTurno) {
        boolean register = false;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO IngresoTurno(id_turno, id_ingreso) VALUES (?,?)";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, ingresoTurno.getIdTurno());
            pstm.setInt(2, ingresoTurno.getIdIngreso());
            pstm.execute(sql);
            register = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase IngresoTurnoDAOImpl, metodo register, "+e.getMessage());
        }
        return register;
    }

    @Override
    public List<IngresoTurno> obtain() {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM IngresoTurno ORDER BY id_ingreso";
        List<IngresoTurno> ingresoTurnoList = new ArrayList<IngresoTurno>();
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            while(rs.next()){
                IngresoTurno it = new IngresoTurno();
                it.setIdIngreso(rs.getInt(1));
                it.setIdTurno(rs.getInt(2));
                ingresoTurnoList.add(it);
            }
            pstm.close();
            rs.close();
            con.close();
        }catch (SQLException e){
            System.out.println("Error: Clase IngresoTurnoDAOImpl, metodo obtain");
            e.printStackTrace();
        }
        return ingresoTurnoList;
    }

    @Override
    public boolean delete(IngresoTurno ingresoTurno) {
        boolean delete = false;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM IngresoTurno WHERE id_ingreso = ? AND id_turno = ?";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, ingresoTurno.getIdIngreso());
            pstm.setInt(2, ingresoTurno.getIdTurno());
            pstm.execute(sql);
            delete = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase IngresoTurnoDAOImpl, metodo delete. " +e.getMessage());
        }
        return delete;
    }

    @Override
    public boolean modify(IngresoTurno ingresoTurno, IngresoTurno aux) {
        boolean modify = false;
        PreparedStatement pstm = null;
        String sql = "UPDATE IngresoTurno SET id_turno = ?, id_ingreso = ? WHERE id_turno = ? AND id_ingreso = ?";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, aux.getIdTurno());
            pstm.setInt(2, aux.getIdIngreso());
            pstm.setInt(3, ingresoTurno.getIdTurno());
            pstm.setInt(4, ingresoTurno.getIdIngreso());
            pstm.execute(sql);
            modify = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase IngresoTurnoDAOImpl, metodo modify. " +e.getMessage());
        }
        return modify;
    }
}
