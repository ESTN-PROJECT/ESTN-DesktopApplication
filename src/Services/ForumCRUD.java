/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Database.MyDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Models.Discussion;
import Models.Forum;

/**
 *
 * @author Firas
 */
public class ForumCRUD {
    public void ajouterForum(Forum forum){
        try {
            String requete= "INSERT INTO forum(sujet)"
                    + "VALUES (?)";
            PreparedStatement pst = MyDB.getInstance().getcnx()
                    .prepareStatement(requete);
            pst.setString(1, forum.getSujet());
            pst.executeUpdate();
            System.out.println("Forum inserée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
    }
    public void suppForum(Forum forum){
        
         try {
            String requete = "UPDATE forum SET archive=1 WHERE id=?";
            PreparedStatement pst = MyDB.getInstance().getcnx()
                    .prepareStatement(requete);
            pst.setInt(1, forum.getId());
            pst.executeUpdate();
            System.out.println("Forum supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void updateForum(Forum forum){
        
         try {
            String requete = "UPDATE forum SET sujet=? WHERE id=?";
            PreparedStatement pst = MyDB.getInstance().getcnx()
                    .prepareStatement(requete);
            pst.setString(1, forum.getSujet());
            pst.setInt(2, forum.getId());
            pst.executeUpdate();
            System.out.println("Forum modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     public List<Forum> displayForum() {
         List<Forum> forumList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM forum";
            Statement st = MyDB.getInstance().getcnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                  if(rs.getBoolean("archive")==true){
                    
                }else{
                Forum f = new Forum();
                f.setId(rs.getInt("id"));
                f.setSujet(rs.getString("sujet"));
                             
                forumList.add(f);
            }}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return forumList;
}
}
