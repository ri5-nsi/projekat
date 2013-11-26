/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amer
 */
public class dbHelper {
    
    private static dbHelper _instance = null;
    
    private String dbUrl;
    private String dbCatalog;
    private String username;
    private String password;
    
    private Connection con;
    private Statement st;
    
    private dbHelper(){
        loadConnectionParams();
        connectToDB();
    }
        
    public static ResultSet ExecuteQuery(String sqlQuery){
        try{
            if(_instance == null)
                _instance = new dbHelper();
            
            if( !_instance.st.isClosed() ){
                ResultSet rs = _instance.st.executeQuery(sqlQuery);
                return rs;
            }
        } catch(SQLException ex){
            //TOOD add logging logic here
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    private void loadConnectionParams(){
        try{
            // TODO ADD LOGIC THAT WILL GET THIS VALUES FROM CONFIG
            /*
            prop.load(new FileInputStream("config.properties"));
            dbUrl= prop.getProperty("databaseURL");
            username=prop.getProperty("databaseUsername");
            password=prop.getProperty("databasePassword");
            */
            username="almin";
            password="post12gres90";
            dbUrl="jdbc:postgresql://162.219.6.176:5432/nsi2013";
        } catch(Exception ex){
            //TODO add logging logic
            System.out.println(ex.getMessage());
        }
    }
    
    private boolean connectToDB(){
        try{
            
        	Class.forName("org.postgresql.Driver");//.newInstance();
            con = DriverManager.getConnection(dbUrl,username, password);
                        
            st = con.createStatement();
            st.setPoolable(true);
        } catch(Exception ex){
            //TODO add logging logic here
            return false;
        }
        return true;
    }
    
    private void closeConnection(){
        try {
            con.close();
        } catch (Exception ex) {
            //TODO add logging logic here
        }
    }
            
}

