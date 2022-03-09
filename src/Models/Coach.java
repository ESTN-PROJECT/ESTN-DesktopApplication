/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author HP
 */
public class Coach extends Utilisateur {

    private static Coach instance;

    String rank, Description;
    String cout;

    public Coach() {

    }

    public Coach(String rank, String Description, String cout) {
        this.rank = rank;
        this.Description = Description;
        this.cout = cout;
    }

    public Coach( String username, Date date_naiss, String ville, String password, String rank, String Description, String cout, String role,String email) {

        super(username, date_naiss, ville, password, role, email);
        this.rank = rank;
        this.Description = Description;
        this.cout = cout;

    }

    /*
    public Coach(String rank, String Description, Float cout, String username, String date_naiss, String ville, String code_postal, String role) {
        super(username, date_naiss, ville, code_postal, role);
        this.rank = rank;
        this.Description = Description;
        this.cout = cout;
    }
     */
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getCout() {
        return cout;
    }

    public void setCout(String cout) {
        this.cout = cout;
    }

    public static Coach getInstance(String username, Date date_naiss, String ville, String password, String rank, String description, String cout, String role,String email) {
        if (instance == null) {
            instance = new Coach(username, date_naiss, ville, password, rank, description, cout, role, email);
        }
        return instance;
    }
}
