/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DALI CHARFEDDINE
 */
public class MyDB {
   final static String URL="jdbc:mysql://127.0.0.1:3306/estn";
   final static String USERNAME="root";
   final static String PWD="";
   
   private Connection cnx ;   
   static MyDB instance=null;

    public MyDB() {
        try {
          cnx=(Connection) DriverManager.getConnection(URL,USERNAME,PWD);
        System.out.println("connecté");   
        }catch (SQLException ex){
            ex.printStackTrace();
        }
       
    }

    public Connection getCnx() {
        return cnx;
    }

    public static MyDB getInstance() {
        if (instance==null){
            instance=new MyDB();
        }
        return instance;
    }

    
}
