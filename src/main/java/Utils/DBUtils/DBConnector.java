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
    
    private static final String user = "root";
    private static final String pass = "Goku2010.";
    private java.sql.Statement s;
    private ResultSet result = null;
    private Connection connection = null;
    private static DBConnector instance;
    private /*static*/ Statement query = null;
    private /*static*/ PreparedStatement p_query = null;
    
    

    public void startConnection()throws SQLException {
        try{
            
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tpmids1", user, pass);
        
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
      
     public void setResult(ResultSet result) {
        this.result= result;
    }
     public ResultSet getResult() {
        return result;
    } 
     
     public  Statement getQuery() {
        return query;
    }

    public  void setQuery(Statement query) {
        this.query = query;
    }
    
    public  PreparedStatement getP_query() {
        return p_query;
    }

    public  void setP_query(PreparedStatement p_query) {
        this.p_query = p_query;
    }
}
