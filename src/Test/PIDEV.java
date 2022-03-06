/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import models.Categorie;
import service.ServiceCommande;
import service.ServiceProduit;
import models.Commande;
import models.Produit;
import service.ServiceCategorie;


/**
 *
 * @author DALI CHARFEDDINE
 */
public class PIDEV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    ServiceProduit p =new ServiceProduit();
  ServiceCommande c =new ServiceCommande();
   
   
  System.out.println(p.afficher());  
   //p.ajouter(new Produit("zzzzhhhhzpppppp","aaaa",25.6f, "olhjfgexxxxx","zefze"));
 // p.modifer(new Produit("clavier",26.5f, "azerty", "hehe"), 26);
  //p.Recherche();
 // p.Tri();
   // p.supprimer(5);
 
// System.out.println(c.afficher());
 //c.ajouter();
  //c.modifier(new Commande("31-03-2000",90),25 );
 //c.Recherche();
  //c.supprimer(15);
 // c.Tri();
 
  // ServiceCategorie cat =new ServiceCategorie();  
   
  // System.out.println(cat.afficher());  
 //cat.ajouter(new Categorie("zzzzhhhhzpppppp",90));
  // cat.modifer(new Categorie(2,"clavier"), 1);
  //cat.Recherche();
 // cat.Tri();
   // cat.supprimer(5);
  
   



     
       
   
    }
    
}
