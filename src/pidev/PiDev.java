/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */





package pidev;

import Controllers.Evenement_Controller;
import Controllers.Participation_Controller;
import Model.Evenement;
import Model.Participation;
import javax.swing.SpringLayout;

/**
 *
 * @author AZIZ
 */
public class PiDev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            
        Evenement_Controller ec = new Evenement_Controller();
     
        Participation_Controller pc = new Participation_Controller();
 
        //System.out.println(pc.afficher_participation());
      //  pc.Supprimer_Participation(1);
    
        
        //ec.Ajouter_Evenement(new Evenement( "League","eah","12/03/2022"));
    
           //ec.Modifer_Evenement(new Evenement("hehejjjjjjjjjjj","eah","12/03/2022"), 9);

       // ec.Supprimer_Evenement(4);
         //  System.out.println(e.afficher());
     
    
    
}
}
