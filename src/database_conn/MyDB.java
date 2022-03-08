/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_conn;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author AZIZ
 */
public class MyDB {
    final static String url="jdbc:mysql://127.0.0.1:3308/estn";
    final static String username = "root";
    final static String pwd ="";
       private Connection cnx;
   static MyDB instance = null;
    
    public MyDB(){
        try{
            cnx = (Connection) DriverManager.getConnection(url,username,pwd);
        System.out.println("connected");
        
    }catch (SQLException ex){
        ex.printStackTrace();
    }
    
}
    public Connection getCnx(){
        return cnx;
    }
    public static MyDB getInstance(){
        if(instance == null){
            instance = new MyDB();
        }
        return instance ;
        
    }
    
    
    
    }