package DAOs.MySQLImplementations;

import DAOs.Interfaces.ITurnosDAO;
import Objetos.Turno;
import Utils.DBUtils.DBConnector;

import java.sql.*;
import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TurnosDAOImpl implements ITurnosDAO {
    private static TurnosDAOImpl turnosDAO;

    public static TurnosDAOImpl getInstance(){
        if(turnosDAO==null){
            turnosDAO= new TurnosDAOImpl();
        }
        return turnosDAO;
    }

    private TurnosDAOImpl() {
    }

    DBConnector DBConnection ;
    Connection con = null;
    @Override
    public boolean register(Turno turno) {
        boolean register = false;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO Turnos(horaInicio, horaFin,asistio,id_paciente,id_Profesional,valor,descuento) VALUES (?,?,?,?,?,?,?)";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setTimestamp(1, new Timestamp(turno.getHoraInicio().getTime()));
            pstm.setTimestamp(2, new Timestamp(turno.getHoraFin().getTime()));
            pstm.setBoolean(3, turno.isAsistio());
            pstm.setInt(4, turno.getIdPaciente());
            pstm.setInt(5, turno.getIdProfesional());
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
        String sql = "SELECT * FROM Turnos ORDER BY id";
        List<Turno> turnoList = new ArrayList<Turno>();
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            while(rs.next()){
                Turno t = new Turno();
                t.setId(rs.getInt(1));
                t.setHoraInicio(new Date(rs.getTimestamp(2).getTime()));
                t.setHoraFin(new Date(rs.getTimestamp(3).getTime()));
                t.setAsistio(rs.getBoolean(4));
                t.setIdPaciente(rs.getInt(5));
                t.setIdProfesional(rs.getInt(6));
                t.setValor(rs.getFloat(7));
                t.setDescuento(rs.getFloat(8));
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
        String sql = "DELETE FROM Turnos WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, turno.getId());
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
        String sql = "UPDATE Turnos SET id = ?, horainicio = ?, horafin = ?, asistio = ?, id_paciente = ?, id_profesional = ?, valor = ?, descuento = ? WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, aux.getId());
            pstm.setTimestamp(2, new Timestamp(aux.getHoraInicio().getTime()) );
            pstm.setTimestamp(3, new Timestamp(aux.getHoraFin().getTime()) );
            pstm.setBoolean(4, aux.isAsistio());
            pstm.setInt(5, aux.getIdPaciente());
            pstm.setInt(6, aux.getIdProfesional());
            pstm.setFloat(7, aux.getValor());
            pstm.setFloat(8, aux.getDescuento());
            pstm.setInt(9, turno.getId());
            pstm.execute(sql);
            modify = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase TurnoDAOImpl, metodo modify. " +e.getMessage());
        }
        return modify;
    }

    @Override
    public List<Turno> profesionalFutureApointments(String idprofesional, int week) {
        Date date = new Date();
        date.setTime(date.getTime()/86400000);
        return null;
    }
}
