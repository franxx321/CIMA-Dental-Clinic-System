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
            pstm.setFloat(6, turno.getValor());
            pstm.setFloat(7, turno.getDescuento());
            pstm.executeUpdate();
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
            pstm.executeUpdate();
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
        String sql = "UPDATE Turnos SET  horainicio = ?, horafin = ?, asistio = ?, id_paciente = ?, id_profesional = ?, valor = ?, descuento = ? WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setTimestamp(1, new Timestamp(aux.getHoraInicio().getTime()) );
            pstm.setTimestamp(2, new Timestamp(aux.getHoraFin().getTime()) );
            pstm.setBoolean(3, aux.isAsistio());
            pstm.setInt(4, aux.getIdPaciente());
            pstm.setInt(5, aux.getIdProfesional());
            pstm.setFloat(6, aux.getValor());
            pstm.setFloat(7, aux.getDescuento());
            pstm.setInt(8, turno.getId());
            pstm.executeUpdate();
            modify = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase TurnoDAOImpl, metodo modify. " +e.getMessage());
        }
        return modify;
    }

    @Override
    public List<Turno> profesionalFutureApointments(int idprofesional, int week) {
        List<Turno> turnosList = null;
        Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        try{
            DBConnection=DBConnector.getInstance();
            DBConnection.startConnection();
            con=DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement("SELECT * " +
                    "FROM turnos " +
                    "where id_profesional = ? " +
                    "and horaInicio > ? " +
                    "and horaInicio < ?");
            ptsm.setInt(1,idprofesional);
            ptsm.setTimestamp(2,new Timestamp(date.getTime()+(long)week*604800000));
            ptsm.setTimestamp(3,new Timestamp(date.getTime()+(long)(week+1)*604800000));
            ResultSet rs = ptsm.executeQuery();
            if(rs.next()){
                turnosList= new ArrayList<>();
                do {
                    Turno t = new Turno();
                    t.setId(rs.getInt(1));
                    t.setHoraInicio(new Date(rs.getTimestamp(2).getTime()));
                    t.setHoraFin(new Date(rs.getTimestamp(3).getTime()));
                    t.setAsistio(rs.getBoolean(4));
                    t.setIdPaciente(rs.getInt(5));
                    t.setIdProfesional(rs.getInt(6));
                    t.setValor(rs.getFloat(7));
                    t.setDescuento(rs.getFloat(8));
                    turnosList.add(t);
                }while (rs.next());
            }
        }
        catch (SQLException e){
            System.out.println("Fallo profesional future apointments en Turno Dao IMPL"+e.getMessage());
        }
        return turnosList;
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
            con.close();
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
            ptsm.setTimestamp(2,new Timestamp(horaInicio.getTime()));
            ptsm.setTimestamp(3,new Timestamp( horaFin.getTime()));
            ptsm.setTimestamp(4,new Timestamp(horaInicio.getTime()));
            ptsm.setTimestamp(5,new Timestamp(horaFin.getTime()));
            ptsm.setTimestamp(6,new Timestamp(horaInicio.getTime()));
            ptsm.setTimestamp(7,new Timestamp(horaFin.getTime()));
            ResultSet rs = ptsm.executeQuery();

            turnoList= new ArrayList<>();
            if(!rs.next()){
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
            con.close();
        }
        catch (SQLException e){
            System.out.println("Fallo Turnos DAO Impl get Overlapping turnos"+e.getMessage());
        }
        return turnoList;
    }

    public Turno getByDateProfesional(Date horaInicio, Date horaFin, int idProfesional){
        Turno turno = null;
        try {
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement("SELECT * " +
                    "FROM turnos " +
                    "where horaInicio =? and horaFin = ? and id_profesional = ?");
            ptsm.setTimestamp(1,new Timestamp(horaInicio.getTime()));
            ptsm.setTimestamp(2,new Timestamp(horaFin.getTime()));
            ptsm.setInt(3,idProfesional);
            ResultSet rs = ptsm.executeQuery();
            turno= new Turno();
            if(!rs.next()){
                turno.setId(-1);
            }
            else{
                turno.setId(rs.getInt(1));
                turno.setHoraInicio(new Date(rs.getTimestamp(2).getTime()));
                turno.setHoraInicio(new Date(rs.getTimestamp(3).getTime()));
                turno.setAsistio(rs.getBoolean(4));
                turno.setIdPaciente(rs.getInt(5));
                turno.setIdProfesional(rs.getInt(6));
                turno.setValor(rs.getFloat(7));
                turno.setDescuento(rs.getFloat(8));
            }
            con.close();
        }

        catch (SQLException e){
            System.out.println("fallo Turnos DAO Impl get by date profesional"+ e.getMessage());
        }
        return turno;

    }

    @Override
    public List<Turno> getPatientFutureApointments(int idPaciente) {
        List<Turno> turnoList = null;
        Date today = new Date();
        today.setHours(0);
        today.setMinutes(0);
        today.setSeconds(0);
        try {
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement("SELECT * " +
                    "FROM turnos " +
                    "WHERE horaInicio > ?" +
                    "and id_paciente =?" +
                    "ORDER BY horaInicio");
            ptsm.setTimestamp(1, new Timestamp(today.getTime()));
            ptsm.setInt(2,idPaciente);
            ResultSet rs = ptsm.executeQuery();
            if (rs.next()){
                turnoList= new ArrayList<>();
                do {
                    Turno turno = new Turno();
                    turno.setId(rs.getInt(1));
                    turno.setHoraInicio(new Date(rs.getTimestamp(2).getTime()));
                    turno.setHoraInicio(new Date(rs.getTimestamp(3).getTime()));
                    turno.setAsistio(rs.getBoolean(4));
                    turno.setIdPaciente(rs.getInt(5));
                    turno.setIdProfesional(rs.getInt(6));
                    turno.setValor(rs.getFloat(7));
                    turno.setDescuento(rs.getFloat(8));
                    turnoList.add(turno);
                } while (rs.next());
            }
            con.close();
        }
        catch (SQLException e){
            System.out.println("Error en get Patient Future Appointments Turno DAO IMPL"+e.getMessage());
        }

        return null;
    }


}
