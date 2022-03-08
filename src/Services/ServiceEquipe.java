/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.Scrimms;
import Models.Equipe;
import Database.MyDB;
import Interfaces.Iservice;
import java.util.Scanner;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import Models.Equipe;
import java.sql.Connection;
import java.util.Comparator;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Guide Info
 */
public class ServiceEquipe  {
   Connection cnx;

    public ServiceEquipe() {
        cnx = MyDB.getInstance().getcnx();
    }
    public void ajouter(Equipe e) {
           try {
            String requete2 = "INSERT INTO equipe (nom_equipe,team_leader_ign,player_2_ign,player_3_ign,player_4_ign,player_5_ign,jeu) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = MyDB.getInstance().cnx.prepareStatement(requete2);
            pst.setString(1, e.getNom_equipe());
            pst.setString(2, e.getTeam_leader_ign());
            pst.setString(3, e.getP2());
            pst.setString(4, e.getP3());
            pst.setString(5, e.getP4());
            pst.setString(6, e.getP5());
            pst.setString(7, e.getJeu());

          
            pst.executeUpdate();
            System.out.println("Equipe added");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Equipe e) {
 PreparedStatement ps;
        String query = "UPDATE `equipe` SET `nom_equipe`=?,`jeu`=?,`team_leader_ign`=?,`player_2_ign`=?,`player_3_ign`=?,`player_4_ign`=?,`player_5_ign`=? where id=?   " ;
        try {

            ps = cnx.prepareStatement(query);

            ps.setString(1, e.getNom_equipe());
            ps.setString(2, e.getJeu());
            ps.setString(3, e.getTeam_leader_ign());
            ps.setString(4, e.getP2());
            ps.setString(5, e.getP3());
            ps.setString(6, e.getP4());
            ps.setString(7, e.getP5());
            ps.setInt(8, e.getId());
            ps.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteEquipe(int id) {
 try {
            String requete7 = "DELETE FROM equipe WHERE id=?";
            PreparedStatement pst7 = MyDB.getInstance().cnx.prepareStatement(requete7);
            pst7.setInt(1, id);
            pst7.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
    }
    }
    
    
    public List<Equipe> afficher() {
ObservableList<Equipe> myList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM equipe";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Equipe e = new Equipe();
                e.setId(rs.getInt(1));
                e.setNom_equipe(rs.getString("nom_equipe"));
                  e.setTeam_leader_ign(rs.getString("team_leader_ign"));
                   e.setP2(rs.getString("player_2_ign"));
                    e.setP3(rs.getString("player_3_ign"));
                    e.setP4(rs.getString("player_4_ign"));
                    e.setP5(rs.getString("player_5_ign"));
                    e.setJeu(rs.getString("jeu"));

                   
                  

                myList.add(e);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
   
      return myList;

    }
    
     public List<Equipe> Tri() {
        Comparator<Equipe> comparator = Comparator.comparing(Equipe::getJeu);
        List<Equipe> equipe = afficher();
        return equipe.stream().sorted(comparator).collect(Collectors.toList());
    }
       
     public List<Equipe> RechercheByJeu(String jeu) {

        return afficher().stream().filter(a -> a.getJeu().equals(jeu)).collect(Collectors.toList());

    }

     
     public List<String> affichernp() {
        List<String> Equipe = new ArrayList<>();
        try {
            String req = "SELECT nom_equipe FROM equipe ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Equipe.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return Equipe;
    }

    }


