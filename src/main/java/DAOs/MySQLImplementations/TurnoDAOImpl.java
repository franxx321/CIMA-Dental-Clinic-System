package DAOs.MySQLImplementations;

import DAOs.Interfaces.ITurnosDAO;
import Objetos.Turno;
import Utils.DBUtils.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TurnoDAOImpl implements ITurnosDAO {
    private static TurnoDAOImpl turnosDAO;
    public static TurnoDAOImpl getInstance(){
        if(turnosDAO==null){
            turnosDAO= new TurnoDAOImpl();
        }
        return turnosDAO;
    }
    private TurnoDAOImpl() {
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
            DBConnection.startConnection();
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
    public List<Turno> obtain() {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Turnos ORDER BY id";
        List<Turno> turnoList = new ArrayList<Turno>();
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
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
            DBConnection.startConnection();
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
            DBConnection.startConnection();
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

    @Override
    public List <Turno> getByIdProfesional(int idProfesional){
        List <Turno> turnoList = null;
        try{
            DBConnection=DBConnector.getInstance();
            DBConnection.startConnection();
            con=DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement("SELECT * FROM Turnos Where id_profesional = ?");
            ptsm.setInt(1,idProfesional);
            ResultSet rs = ptsm.executeQuery();
            turnoList= new ArrayList<>();
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


        }
        catch (SQLException e){
            System.out.println("Fallo Turno DAO IMPL get by id profesional"+e.getMessage());
        }
        return turnoList;
    }

    @Override
    public List<Turno> getOverlappingturnos (int idProfesional, Date horaInicio, Date horaFin){
        List<Turno> turnoList= null;
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement("SELECT * " +
                    "FROM turnos " +
                        "where id_profesional = ? AND" +
                        " (horaInicio < ? AND horaInicio > ?) OR " +
                        "(horaInicio < ? AND horaFin > ?) OR " +
                        " (horaInicio > ? AND horaInicio < ?) ");
            ptsm.setInt(1,idProfesional);
            ptsm.setTimestamp(1,new Timestamp(horaInicio.getTime()));
            ptsm.setTimestamp(2,new Timestamp( horaFin.getTime()));
            ptsm.setTimestamp(3,new Timestamp(horaInicio.getTime()));
            ptsm.setTimestamp(4,new Timestamp(horaFin.getTime()));
            ptsm.setTimestamp(5,new Timestamp(horaInicio.getTime()));
            ptsm.setTimestamp(6,new Timestamp(horaFin.getTime()));
            ResultSet rs = ptsm.executeQuery();
            rs.next();
            turnoList= new ArrayList<>();
            if(rs.getInt(1)==0){
                Turno turno = new Turno();
                turno.setId(-1);
                turnoList.add(turno);
            }
            else {
                do{
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
                } while (rs.next());
            }
        }
        catch (SQLException e){
            System.out.println("Fallo Turnos DAO Impl get Overlapping turnos"+e.getMessage());
        }
        return turnoList;
    }



}
