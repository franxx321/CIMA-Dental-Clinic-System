package DAOs.MySQLImplementations;

import DAOs.Interfaces.ITurnosDAO;
import Objetos.FichaClinica;
import Objetos.Turno;
import Utils.DBUtils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurnosDAOImpl implements ITurnosDAO {
    DBConnector DBConnection ;
    Connection con = null;
    @Override
    public boolean register(Turno turno) {
        boolean register = false;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO Turno VALUES (?,?,?,?,?,?,?)";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            //pstm.setInt(1, turno.getId());
            pstm.setDate(2, turno.getFecha());
            pstm.setBoolean(3, turno.isAsistio());
            //pstm.setInt(4, turno.getId_Paciente());
            //pstm.setInt(5, turno.getId_Profesional());
            pstm.setFloat(4, turno.getValor());
            pstm.setFloat(5, turno.getDescuento());
            pstm.execute(sql);
            register = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase TurnoDAOImpl, metodo register "+e.getMessage());
        }
        return register;
    }

    @Override
    public List<Turno> obtain(Turno turno) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Turno ORDER BY id";
        List<Turno> turnoList = new ArrayList<Turno>();
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            while(rs.next()){
                Turno t = new Turno();
                //t.setId(rs.getInt(1));
                t.setFecha(rs.getDate(2));
                t.setAsistio(rs.getBoolean(3));
                //t.setInt(rs.getInt(4));
                //t.setInt(rs.getInt(5));
                t.setValor(rs.getFloat(6));
                t.setDescuento(rs.getFloat(7));
                turnoList.add(t);
            }
            pstm.close();
            rs.close();
            con.close();
        }catch (SQLException e){
            System.out.println("Error: Clase TurnoDAOImpl, metodo obtain");
            e.printStackTrace();
        }
        return turnoList;
    }

    @Override
    public boolean delete(Turno turno) {
        boolean delete = false;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM Turno WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            //pstm.setInt(1, turno.getId());
            pstm.execute(sql);
            delete = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase TurnoDAOImpl, metodo delete. " +e.getMessage());
        }
        return delete;
    }

    @Override
    public boolean modify(Turno turno, Turno aux) {
        boolean modify = false;
        PreparedStatement pstm = null;
        String sql = "UPDATE Turno SET id = ?, fecha = ?, asistio = ?, id_paciente = ?, id_profesional = ?, valor = ?, descuento = ? WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            //pstm.setInt(1, aux.getId());
            pstm.setDate(2, aux.getFecha());
            pstm.setBoolean(3, aux.isAsistio());
            //pstm.setInt(4, aux.getId_Paciente());
            //pstm.setInt(5, aux.getId_Profesional());
            pstm.setFloat(6, aux.getValor());
            pstm.setFloat(7, aux.getDescuento());
            //pstm.setInt(8, turno.getId());
            pstm.execute(sql);
            modify = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase TurnoDAOImpl, metodo modify. " +e.getMessage());
        }
        return modify;
    }
}
