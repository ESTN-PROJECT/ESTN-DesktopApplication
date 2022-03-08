/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author bilel
 */
public class Session {
    
    int id,téléphone_coach,etat;
   
   Date date ;
            String description,duree,nom_membre;

    public Session() {
    }

    public Session(String nom_membre, int téléphone_coach, int etat, Date date) {
        this.nom_membre = nom_membre;
        this.téléphone_coach = téléphone_coach;
        this.etat = etat;
        this.date = date;
    }

    public Session(int id, String nom_membre, int téléphone_coach, int etat, Date date, String duree) {
        this.id = id;
        this.nom_membre = nom_membre;
        this.téléphone_coach = téléphone_coach;
        this.etat = etat;
        this.date = date;
        this.duree = duree;
    }

    public Session(int téléphone_coach, Date date, String description, String duree, String nom_membre) {
        this.téléphone_coach = téléphone_coach;
        this.date = date;
        this.description = description;
        this.duree = duree;
        this.nom_membre = nom_membre;
    }

   

 
    
    
            
            
            
            

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_membre() {
        return nom_membre;
    }

    public void setNom_membre(String nom_membre) {
        this.nom_membre = nom_membre;
    }

  

    public int getTéléphone_coach() {
        return téléphone_coach;
    }

    public void setTéléphone_coach(int téléphone_coach) {
        this.téléphone_coach = téléphone_coach;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Session{" + "téléphone_coach=" + téléphone_coach + ", date=" + date + ", description=" + description + ", duree=" + duree + ", nom_membre=" + nom_membre + '}';
    }
    
    

    
            
    
    
    
}
