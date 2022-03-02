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
 * @author HP
 */
public class MyDB {
    private static MyDB instance;
    private final String URL = "jdbc:mysql://127.0.0.1:3306/estn?zeroDateTimeBehavior=convertToNull";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private Connection cnx;

    //cnx mysql
    private MyDB() {
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
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
