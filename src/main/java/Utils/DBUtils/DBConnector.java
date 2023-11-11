/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils.DBUtils;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public  class  DBConnector  {
    
    private static final String user = "prueba";
    private static final String pass = "Holamundo1";
    private ResultSet result = null;
    private Connection connection = null;
    private static DBConnector instance;
    private /*static*/ Statement query = null;
    private /*static*/ PreparedStatement p_query = null;
    
    

    public void startConnection()throws SQLException {
        try{
            
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_is", user, pass);
        
        if (connection != null) {
                System.out.println("Conexion a base de datos:  ... Ok");
            }
        }
        catch(SQLException e){
            System.out.println("Fallo!" + e.getMessage());
        
    }
    }

    
    public Connection getConnection() {
        return connection ;
    }
    
      public void endConnection() {
        try {
            connection.close();
            connection = null;
        }catch (Exception e) {
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
