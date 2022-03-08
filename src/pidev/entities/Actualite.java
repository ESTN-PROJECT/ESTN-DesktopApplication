/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

import java.util.Date;

/**
 *
 * @author Firas
 */
public class Actualite {
     private int id;
   private java.sql.Date date_ajout;
   private String descrition;
   private String image;

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.sql.Date getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(java.sql.Date date_ajout) {
        this.date_ajout = date_ajout;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Actualite() {
    }

    public Actualite(int id, java.sql.Date date_ajout, String descrition,String image) {
        this.id = id;
        this.date_ajout = date_ajout;
        this.descrition = descrition;
        this.image=image;
    }

    public Actualite(java.sql.Date date_ajout, String descrition ,String image) {
        this.date_ajout = date_ajout;
        this.descrition = descrition;
        this.image=image;
    }

    @Override
    public String toString() {
        return "Actualite{" + "date_ajout=" + date_ajout + ", description=" + descrition + ", image=" + image+'}';
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

 
  
}
