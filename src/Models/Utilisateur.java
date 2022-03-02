/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * singleton
 *
 * @author HP
 */
public class Utilisateur {

    private static Utilisateur instance;

    int id;
    String username, ville, password;
  

    Date date_naiss ;

    String role;

    public Utilisateur() {
    }

    public Utilisateur(String username, Date date_naiss, String ville, String password, String role) {

        this.username = username;
        this.date_naiss = date_naiss;
        this.ville = ville;
        this.password = password;
        this.role = role;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

   

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDate_naiss() {
        return date_naiss;
    }

    public void setDate_naiss(Date date_naiss) {
        this.date_naiss = date_naiss;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", username=" + username + ", ville=" + ville + ", password=" + password + ", date_naiss=" + date_naiss + ", role=" + role + '}';
    }

   

 

    public static Utilisateur getInstance() {
        if (instance == null) {
            instance = new Utilisateur();
        }
        return instance;
    }
}
