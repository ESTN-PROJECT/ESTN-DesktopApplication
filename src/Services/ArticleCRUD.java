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
import Models.Actualite;
import Models.Article;


/**
 *
 * @author Firas
 */
public class ArticleCRUD {
    
    public void ajouterArticle(Article article){
        
        try {
            String requete= "INSERT INTO article(titre, contenu)"
                    + "VALUES (?,?)";
            PreparedStatement pst = MyDB.getInstance().getcnx()
                    .prepareStatement(requete);
            pst.setString(1, article.getTitre());
            pst.setString(2, article.getContenu());
            pst.executeUpdate();
            System.out.println("Article inserée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void suppArticle(Article article){
         try {
            String requete = "UPDATE article SET archive=1 WHERE id=?";
            PreparedStatement pst = MyDB.getInstance().getcnx()
                    .prepareStatement(requete);
            pst.setInt(1, article.getId());
            pst.executeUpdate();
            System.out.println("Article supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void updateArticle(Article article){
        try {
            String requete = "UPDATE article SET titre=?,contenu=? WHERE id=?";
            PreparedStatement pst = MyDB.getInstance().getcnx()
                    .prepareStatement(requete);
            pst.setString(1, article.getTitre());
            pst.setString(2, article.getContenu());
            pst.setInt(3, article.getId());
            pst.executeUpdate();
            System.out.println("Article modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Article> displayArticle() {
         List<Article> articleList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM article";
            Statement st = MyDB.getInstance().getcnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                  if(rs.getBoolean("archive")==true){
                    
                }else{
                Article a = new Article();
                a.setId(rs.getInt("id"));
                a.setContenu(rs.getString("contenu"));
                a.setTitre(rs.getString("titre"));
              
                articleList.add(a);
            }}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return articleList;
}
}
