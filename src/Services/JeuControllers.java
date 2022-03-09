/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Database.MyDB;


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
public class JeuControllers  {
    
     private Connection cnx;
     private Statement ste;
    
    private ResultSet rs;

    public JeuControllers() {
        cnx = MyDB.getInstance().getcnx();
    }

    public void ajouter(Jeu j) {
        
      String query = "INSERT INTO `jeu`(`nom`, `categorie`, `description`,`image`) VALUES ('"+ j.getNom()+"','"+ j.getCategorie()+ "','" + j.getDescription()+"','"+j.getImage()+"')"  ;

        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(JeuControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public List afficher() {
       List<Jeu> Jeu = new ArrayList();

        try {
            Statement stm = cnx.createStatement();
            String querry = "SELECT * FROM `JEU` WHERE `etat`=" +0;

            ResultSet rs = stm.executeQuery(querry);

            while (rs.next()) {
                Jeu j = new Jeu();
                j.setId(rs.getInt(1));
                j.setNom(rs.getString(2));
                j.setCategorie(rs.getString(3));
                j.setDescription(rs.getString(4));
                j.setImage(rs.getString(5));
                j.setEtat(rs.getInt(6));
                Jeu.add(j);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return Jeu;
    }

   public void DeleteJeu(int t) {
        PreparedStatement ps;

        String query = "UPDATE jeu SET `etat`=1 WHERE `id_jeu`= " + t;

        try {
            ps = cnx.prepareStatement(query);

            ps.execute();

        } catch (SQLException e) {
            System.out.println(e);
        }
   }


    public boolean modifer(Jeu j) {
        
       try {
            String req = " UPDATE jeu SET `nom`= '" + j.getNom() + "' , "
                    + "`categorie`='" + j.getCategorie()+ "' ,"
                    + "`description`='" + j.getDescription() +                 
                    "' WHERE `id_jeu` = '" + j.getId() + "'";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    
    
//    public List<Jeu> trierjeu(){
//        List<Jeu> jeu = new ArrayList<>();
//        String sql="select * from jeu ORDER BY nom ASC ";
//        try {
//            ste = cnx.createStatement();
//           rs= ste.executeQuery(sql);
//           while(rs.next()){
//               Jeu e = new Jeu();
//               e.setId(rs.getInt("id_jeu"));
//               e.setNom(rs.getString("nom"));
//               e.setCategorie(rs.getString("categorie"));
//               e.setDescription(rs.getString("description"));
//               e.setEtat(rs.getInt("etat"));
//               jeu.add(e);
//               System.out.println(e.toString());
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//        return jeu;
//    }
//    
//    
//    public List<Jeu> Recherchejeu(String nom) {
//        List<Jeu> myList = new ArrayList<Jeu>();
//
//        try {
//            String requete3 = "SELECT * From jeu where nom like '%" + nom + "%'";
//            Statement st3 = MyDB.getInstance().getcnx().createStatement();
//            ResultSet rs = st3.executeQuery(requete3);
//            while (rs.next()) {
//                Jeu p = new Jeu();
//                p.setId(rs.getInt("id_jeu"));
//               p.setNom(rs.getString("nom"));
//               p.setCategorie(rs.getString("categorie"));
//               p.setDescription(rs.getString("description"));
//               p.setEtat(rs.getInt("etat"));
//                
//                myList.add(p);
//
//            }
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//
//        }
//        return myList;
//
//    }

//    public  void Recherche() {
//                    Scanner sc =new Scanner (System.in);
//                    System.out.println("donner nom de jeu");
//                    String nom=sc.next();
//                    afficher().stream().filter(e -> (e.getNom()== null ? nom == null : e.getNom().equals(nom))).forEach(e->System.out.println(e));
//}
  public List<Jeu> Tri() {
        Comparator<Jeu> comparator = Comparator.comparing(Jeu::getNom);
        List<Jeu> jeu = afficher();
        return jeu.stream().sorted(comparator).collect(Collectors.toList());
    }

  
    
   

//    public List<Jeu> TriParUsername() {
//        List<Jeu> jeu = afficher();
//        List<Jeu> resultat = jeu.stream().sorted(Comparator.comparing(Jeu::getNom)).collect(Collectors.toList());
//        return resultat;
//
//    }

    public Iterable<Jeu> Recherche(String recherche) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public List<String> affichernp() {
        List<String> Jeu = new ArrayList<>();
        try {
            String req = "SELECT nom FROM jeu WHERE etat = 0 ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Jeu.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return Jeu;
    }
    
}
