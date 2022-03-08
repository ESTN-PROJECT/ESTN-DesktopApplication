/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pidev.entities.Actualite;
import pidev.tools.MyConnection;

/**
 *
 * @author Firas
 */
public class ActualiteCRUD {
    
    
    public void ajouterActualite(Actualite actualite){
        try {
            String requete= "INSERT INTO actualité(date_ajout,description,image)"
                    + "VALUES (?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setDate(1,actualite.getDate_ajout());
           pst.setString(2, actualite.getDescrition());
           pst.setString(3, actualite.getImage());
            pst.executeUpdate();
            System.out.println("Actualité inserée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void suppActualite(Actualite actualite){
         try {
            String requete = "UPDATE actualité SET archive=1 WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, actualite.getId());
            pst.executeUpdate();
            System.out.println("Actualité supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void updateActualite(Actualite actualite){
         try {
            String requete = "UPDATE actualité SET date_ajout=?,description=?,image=? WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setDate(1, actualite.getDate_ajout());
            pst.setString(2, actualite.getDescrition());
             pst.setString(3, actualite.getImage());
            pst.setInt(4, actualite.getId());
            pst.executeUpdate();
            System.out.println("Actualite modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Actualite> displayActualite() {
         List<Actualite> actualiteList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM actualité";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                if(rs.getBoolean("archive")==true){
                    
                }else{
                Actualite a = new Actualite();
                a.setId(rs.getInt("id"));
                a.setDate_ajout(rs.getDate("date_ajout"));
                a.setDescrition(rs.getString("description"));
                a.setImage(rs.getString("image"));
              
                actualiteList.add(a);
            }}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return actualiteList;
}
    
    
    public List<Actualite> getActualiteParDate(Date date)throws SQLException{
                List<Actualite> list= new ArrayList<>();

       
       Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
        String query="select * from actualité where date_ajout="+"'"+date+"'";
        ResultSet rs=st.executeQuery(query);
        while(rs.next())
        {
            Actualite a=new Actualite(rs.getInt("id"),rs.getDate("date_ajout"),rs.getString("description"),rs.getString("image"));
            list.add(a);
        }
        return list;
    }
    
    }
    

