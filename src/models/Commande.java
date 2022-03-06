/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author DALI CHARFEDDINE
 */
public class Commande {

   
   int num_cmd,IsDelete;
   float prix ;
   String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String date = simpleDateFormat.format(new Date());

    public Commande() {
    }
    

    public Commande(int num_cmd, String date, float prix) {
        this.num_cmd = num_cmd;
        this.date = date;
        this.prix = prix;
    }

    public Commande(String date, float prix) {
        this.date = date;
        this.prix = prix;
    }

    public int getNum_cmd() {
        return num_cmd;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    

    public float getPrix() {
        return prix;
    }

    public void setNum_cmd(int num_cmd) {
        this.num_cmd = num_cmd;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getIsDelete() {
        return IsDelete;
    }

    public void setIsDelete(int IsDelete) {
        this.IsDelete = IsDelete;
    }
     public static void add(Commande e) {
        
    }

    @Override
    public String toString() {
        return "Commande{" + "num_cmd=" + num_cmd + ", date=" + date + ", prix=" + prix + '}';
    }
   
}
