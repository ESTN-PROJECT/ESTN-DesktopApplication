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
public class Member extends Utilisateur {

    private static Member instance;

    String rank;

    private Member() {
    }

    private Member(String username, Date date_naiss, String ville, String password, String rank, String role) {

        super(username, date_naiss, ville, password, role);
        this.rank = rank;

    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public static Member getInstance(String username, Date date_naiss, String ville, String password, String rank, String role) {
        if (instance == null) {
            instance = new Member(username, date_naiss, ville, password, rank, role);
        }
        return instance;
    }
}
