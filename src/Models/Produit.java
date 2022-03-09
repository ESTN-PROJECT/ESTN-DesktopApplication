/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author DALI CHARFEDDINE
 */
public class Produit {

  


    

    
    private int id,IsDelete;
    private String nom,photo;
    private float prix ;
    private String description,categorie;
    
    
private static int id_courant;

    public static int getId_courant() {
        return id_courant;
    }
 

    public static void setId_courant(int id_courant) {
        Produit.id_courant = id_courant;
    }


    public Produit() {
    }

    public Produit(int id, int IsDelete,String photo, String nom, float prix, String description, String categorie) {
        this.id = id;
        this.IsDelete = IsDelete;
        this.nom = nom;
        
        this.prix = prix;
        this.description = description;
        this.categorie = categorie;
    }

    public Produit(int id, String nom, String photo ,float prix, String description, String categorie) {
        this.id = id;
        this.nom = nom;
        this.photo = photo;
        this.prix = prix;
        this.description = description;
        this.categorie = categorie;
    }

    public Produit(String nom, float prix, String description, String categorie) {
        this.nom = nom;

        this.prix = prix;
        this.description = description;
        this.categorie = categorie;
    }

    public Produit(String nom, String photo, float prix, String description, String categorie) {
        this.nom = nom;
        this.photo = photo;
        this.prix = prix;
        this.description = description;
        this.categorie = categorie;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
   

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public float getPrix() {
        return prix;
    }

    public String getDescription() {
        return description;
    }

   

    public String getCategorie() {
        return categorie;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setIsDelete(int IsDelete) {
        this.IsDelete = IsDelete;
    }

    @Override
    public String toString() {
        return "Produit{" + "nom=" + nom + ", photo=" + photo + ", prix=" + prix + ", description=" + description + ", categorie=" + categorie + '}';
    }

  

    

    public int getIsDelete() {
        return IsDelete;
    }

   
   

    public void setId(int id) {
        this.id = id;
    }

    

}