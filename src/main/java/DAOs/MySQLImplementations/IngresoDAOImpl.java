package DAOs.MySQLImplementations;

import DAOs.Interfaces.IIngresoDAO;
import Objetos.Ingreso;
import Utils.DBUtils.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngresoDAOImpl implements IIngresoDAO {
    private static IngresoDAOImpl ingresoDAO;
    public static IngresoDAOImpl getInstance(){
        if(ingresoDAO == null){
            ingresoDAO = new IngresoDAOImpl();
        }
        return ingresoDAO;
    }
    private IngresoDAOImpl(){
    }

    DBConnector DBConnection ;
    Connection con = null;
    @Override
    public boolean register(Ingreso ingreso) {
        boolean register = false;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO Ingresos(fecha, monto, descripcion, id_paciente, id_profesional, id_obrasocial,id_turno) VALUES (?,?,?,?,?,?,?)";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setDate(1, ingreso.getFecha());
            pstm.setFloat(2, ingreso.getMonto());
            pstm.setString(3, ingreso.getDescripcion());
            pstm.setInt(4, ingreso.getIdPaciente());
            pstm.setInt(5, ingreso.getIdProfesional());
            if(ingreso.getIdObraSocial() !=-1){
                pstm.setInt(6, ingreso.getIdObraSocial());
            }
            else{
                pstm.setNull(6,Types.INTEGER);
            }
            pstm.setInt(7,ingreso.getIdTurno());
            pstm.executeUpdate();
            register = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase IngresoDAOImpl, metodo register "+e.getMessage());
        }
        return register;
    }

    @Override
    public List<Ingreso> obtain() {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Ingresos ORDER BY id";
        List<Ingreso> ingresoList = new ArrayList<Ingreso>();
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            while(rs.next()){
                Ingreso i = new Ingreso();
                //i.setId(rs.getInt(1));
                i.setFecha(rs.getDate(2));
                i.setMonto(rs.getFloat(3));
                i.setDescripcion(rs.getString(4));
                i.setIdPaciente(rs.getInt(5));
                i.setIdProfesional(rs.getInt(6));
                i.setIdObraSocial(rs.getInt(7));
                ingresoList.add(i);
            }
            pstm.close();
            rs.close();
            con.close();
        }catch (SQLException e){
            System.out.println("Error: Clase IngresoDAOImpl, metodo obtain");
            e.printStackTrace();
        }
        return ingresoList;
    }

    @Override
    public boolean delete(Ingreso ingreso) {
        boolean delete = false;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM Ingresos WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, ingreso.getId());
            pstm.executeUpdate();
            delete = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase IngresoDAOImpl, metodo delete. " +e.getMessage());
        }
        return delete;
    }

    @Override
    public boolean modify(Ingreso ingreso, Ingreso aux) {
        boolean modify = false;
        PreparedStatement pstm = null;
        String sql = "UPDATE Ingresos SET id = ?, fecha = ?, monto = ?, descripcion = ?, id_paciente = ?, id_profesional = ?, id_obrasocial = ? WHERE id = ?";
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            //pstm.setInt(1, aux.getId());
            pstm.setDate(2, aux.getFecha());
            pstm.setFloat(3, aux.getMonto());
            pstm.setString(4, aux.getDescripcion());
            pstm.setInt(5, aux.getIdPaciente());
            pstm.setInt(6, aux.getIdProfesional());
            pstm.setInt(7, aux.getIdObraSocial());
            pstm.setInt(8, ingreso.getId());
            pstm.executeUpdate();
            modify = true;
            pstm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: Clase IngresoDAOImpl, metodo modify. " +e.getMessage());
        }
        return modify;
    }

    @Override
    public List<Ingreso> getIngresoByProfesional(Ingreso ingreso){
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Ingresos WHERE id_profesional = ?";
        List<Ingreso> ingresoByProfesionalList = new ArrayList<>();
        try{
            DBConnection = DBConnector.getInstance();
            DBConnection.startConnection();
            con = DBConnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, ingreso.getIdProfesional());
            rs = pstm.executeQuery();
            while (rs.next()){
                Ingreso i = new Ingreso();
                i.setId(rs.getInt(1));
                i.setFecha(rs.getDate(2));
                i.setMonto(rs.getFloat(3));
                i.setDescripcion(rs.getString(4));
                i.setIdPaciente(rs.getInt(5));
                i.setIdProfesional(rs.getInt(6));
                i.setIdObraSocial(rs.getInt(7));
                ingresoByProfesionalList.add(i);
            }
            pstm.close();
            con.close();
        }catch (SQLException e){
            System.out.println("Error: Clase IngresoDAOImpl. metodo getIngresoByProfesional. "+ e.getMessage());
        }
        return ingresoByProfesionalList;
    }

}
