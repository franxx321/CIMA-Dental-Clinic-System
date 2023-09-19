/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils.DBUtils;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public  class  DBConnector  {
    
    private static final String user = "root";
    private static final String pass = "123";
    private java.sql.Statement s;
    private ResultSet resultado;
    private Connection connection = null;
    private static DBConnector instance;
    
    

    public void startConnection()throws SQLException, ClassNotFoundException, UnknownHostException {
        try{
            
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306;database=bd_is;encrypt=true;trustServerCertificate=true;", user, pass);
        
        if (connection != null) {
                System.out.println("Conexion a base de datos:  ... Ok");
            }
        }
        catch(SQLException e){
            System.out.println("Fallo!" + e.getMessage());
        
    }
    }
    
    public void setConnection(Connection conn) {
        this.connection = conn;
    }
    
    public Connection getConnection() {
        return connection ;
    }
    
      public void endConnection() {
        try {
            connection.close();
            connection = null;
        } catch (Exception e) {
            System.out.println("Problema para cerrar la Conexión a la base de datos ");
        }
    }
    
      


        public static DBConnector getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBConnector();
        } 

        return instance;
        }
      
     
}
