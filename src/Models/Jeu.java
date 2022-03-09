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
public class Jeu {
    
    private static int id_courant;

    public static int getId_courant() {
        return id_courant;
    }
    private static int id_courant_do;

    public static int getId_courant_do() {
        return id_courant_do;
    }

    public static void setId_courant(int id_courant) {
        Jeu.id_courant = id_courant;
    }
    
    int id;
    String nom;
    String categorie;
    String description;
    int etat;
    String image;

    public Jeu() {
    }

    public Jeu(String nom, String categorie, String description ,String image) {
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.image = image;
        
        
    }

    public Jeu(int id, String nom, String categorie, String description,String image) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.image = image;
      
    }
   
    

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Jeu{" + "nom=" + nom + ", categorie=" + categorie + ", description=" + description + ", image=" + image + '}';
    }
    

   

    

   
    
    
}
