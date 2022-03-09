/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author Firas
 */
public class Actualite {
     private int id;
   private java.sql.Date date_ajout;
   private String descrition;

    

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

    public Actualite() {
    }

    public Actualite(int id, java.sql.Date date_ajout, String descrition) {
        this.id = id;
        this.date_ajout = date_ajout;
        this.descrition = descrition;
    }

    public Actualite(java.sql.Date date_ajout, String descrition) {
        this.date_ajout = date_ajout;
        this.descrition = descrition;
    }

    @Override
    public String toString() {
        return "Actualite{" + "date_ajout=" + date_ajout + ", description=" + descrition + '}';
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

 
  
}
