/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Database.MyDB;
import Interface.IService;
import Model.Entrainement;
import Model.Jeu;
import Model.Session;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bilel
 */
public class SessionControllers {

    private Connection cnx;

    public SessionControllers() {
        cnx = MyDB.getConnect();
    }

    public List afficher() {
         List<Session> Session = new ArrayList();

        try {
            Statement stm = cnx.createStatement();
            String querry = "SELECT * FROM `SESSION` WHERE `etat`="+0;

            ResultSet rs = stm.executeQuery(querry);

            while (rs.next()) {
                Session s = new Session();
                s.setId(rs.getInt(1));
                s.setDate(rs.getDate(2));
                s.setDuree(rs.getString(3));
                s.setNom_membre(rs.getString(4));
                s.setTéléphone_coach(rs.getInt(5));
                s.setEtat(rs.getInt(6));
                Session.add(s);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return Session;
 
    }

    public void DeleteJeu(int t) {
        PreparedStatement ps;

        String query = "UPDATE session SET `etat`=1 WHERE `id`= " + t;

        try {
            ps = cnx.prepareStatement(query);

            ps.execute();

        } catch (SQLException e) {
            System.out.println(e);
        }
   }



    

   
    public void ajouter(Session s) {
        String query = "INSERT INTO `session`(`date`, `duree`, `nom_membre`, `tel_coach`) VALUES ('"+s.getDate()+"'"+ s.getDuree()+"','"+ s.getNom_membre()+ "','"+s.getTéléphone_coach()+"')";
    
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(JeuControllers.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    
    
    
    
    
    
    public boolean modifer(Session s) {
               try {
            String req = " UPDATE jeu SET `date`= '" + s.getDate()+ "' , "
                    + "`duree`='" + s.getDuree()+ "' ,"
                     + "`nom_membre`='" + s.getNom_membre()+ "' ,"
                    + "`tel_coach`='" + s.getTéléphone_coach()+                 
                    "' WHERE `id` = '" + s.getId() + "'";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
  
}
