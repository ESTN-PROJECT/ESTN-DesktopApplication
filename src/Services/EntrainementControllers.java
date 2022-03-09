/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Database.MyDB;

import Models.Entrainement;
import Models.Jeu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author bilel
 */
public class EntrainementControllers  {
    
      private Connection cnx;

    public EntrainementControllers() {
        cnx = MyDB.getInstance().getcnx();
    }


   

    public List afficher() {
List<Entrainement> Entrainement = new ArrayList();

        try {
            Statement stm = cnx.createStatement();
            String querry = "SELECT * FROM `ENTRAINEMENT` WHERE `etat`="+0;

            ResultSet rs = stm.executeQuery(querry);

            while (rs.next()) {
                Entrainement e = new Entrainement();
                e.setId(rs.getInt(1));
                e.setNom_jeu(rs.getString(2));
                e.setNom_coach(rs.getString(3));
                e.setNom_membre(rs.getString(4));
                e.setTel(rs.getString(5));
                 e.setDescription(rs.getString(6));
                e.setEtat(rs.getInt(7));
               
                Entrainement.add(e);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return Entrainement; 
    }

 

    public void DeleteJeu(int t) {
        PreparedStatement ps;

        String query = "UPDATE entrainement SET `etat`=1 WHERE `id`= " + t;

        try {
            ps = cnx.prepareStatement(query);

            ps.execute();

        } catch (SQLException e) {
            System.out.println(e);
        }
   }


  

    public void ajouter(Entrainement e) {
 String query = "INSERT INTO `entrainement`(`nom_jeu`,`nom_coach`,`nom_membre`,`telephone`,`description`) VALUES ('"+ e.getNom_jeu()+"','"+ e.getNom_coach()+ "','"+e.getNom_membre()+"','"+e.getTel()+"','" + e.getDescription()+"')";

        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(EntrainementControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
            }

    public boolean modifer(Entrainement e) {
  try {
            String req = " UPDATE entrainement SET `nom_jeu`= '" + e.getNom_jeu()+ "' , "
                    + "`nom_coach`='" + e.getNom_coach()+ "' ,"
                    + "`nom_membre`='" + e.getNom_membre()+ "' ,"
                    + "`telephone`='" + e.getTel()+ "' ,"
                    + "`description`='" + e.getDescription()+  
                    "' WHERE `id` = '" + e.getId() + "'";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    
    
    public List<Entrainement> Tri() {
        Comparator<Entrainement> comparator = Comparator.comparing(Entrainement::getDescription);
        List<Entrainement> entrainement = afficher();
        return entrainement.stream().sorted(comparator).collect(Collectors.toList());
    }
    
    
    
    
//    public List afficherC() {
//List<Entrainement> Entrainement = new ArrayList();
//
//        try {
//            Statement stm = cnx.createStatement();
//            String querry = "select * from entrainement e join jeu j on e.Nom_jeu=j.nom";
//
//            ResultSet rs = stm.executeQuery(querry);
//
//            while (rs.next()) {
//                Entrainement e = new Entrainement();
//                 e.setId(rs.getInt(1));
//                e.setNom_jeu(rs.getString(2));
//                e.setNom_coach(rs.getString(3));
//                e.setDescription(rs.getString(4));
//                e.setEtat(rs.getInt(5));
//               
//                Entrainement.add(e);
//            }
//
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//
//        }
//
//        return Entrainement; 
//    }

//       public  void Recherche() {
//                    Scanner sc =new Scanner (System.in);
//                    System.out.println("donner nom de jeu");
//                    String nom_coach=sc.next();
//                    afficher().stream().filter(e -> (e.getClass()== null ? nom_coach == null : e.getClass().equals(nom_coach))).forEach(e->System.out.println(e));
//}
    }

   
   

