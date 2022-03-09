/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Guide Info
 */
public class Scrimms {


    int id;
    String nom_eq1, nom_eq2,Game;
    public Scrimms() {
    }
    public Scrimms(int id, String nom_eq1, String nom_eq2, String Game) {
        this.id = id;
        this.nom_eq1 = nom_eq1;
        this.nom_eq2 = nom_eq2;
        this.Game = Game;

    }

    public Scrimms(String nom_eq1, String nom_eq2, String Game) {

        this.nom_eq1 = nom_eq1;
        this.nom_eq2 = nom_eq2;
        this.Game = Game;
    }

    


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_eq1() {
        return nom_eq1;
    }

    public void setNom_eq1(String nom_eq1) {
        this.nom_eq1 = nom_eq1;
    }

    public String getNom_eq2() {
        return nom_eq2;
    }

    public void setNom_eq2(String nom_eq2) {
        this.nom_eq2 = nom_eq2;
    }


    public String getGame() {
        return Game;
    }

    public void setGame(String Game) {
        this.Game = Game;
    }
    
    
    @Override
    public String toString() {
        return " TEAM 1 : " + nom_eq1 + "---VERSUS---" + "TEAM 2 : =" + nom_eq2 +  "===>"+ "Game : " + Game ;
    }

    

}

