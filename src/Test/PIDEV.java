/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Controllers.UtilisateurController;
import Models.Coach;
import Models.Member;
import Models.Utilisateur;
import java.sql.Date;

/**
 *
 * @author HP
 */
public class PIDEV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UtilisateurController uc = new UtilisateurController();

        //Utilisateur a = Utilisateur.getInstance();
        System.out.println(uc.afficher().toString());
         //uc.SignUpCoach(Coach.getInstance("taw", "10-10-1999", "yalellee", "5500", "plat", "hehehe", "50.2", " "));
        // Member m1 = Member.getInstance("tryrty", "30-10-2008", "mahddfia", "565500", "plfgat", " ");
        // uc.SignUpMember(m1);
        //uc.UpdateCoach(Coach.getInstance("koko", "10-12-2021", "yalellee", "5500", "plat", "hehehe", 50.2f, " "), 28);
        //uc.UpdateMember(Member.getInstance("la", "30-10-2011", "mahddfia", "565500", "plfgat", " "), 18);
        //uc.DeleteUser(28);
        //uc.FindUser(25);
        // uc.Login("koko", "5500");
        //System.out.println(uc.TriParId());
        //System.out.println(uc.TriParUsername());
        //System.out.println(uc.RechercheById(28));
        //System.out.println(uc.RechercheByUsername("koko"));


    }
}
