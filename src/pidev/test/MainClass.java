/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.test;



import java.sql.SQLException;
import java.util.Date;
import pidev.entities.Actualite;
import pidev.entities.Article;
import pidev.entities.Discussion;
import pidev.entities.Forum;
import pidev.services.ActualiteCRUD;
import pidev.services.ArticleCRUD;
import pidev.services.DiscussionCRUD;
import pidev.services.ForumCRUD;

/**
 *
 * @author House
 */
public class MainClass {
    
    public static void main(String[] args) throws SQLException {
        Forum f = new Forum(1,"testModifier");
        Article a = new Article(1,"Bonjour", "Quoi de neufss");
        Discussion d = new Discussion(3,"je suis le contenu modifié ya bhim ya bhim");
        java.sql.Date date=new java.sql.Date(22, 10, 6);
       // Actualite ac=new Actualite(3,new java.sql.Date(22, 10, 6));
        ActualiteCRUD as=new ActualiteCRUD();
        DiscussionCRUD ds=new DiscussionCRUD();
        ForumCRUD fs= new ForumCRUD();
        ArticleCRUD aser= new ArticleCRUD();
       int n=0;
       while(n<3){
        ds.Filter(d);
        n++;}
        //Suppression 
        /*as.suppActualite(ac);
        ds.suppDisc(d);
        fs.suppForum(f);
        aser.suppArticle(a);
        */
        
      //  as.suppActualite(ac);
        //Modification test
     /*   as.updateActualite(ac);
        ds.updateDisscusion(d);
        fs.updateForum(f);
        aser.updateArticle(a);*/
        
        //Affichage Test
       // as.ajouterActualite(ac);
       System.out.print(as.displayActualite());
      // System.out.println(as.displayActualite());
       //System.out.println( fs.displayForum());
       //System.out.println(aser.displayArticle());
       // System.out.println(ds.displayDiscussion());
               
        //fs.ajouterForum(f);
        
        //aser.ajouterArticle(a);
      /*  System.out.print(ds.Filter(d.getContenu()));
        ds.ajouterDiscussion(d);
        ds.Filter(d.getContenu());*/
        
       // System.out.println(pcd.displayPersons());
    }
}
