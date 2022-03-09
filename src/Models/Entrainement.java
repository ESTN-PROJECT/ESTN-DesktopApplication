/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author bilel
 */
public class Entrainement {
      int id;
    String description,nom_jeu,nom_coach,nom_membre,tel;
    int etat;
      
    public Entrainement() {
    }

    public Entrainement(String description, String nom_jeu, String nom_coach, String nom_membre, String tel) {
        this.description = description;
        this.nom_jeu = nom_jeu;
        this.nom_coach = nom_coach;
        this.nom_membre = nom_membre;
        this.tel = tel;
    }

    public Entrainement(int id, String description, String nom_jeu, String nom_coach, String nom_membre, String tel, int etat) {
        this.id = id;
        this.description = description;
        this.nom_jeu = nom_jeu;
        this.nom_coach = nom_coach;
        this.nom_membre = nom_membre;
        this.tel = tel;
        this.etat = etat;
    }

    

   
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getNom_jeu() {
        return nom_jeu;
    }

    public void setNom_jeu(String nom_jeu) {
        this.nom_jeu = nom_jeu;
    }

    public String getNom_coach() {
        return nom_coach;
    }

    public void setNom_coach(String nom_coach) {
        this.nom_coach = nom_coach;
    }

    public String getNom_membre() {
        return nom_membre;
    }

    public void setNom_membre(String nom_membre) {
        this.nom_membre = nom_membre;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) { 
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Entrainement{" + " Nom Jeu :  " + nom_jeu + "  Nom Coach :  " + nom_coach + "  Nom Membre :  " + nom_membre + "Téléphone :  " + tel + " Description :  " + description + '}';
    }

    

    
    
    

    
    
   

    
    
   

   
    
    
    
}
