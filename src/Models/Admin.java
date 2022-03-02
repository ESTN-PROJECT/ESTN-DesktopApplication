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
public class Admin extends Utilisateur {

    private static Admin instance;

    public Admin() {
    }

    public Admin(String username, Date date_naiss, String ville, String code_postal, String role) {
        super(username, date_naiss, ville, code_postal, role);
    }

    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }
}
