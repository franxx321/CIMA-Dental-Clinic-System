package DAOs.MySQLImplementations;

import DAOs.Interfaces.ITurnosDAO;
import Objetos.Turno;
import Utils.DBUtils.DBConnector;
import Utils.FormatedDate;

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
    private DBConnector DBConnection ;
    private Connection con = null;
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
        String sql = "UPDATE Turnos SET  horainicio = ?, horafin = ?, asistio = ?, valor = ?, descuento = ? WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setTimestamp(1, new Timestamp(aux.getHoraInicio().getTime()) );
            pstm.setTimestamp(2, new Timestamp(aux.getHoraFin().getTime()) );
            pstm.setBoolean(3, aux.isAsistio());
            pstm.setFloat(4, aux.getValor());
            pstm.setFloat(5, aux.getDescuento());
            pstm.setInt(6, turno.getId());
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
    public List<Turno> profesionalFutureApointments(Turno turno) {
        List<Turno> turnosList = null;

        try{
            DBConnection=DBConnector.getInstance();
            DBConnection.startConnection();
            con=DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement("SELECT * " +
                    "FROM turnos " +
                    "where id_profesional = ? " +
                    "and horaInicio > ? " +
                    "and horaInicio < ?");
            ptsm.setInt(1,turno.getIdProfesional());
            ptsm.setTimestamp(2,new Timestamp(turno.getHoraInicio().getTime()));
            ptsm.setTimestamp(3,new Timestamp(turno.getHoraFin().getTime()));
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
    public List <Turno> getByIdProfesional(Turno turno){
        List <Turno> turnoList = null;
        try{
            DBConnection=DBConnector.getInstance();
            DBConnection.startConnection();
            con=DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement("SELECT * FROM Turnos Where id_profesional = ?");
            ptsm.setInt(1,turno.getIdProfesional());
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
    public List<Turno> getOverlappingturnos (Turno turno){
        List<Turno> turnoList= null;
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement("SELECT * " +
                    "FROM turnos " +
                        "where id_profesional = ? AND(" +
                        " (horaInicio <= ? AND horaInicio >= ?) OR " +
                        "(horaInicio <= ? AND horaFin >= ?) OR " +
                        " (horaInicio >= ? AND horaInicio <= ?)) ");
            ptsm.setInt(1,turno.getIdProfesional());
            ptsm.setTimestamp(2,new Timestamp(turno.getHoraInicio().getTime()));
            ptsm.setTimestamp(3,new Timestamp(turno.getHoraFin().getTime()));
            ptsm.setTimestamp(4,new Timestamp(turno.getHoraInicio().getTime()));
            ptsm.setTimestamp(5,new Timestamp(turno.getHoraFin().getTime()));
            ptsm.setTimestamp(6,new Timestamp(turno.getHoraInicio().getTime()));
            ptsm.setTimestamp(7,new Timestamp(turno.getHoraFin().getTime()));
            ResultSet rs = ptsm.executeQuery();

            turnoList= new ArrayList<>();
            if(!rs.next()){
                Turno turno1 = new Turno();
                turno1.setId(-1);
                turnoList.add(turno1);
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
    @Override
    public Turno getByDateProfesional(Turno turno){
        Turno turno1 = null;
        try {
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement("SELECT * " +
                    "FROM turnos " +
                    "where horaInicio =? and horaFin = ? and id_profesional = ?");
            ptsm.setTimestamp(1,new Timestamp(turno.getHoraInicio().getTime()));
            ptsm.setTimestamp(2,new Timestamp(turno.getHoraFin().getTime()));
            ptsm.setInt(3,turno.getIdProfesional());
            ResultSet rs = ptsm.executeQuery();
            turno= new Turno();
            if(!rs.next()){
                turno.setId(-1);
            }
            else{
                turno.setId(rs.getInt(1));
                turno.setHoraInicio(new Date(rs.getTimestamp(2).getTime()));
                turno.setHoraFin(new Date(rs.getTimestamp(3).getTime()));
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
    public List<Turno> getPatientFutureApointments(Turno turno) {
        List<Turno> turnoList = null;
        Date today = FormatedDate.formatedDate(new Date());
        try {
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement("SELECT * " +
                    "FROM turnos " +
                    "WHERE horaInicio > ?" +
                    "and id_paciente =?");
            ptsm.setTimestamp(1, new Timestamp(today.getTime()-1209600000));
            ptsm.setInt(2,turno.getIdPaciente());
            ResultSet rs = ptsm.executeQuery();
            if (rs.next()){
                turnoList= new ArrayList<>();
                do {
                    Turno turno1 = new Turno();
                    turno1.setId(rs.getInt(1));
                    turno1.setHoraInicio(new Date(rs.getTimestamp(2).getTime()));
                    turno1.setHoraFin(new Date(rs.getTimestamp(3).getTime()));
                    turno1.setAsistio(rs.getBoolean(4));
                    turno1.setIdPaciente(rs.getInt(5));
                    turno1.setIdProfesional(rs.getInt(6));
                    turno1.setValor(rs.getFloat(7));
                    turno1.setDescuento(rs.getFloat(8));
                    turnoList.add(turno1);
                } while (rs.next());
            }
            con.close();
        }
        catch (SQLException e){
            System.out.println("Error en get Patient Future Appointments Turno DAO IMPL"+e.getMessage());
        }

        return turnoList;
    }

    @Override
    public Turno getById(Turno t) {
        Turno turno = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement("SELECT  *" +
                    "FROM  turnos " +
                    "WHERE id = ?");
            pstm.setInt(1,t.getId());
            rs = pstm.executeQuery();
            turno = new Turno();
            if(rs.next()){
                turno.setId(rs.getInt(1));
                turno.setHoraInicio(new Date(rs.getTimestamp(2).getTime()));
                turno.setHoraFin(new Date(rs.getTimestamp(3).getTime()));
                turno.setAsistio(rs.getBoolean(4));
                turno.setIdPaciente(rs.getInt(5));
                turno.setIdProfesional(rs.getInt(6));
                turno.setValor(rs.getFloat(7));
                turno.setDescuento(rs.getFloat(8));
            }
            else{
                turno.setId(-1);
            }

        }
        catch (SQLException e){
            System.out.println("Fallo TurnoDAOImpl, getById "+ e.getMessage());
        }


        return turno;
    }




}
