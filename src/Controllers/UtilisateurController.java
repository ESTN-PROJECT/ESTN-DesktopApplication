/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Database.MyDB;
import Models.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Interfaces.IServices;
import Models.Coach;
import Models.Member;
import java.sql.Date;
import java.util.Comparator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author HP
 */
public class UtilisateurController implements IServices {

    private Connection cnx;

    public UtilisateurController() {
        cnx = MyDB.getInstance().getcnx();
    }

    @Override
    public void SignUpMember(Member u) {
        String query = "INSERT INTO `utilisateur`(`username`, `date_naiss`, `ville`, `password`, `rank`, `description`, `cout`, `role`) VALUES ('" + u.getUsername() + "','" + u.getDate_naiss() + "','" + u.getVille() + "','" + u.getPassword() + "','" + u.getRank() + "','" + "0" + "','" + "0" + "','" + "member" + "')";

        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void SignUpCoach(Coach u) {
        String query = "INSERT INTO `utilisateur`(`username`, `date_naiss`, `ville`, `password`, `rank`, `description`, `cout`, `role`) VALUES ('" + u.getUsername() + "','" + u.getDate_naiss() + "','" + u.getVille() + "','" + u.getPassword() + "','" + u.getRank() + "','" + u.getDescription() + "','" + u.getCout() + "','" + "coach" + "')";

        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Utilisateur> afficher() {
        List<Utilisateur> utilisateurs = new ArrayList();

        try {
            Statement stm = cnx.createStatement();

            String querry = "SELECT * from utilisateur  WHERE `IsDeleted`=" + 0;
            ResultSet rs = stm.executeQuery(querry);

            while (rs.next()) {
                Utilisateur p = new Utilisateur();
                p.setId(rs.getInt(1));
                p.setUsername(rs.getString(2));
                p.setDate_naiss(rs.getDate(3));
                p.setVille(rs.getString(4));
                p.setPassword(rs.getString(5));
                p.setRole(rs.getString(9));

                utilisateurs.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return utilisateurs;
    }

    @Override
    public void DeleteUser(int t) {
        PreparedStatement ps;

        String query = "UPDATE `utilisateur` SET `IsDeleted`=1 WHERE `ID`= " + t;

        try {
            ps = cnx.prepareStatement(query);

            ps.execute();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    @Override
    public void UpdateMember(Member u, int id) {
        PreparedStatement ps;
        String query = "UPDATE `utilisateur` SET `username`=?,`date_naiss`=?,`ville`=?,`password`=?,`rank`=?,`description`=0,`cout`=0,`role`=? WHERE `ID`= " + id;
        try {

            ps = cnx.prepareStatement(query);

            ps.setString(1, u.getUsername());
            ps.setDate(2, (Date) u.getDate_naiss());
            ps.setString(3, u.getVille());
            ps.setString(4, u.getPassword());
            ps.setString(5, u.getRank());

            ps.setString(6, u.getRole());

            ps.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void UpdateCoach(Coach u, int id) {
        PreparedStatement ps;
        String query = "UPDATE `utilisateur` SET `username`=?,`date_naiss`=?,`ville`=?,`password`=?,`rank`=?,`description`=?,`cout`=?,`role`=? WHERE `ID`= " + id;
        try {
//        String query = "UPDATE `utilisateur` SET `username`='" + u.getUsername() + "',`date_naiss`='" + u.getDate_naiss() + "',`ville`='" + u.getVille() + "',`code_postal`='" + u.getCode_postal() + "',`rank`='" + u.getRank() + "',`description`='" + u.getDescription() + "',`cout`='" + u.getCout() + "',`role`='" + u.getRole() + "' WHERE `ID`=?";

            ps = cnx.prepareStatement(query);

            ps.setString(1, u.getUsername());
            ps.setDate(2, (Date) u.getDate_naiss());
            ps.setString(3, u.getVille());
            ps.setString(4, u.getPassword());
            ps.setString(5, u.getRank());
            ps.setString(6, u.getDescription());
            ps.setString(7, u.getCout());

            ps.setString(8, u.getRole());

            ps.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void FindUser(int id) {
        PreparedStatement ps;
        String query = "SELECT * FROM `utilisateur` WHERE `id`=? ";

        try {
            Utilisateur p = new Utilisateur();

            ps = cnx.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("User Found");

            } else {
                System.out.println("User Not Found");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //features(search, filter,..) -> api stream

    public boolean Login(String username, String password) {

        PreparedStatement ps;
        String query = "select * from `utilisateur` where `username`= '" + username + "' and `password` = '" + password + "' ";

        try {
            ps = cnx.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("connected");
                return true;
            } else {
                System.out.println("You should try again!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public List<Utilisateur> RechercheById(int id) {
        return afficher().stream().filter(a -> a.getId() == id).collect(Collectors.toList());

    }

    public List<Utilisateur> RechercheByUsername(String username) {

        return afficher().stream().filter(a -> a.getUsername().equals(username)).collect(Collectors.toList());

    }

    public List<Utilisateur> TriParId() {
        List<Utilisateur> utilisateurs = afficher();
        List<Utilisateur> resultat = utilisateurs.stream().sorted(Comparator.comparingInt(Utilisateur::getId)).collect(Collectors.toList());
        return resultat;

    }

    public List<Utilisateur> TriParUsername() {
        List<Utilisateur> utilisateurs = afficher();
        List<Utilisateur> resultat = utilisateurs.stream().sorted(Comparator.comparing(Utilisateur::getUsername)).collect(Collectors.toList());
        return resultat;

    }

}
