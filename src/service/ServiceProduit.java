/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Produit;
import Database.MyDB;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author DALI CHARFEDDINE
 */
public class ServiceProduit {

    static java.sql.Connection cnx = MyDB.getInstance().getCnx();
    private Statement ste;
    private ResultSet rs;

    public void ajouter(Produit p) {
        String query = "INSERT INTO produit( `nom`,`photo`, `prix`, `description`, `categorie`,`IsDelete`) VALUES ('" + p.getNom() + "','" + p.getPhoto() + "','" + p.getPrix() + "','" + p.getDescription() + "','" + p.getCategorie() + "','" + 0 + "')";

        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Produit> afficher() {
        List<Produit> produit = new ArrayList<>();
        try {

            String req = "SELECT * from produit  WHERE `IsDelete`=" + 0;

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                produit.add(new Produit(rs.getInt(1),rs.getString(2),rs.getString(6),rs.getFloat(3),rs.getString(4),rs.getString(5)));
            }

        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return produit;
    }

    public boolean modifer(Produit p) {
        try {
            String req = " UPDATE produit SET `nom`= '" + p.getNom() + "' , "
                    + "`prix`='" + p.getPrix() + "' ,"
                   
                    + "`description`='" + p.getDescription() + "',"
                    + "`categorie`='" + p.getCategorie()                   
                    + "' WHERE `id` = '" + p.getId() + "'";
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

        String query = "UPDATE `produit` SET `IsDelete`=1 WHERE `id`= " + t;

        try {
            ps = cnx.prepareStatement(query);

            ps.execute();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public List<Produit> Recherche(String nom) {

        return afficher().stream().filter(a -> a.getNom().equals(nom)).collect(Collectors.toList());

    }

    public List<Produit> Tri() {
        Comparator<Produit> comparator = Comparator.comparing(Produit::getPrix);
        List<Produit> prd = afficher();
        return prd.stream().sorted(comparator).collect(Collectors.toList());
    }
    
public Produit GetProdbyid(int b) throws SQLException {

        //-------------------- Update ----------//
        Produit pr = new Produit();

        String query = "select * from Produit where id = ? ";
        PreparedStatement ps;
        try {
            ps = MyDB.getInstance().getCnx().prepareCall(query);
            ps.setInt(1, b);
            ResultSet rest = ps.executeQuery();

            while (rest.next()) {
                pr.setId(rest.getInt("id"));
                pr.setNom(rest.getString("nom"));
                pr.setPrix(rest.getFloat("prix"));
                pr.setPhoto(rest.getString("photo"));
                pr.setDescription(rest.getString("description"));
                pr.setCategorie(rest.getString("Categorie"));
               
                

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pr;

    }
  



}
