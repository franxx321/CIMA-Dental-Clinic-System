/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBConnector {
    
    public String user = "root";
    public String pass = "123";
    public java.sql.Statement s;
    public ResultSet resultado;
    public Connection conexion = null;

     public void Conectar() throws SQLException, ClassNotFoundException, UnknownHostException {

        try {
            

            String ip_local = Inet4Address.getLocalHost().getHostAddress().replaceAll("\\.\\d+$", "");
            DriverManager.setLoginTimeout(1);

            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306;database=bd_is;encrypt=true;trustServerCertificate=true;", user, pass);

            if (conexion != null) {
                System.out.println("Conexion a base de datos:  ... Ok");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        this.s = conexion.createStatement();

    }
     
      public void Desconectar() {
        try {
            conexion.close();
            conexion = null;
        } catch (Exception e) {
            System.out.println("Problema para cerrar la Conexión a la base de datos ");
        }
    }
}



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lautaro
 */

    