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
public class Categorie {

    private int id_categorie, IsDelete, quantite;
    private String nom_categorie;

    public Categorie(int id_categorie, int quantite, String nom_categorie) {
        this.id_categorie = id_categorie;
        this.quantite = quantite;
        this.nom_categorie = nom_categorie;
    }

    public Categorie() {

    }

    public Categorie(int quantite, String nom_categorie) {
        this.quantite = quantite;
        this.nom_categorie = nom_categorie;
    }

    public int getId_categorie() {
        return id_categorie;
    }

   

  

    public int getQuantite() {
        return quantite;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

   

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    @Override
    public String toString() {
        return "Categorie{" + "quantite=" + quantite +  ", nom_categorie=" + nom_categorie + '}';
    }

}