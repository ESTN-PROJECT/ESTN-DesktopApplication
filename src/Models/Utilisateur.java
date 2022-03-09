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

    Date date_naiss;

    String role;
    String email;

    public Utilisateur() {
    }

    public Utilisateur(String username, Date date_naiss, String ville, String password, String role, String email) {

        this.username = username;
        this.date_naiss = date_naiss;
        this.ville = ville;
        this.password = password;
        this.role = role;
        this.email = email;

    }

    public Utilisateur(String username, Date date_naiss, String ville, String role) {

        this.username = username;
        this.date_naiss = date_naiss;
        this.ville = ville;
        this.role = role;

    }

    public Utilisateur(String username, String ville, String password, Date date_naiss, String email) {
        this.username = username;
        this.ville = ville;
        this.password = password;
        this.date_naiss = date_naiss;
        this.email = email;
    }

    public Utilisateur(int id, String username, String ville, String password, Date date_naiss, String email) {
        this.id = id;
        this.username = username;
        this.ville = ville;
        this.password = password;
        this.date_naiss = date_naiss;
        this.email = email;
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
        return '{' + "username=" + username + ", ville=" + ville + ", date_naiss=" + date_naiss + ", role=" + role + ", email=" + email + '}';
    }

    public static Utilisateur getInstance() {
        if (instance == null) {
            instance = new Utilisateur();
        }
        return instance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
