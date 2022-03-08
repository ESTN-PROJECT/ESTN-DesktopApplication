/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_pi;

import Controllers.EntrainementControllers;
import Controllers.JeuControllers;
import Controllers.SessionControllers;
import Model.Entrainement;
import Model.Jeu;
import Model.Session;

/**
 *
 * @author bilel
 */
public class Projet_pi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
      JeuControllers j = new JeuControllers();
//        j.trierjeu();
        // System.out.println(j.Recherchejeu("GTA"));
     
      //  j.ajouter(new Jeu("aaaaaazzzzhhhhzpppppp", "qsqsaaa", "olhjfgexxxxx"));
        //j.ajouter(new Jeu("bbbb","fsgsfg","tryjut"));
        System.out.println(j.afficher().toString());
       //System.out.println(j.Tri());
        //j.modifer(new Jeu("GTA","fight", "jeu pour les fan de fight"),8);
        // j.DeleteJeu(32);
        //System.out.println(j.afficher().toString());
        
        
        
//       SessionControllers s = new SessionControllers();
//         System.out.println(s.afficher().toString());
//         
        //s.ajouter(new Session(1,23918957,0,"2000-09-09","3h"));
////                  s.ajouter(new Session ("2020/07/11","2020/07/12"));
//         //s.modifer(new Session("ssss","ssssss"),5);
//           System.out.println(s.afficher().toString());
//
//         s.supprimer(6);
//           s.supprimer(7);
//                  System.out.println(s.afficher().toString());

                 
        // EntrainementControllers e = new EntrainementControllers();
//     e.ajouter(new Entrainement(8,1,"aaaaaaaa",0));
         //e.ajouter(new Entrainement(38,2,"hhhhhhhhhh"));
          // System.out.println(e.afficher().toString());
        //e.DeleteJeu(63);
        //System.out.println(e.afficher().toString());
        //e.modifer(new Entrainement(20,1,"mpmpmpmp",0),63);
//            System.out.println(e.afficher().toString());

        
    }
    
}
