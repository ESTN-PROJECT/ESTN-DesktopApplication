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
public class ServiceScrimms  {
   Connection cnx;

    public ServiceScrimms() {
        cnx = MyDB.getInstance().getcnx();
    }
    public void ajouter(Scrimms S) {
           try {
            String requete2 = "INSERT INTO `scrimms` (`nom_eq1`,`nom_eq2`,`Game`) VALUES (?,?,?)";
            PreparedStatement pst = MyDB.getInstance().cnx.prepareStatement(requete2);
            pst.setString(1, S.getNom_eq1());
            pst.setString(2, S.getNom_eq2());
            pst.setString(3, S.getGame());
            

          
            pst.executeUpdate();
            System.out.println("Scrimms added");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Scrimms S) {
 PreparedStatement ps;
        String query = "UPDATE `scrimms` SET `nom_eq1`=?,`nom_eq2`=?,`Game`=? where id=?   " ;
        try {

            ps = cnx.prepareStatement(query);

            ps.setString(1, S.getNom_eq1());
            ps.setString(2, S.getNom_eq2());
            ps.setString(3, S.getGame());
            ps.setInt(4, S.getId());
            ps.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteScrimms(int id) {
 try {
            String requete7 = "DELETE FROM scrimms WHERE id=?";
            PreparedStatement pst7 = MyDB.getInstance().cnx.prepareStatement(requete7);
            pst7.setInt(1, id);
            pst7.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
    }
    }
    
    
    public List<Scrimms> afficher() {
ObservableList<Scrimms> myList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM scrimms";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Scrimms S = new Scrimms();
                S.setId(rs.getInt(1));
                S.setNom_eq1(rs.getString("nom_eq1"));
                S.setNom_eq2(rs.getString("nom_eq2"));
                S.setGame(rs.getString("Game"));
                myList.add(S);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
   
      return myList;

    }
    
     public List<Scrimms> Tri() {
        Comparator<Scrimms> comparator = Comparator.comparing(Scrimms::getGame);
        List<Scrimms> scrimms = afficher();
        return scrimms.stream().sorted(comparator).collect(Collectors.toList());
    }
       
     public List<Scrimms> RechercheByGame(String Game) {

        return afficher().stream().filter(a -> a.getGame().equals(Game)).collect(Collectors.toList());

    }


    }


