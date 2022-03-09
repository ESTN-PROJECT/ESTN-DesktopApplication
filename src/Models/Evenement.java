/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author AZIZ
 */

public class Evenement {
    private int id_event;
    private String titre_event;
    private String description_event;
    private Date date_event;
    private int isdeleted;

public Evenement( String titre_event, String description_event, Date date_event){

this.titre_event = titre_event;
this. description_event = description_event;
this.date_event = date_event;

}
public Evenement(){}

    public int getId_event() {
        return id_event;
    }

    public int getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(int isdeleted) {
        this.isdeleted = isdeleted;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getTitre_event() {
        return titre_event;
    }

    public void setTitre_event(String titre_event) {
        this.titre_event = titre_event;
    }

    public String getDescription_event() {
        return description_event;
    }

    public void setDescription_event(String description_event) {
        this.description_event = description_event;
    }

    public Date getDate_event() {
        return date_event;
    }

    public void setDate_event(Date date_event) {
        this.date_event = date_event;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_event=" + id_event + ", titre_event=" + titre_event + ", description_event=" + description_event + ", date_event=" + date_event + ", isdeleted=" + isdeleted + '}';
    }

   

 
  

  



}