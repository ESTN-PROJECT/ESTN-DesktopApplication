/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Guide Info
 */
public class MyDB {
    public static MyDB instance;
    public final String URL = "jdbc:mysql://127.0.0.1:3306/estn";
    public final String USERNAME = "root";
    public final String PASSWORD = "";
    public Connection cnx;

    //cnx mysql
    private MyDB() {
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("connected");
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            System.out.println("ERROOOOOOOOOOR");
        }
    }

    public static MyDB getInstance() {
        if (instance == null) {
            instance = new MyDB();
        }
        return instance;
    }

    public Connection getcnx() {

        return cnx;
    }
}
