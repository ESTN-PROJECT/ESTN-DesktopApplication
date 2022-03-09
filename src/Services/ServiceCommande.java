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
import Models.Commande;
import Models.Produit;
import Database.MyDB;

import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author DALI CHARFEDDINE
 */
public  class ServiceCommande  {

    static java.sql.Connection cnx = MyDB.getInstance().getcnx();
 private Statement ste;
    private ResultSet rs;
    
    
      public void ajouter(){
    Scanner a = new Scanner(System.in) ;
         System.out.println("donner la Date de la commande");
         String date=a.next();
         System.out.println("donner le Prix");
         float prix=a.nextFloat();
         String query = "INSERT INTO commande(`date`, `prix`,`IsDelete`) VALUES ('"+date+"','"+prix+"','" +0+ "')";

          try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
        }
      }
     
    
    public List<Commande> afficher() {
        List<Commande> commandes = new ArrayList<>();
        try {

            String req = "SELECT * from commande WHERE `IsDelete`="+0;

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                commandes.add(new Commande(rs.getInt(1), rs.getString(2), rs.getFloat(3)));
            }

        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return commandes;
    }
    
    public void modifier(Commande u, int id) {
        PreparedStatement ps;
        String query = "UPDATE `commande` SET `date`=?,`prix`=? WHERE `num_cmd`= " + id;
        try {


            ps = cnx.prepareStatement(query);

            ps.setString(1, u.getDate());
           
            ps.setFloat(2, u.getPrix());

          

            ps.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

   
    public void Supprimer(int t) {
        PreparedStatement ps;

        String query = "UPDATE `commande` SET `IsDelete`=1 WHERE `num_cmd`= " + t;

        try {
            ps = cnx.prepareStatement(query);

            ps.execute();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }
  
          public List<Commande> Tri() {
        Comparator<Commande> comparator = Comparator.comparing(Commande::getNum_cmd);
        List<Commande> rdv = afficher();
        return rdv.stream().sorted(comparator).collect(Collectors.toList());
    }
    
        
        
                
                
                public  void Recherche() {
                    Scanner sc =new Scanner (System.in);
                    System.out.println("donner le numero de commande");
                    int num_cmd=sc.nextInt();
                    afficher().stream().filter(e -> e.getNum_cmd()==num_cmd).forEach(e->System.out.println(e));
}
            
    }

     


