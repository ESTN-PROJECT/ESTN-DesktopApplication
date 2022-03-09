/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Database.MyDB;
import IServices.Evenement_Crud;
import Models.Evenement;
import com.mysql.jdbc.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author AZIZ
 */
public class Evenement_Controller implements Evenement_Crud {

    static java.sql.Connection cnx = MyDB.getInstance().getcnx();
    private Statement ste;
    private ResultSet rs;

    /**
     *
     * @return
     */
    @Override
    public List<Evenement> afficher() {
        List<Evenement> evenements = new ArrayList<>();
        try {
            String querydisplay = " SELECT * FROM `evenement` where `isdeleted`= " + 0;

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(querydisplay);

            while (rs.next()) {
                Evenement e = new Evenement();
                e.setId_event(rs.getInt(1));
                e.setTitre_event(rs.getString(2));
                e.setDescription_event(rs.getString(3));
                e.setDate_event(rs.getDate(4));
                e.setIsdeleted(rs.getInt(5));

                evenements.add(e);
            }

        } catch (SQLException ex) {
        }
        return evenements;
    }

    @Override
    public void Ajouter_Evenement(Evenement e) {

        String query = "INSERT INTO `evenement`( `titre_event`, `description_event`, `date_event`) VALUES ('" + e.getTitre_event() + "','" + e.getDescription_event() + "','" + e.getDate_event() + "')";

        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Evenement_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override

    // First :condition boolean if event is already exist in list or not / database or not .Aziz comment. 
    public void Modifer_Evenement(Evenement e, int id_event) {

        PreparedStatement ps;
        String query = "UPDATE `evenement` SET `titre_event`=?,`description_event`=?,`date_event`=? WHERE `id_event`= '" + id_event + "'";
        try {

            ps = cnx.prepareStatement(query);

            ps.setString(1, e.getTitre_event());
            ps.setString(2, e.getDescription_event());
            ps.setDate(3, new java.sql.Date(e.getDate_event().getTime()));
            //ps.setDate(3, (Date) e.getDate_event());

            ps.execute();
            System.out.println("Done!");

        } catch (SQLException ex) {
        }
    }

    /* @Override
     public void Supprimer_Evenement(int e) {
        PreparedStatement ps;

        String query = "DELETE FROM `evenement` WHERE `id_event`=?  ";

        try {
            ps = cnx.prepareStatement(query);

            ps.setInt(1, e);

            ps.execute();

            System.out.println("event deleted");
        } catch (SQLException ex) {
            System.out.println(e);
        }
    }
     */
    @Override
    public void Supprimer_Evenement(int e) {

        PreparedStatement ps;

        String query = "UPDATE `evenement` SET `isdeleted`=1 WHERE `id_event`= " + e;

        try {
            ps = cnx.prepareStatement(query);

            ps.execute();

        } catch (SQLException ex) {
            System.out.println(e);
        }

    }

    @Override
    public List<Evenement> Recherche_Evenement(String titre_event) {
        List<Evenement> List_evenement = new ArrayList<Evenement>();

        try {
            String requete3 = "SELECT * From evenement where titre_event like '%" + titre_event + "%'";
            Statement st3 = MyDB.getInstance().getcnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setId_event(rs.getInt("id_event"));
                e.setTitre_event(rs.getString("titre_event"));
                e.setDescription_event(rs.getString("description_event"));
                e.setDate_event(rs.getDate("date_event"));

                List_evenement.add(e);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return List_evenement;

    }

    @Override
    public List<Evenement> TriParId() {
        List<Evenement> utilisateurs = afficher();
        List<Evenement> resultat = utilisateurs.stream().sorted(Comparator.comparingInt(Evenement::getId_event)).collect(Collectors.toList());
        return resultat;

    }

    @Override
    public List<Evenement> TriParTitre() {
        List<Evenement> utilisateurs = afficher();
        List<Evenement> resultat = utilisateurs.stream().sorted(Comparator.comparing(Evenement::getTitre_event)).collect(Collectors.toList());
        return resultat;

    }

    @Override

    public List<Evenement> Trie_Evenement() {
        List<Evenement> evenements = new ArrayList<>();
        String sql = "select * from evenementt ORDER BY id_event DESC";
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(sql);
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setId_event(rs.getInt("id_event"));
                e.setTitre_event(rs.getString("titre_event"));
                e.setDescription_event(rs.getString("description_event"));
                e.setDate_event(rs.getDate("date_event"));

                evenements.add(e);
                System.out.println(e.toString());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return evenements;
    }

    @Override
    public List RechercheByTitleEvent(String username) {

        return afficher().stream().filter(a -> a.getTitre_event().equals(username)).collect(Collectors.toList());

    }

    public List<String> affichernp() {
        List<String> evenements = new ArrayList<>();
        try {
            String req = "SELECT titre_event FROM evenement WHERE IsDelete = 0 ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                evenements.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return evenements;
    }

}
