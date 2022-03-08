/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author AZIZ
 */
public class Participation {
private int id_part;
private String nom_part  ;
private String prenom_part ;         
private Date date_participation;
private String titre_event;     
private int isdeteled;

    public Participation(String nom_part, String prenom_part, Date date_participation, String titre_event) {
        this.nom_part = nom_part;
        this.prenom_part = prenom_part;
        this.date_participation = date_participation;
        this.titre_event= titre_event;
    }

    public String getTitre_event() {
        return titre_event;
    }

    public void setTitre_event(String titre_event) {
        this.titre_event = titre_event;
    }
    

    public int getId_part() {
        return id_part;
    }

    public void setId_part(int id_part) {
        this.id_part = id_part;
    }

    public String getNom_part() {
        return nom_part;
    }

    public void setNom_part(String nom_part) {
        this.nom_part = nom_part;
    }

    public String getPrenom_part() {
        return prenom_part;
    }

    public void setPrenom_part(String prenom_part) {
        this.prenom_part = prenom_part;
    }




    public int getIsdeteled() {
        return isdeteled;
    }

    public void setIsdeteled(int isdeteled) {
        this.isdeteled = isdeteled;
    }
 
   public Participation(){}
   
    public Date getDate_participation() {
        return date_participation;
    }

    public void setDate_participation(Date date_participation) {
        this.date_participation = date_participation;
    }

    @Override
    public String toString() {
        return "Participation{" + "id_part=" + id_part + ", nom_part=" + nom_part + ", prenom_part=" + prenom_part + ", date_participation=" + date_participation + ", titre_event=" + titre_event + ", isdeteled=" + isdeteled + '}';
    }



 

   

 
 

}


