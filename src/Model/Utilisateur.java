/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author AZIZ
 */
public class Utilisateur {
    private int id_user;
    private int isdeleted;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(int isdeleted) {
        this.isdeleted = isdeleted;
    }
    

    
    public Utilisateur(int id_user){
        this.id_user = id_user;
    }
    public Utilisateur(){
        
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id_user=" + id_user + '}';
    }
    
    
    
    
    
}
