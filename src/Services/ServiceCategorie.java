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
import Models.Categorie;
import Database.MyDB;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import Models.Produit;

/**
 *
 * @author DALI CHARFEDDINE
 */
public  class ServiceCategorie {

    static java.sql.Connection cnx = MyDB.getInstance().getcnx();
    private Statement ste;
    private ResultSet rs;
    

    
     public void ajouter(Categorie p){
String query = "INSERT INTO categorie( `nom_categorie`, `quantite`,`IsDelete`) VALUES ('" + p.getNom_categorie()+ "','" +p.getQuantite()+ "','" +0+ "')";

           try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    

    public List<Categorie> afficher() {
        List<Categorie> Categories = new ArrayList<>();
        try {

            String req = "SELECT * from categorie WHERE `IsDelete`="+0;

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Categories.add(new Categorie( rs.getInt("id_categorie"),rs.getInt("quantite"),rs.getString("nom_categorie")));
                              
            }

        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return Categories;
    }

    

    public boolean modifier(Categorie p) {
        try {
            String req = " UPDATE categorie SET `nom_categorie`= '" + p.getNom_categorie() + "' , "
                   
                   
                   
                    + "`quantite`='" + p.getQuantite()
                    + "' WHERE `id_categorie` = '" + p.getId_categorie() + "'";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    
   public void Supprimer(int t) {
        PreparedStatement ps;

        String query = "UPDATE `categorie` SET `IsDelete`=1 WHERE `id_categorie`= " + t;

        try {
            ps = cnx.prepareStatement(query);

            ps.execute();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }
    
   
                    public List<Categorie> Recherche(String nom_categorie) {

        return afficher().stream().filter(a -> a.getNom_categorie().equals(nom_categorie)).collect(Collectors.toList());
}
        public List<Categorie> Tri() {
        Comparator<Categorie> comparator = Comparator.comparing(Categorie::getQuantite);
        List<Categorie> prd = afficher();
        return prd.stream().sorted(comparator).collect(Collectors.toList());
    }

   public long Recherche1() {

        List<Categorie> Categorie = afficher();
        return Categorie.stream().filter(b -> b.getQuantite()> 20).filter(b -> b.getQuantite()< 50 ).count();

      
        }
   
      public long Recherche2() {

        List<Categorie> Categorie = afficher();
        return Categorie.stream().filter(b -> b.getQuantite()> 50).filter(b -> b.getQuantite()< 70 ).count();

}
       public long Recherche3() {

        List<Categorie> Categorie = afficher();
        return Categorie.stream().filter(b -> b.getQuantite()<20 ).count();

}
       public long Recherche4() {

        List<Categorie> Categorie = afficher();
 return Categorie.stream().filter(b -> b.getQuantite()> 70).filter(b -> b.getQuantite()< 100 ).count();
}
         public List<String> affichernp() {
        List<String> Categorie = new ArrayList<>();
        try {
            String req = "SELECT nom_categorie FROM categorie WHERE IsDelete = 0 ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Categorie.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return Categorie;
    }
}



