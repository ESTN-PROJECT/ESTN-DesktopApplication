/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

/**
 *
 * @author Firas
 */
public class Forum {
    private int id;
    private String sujet;

    public Forum() {
    }

    public Forum(int id, String sujet) {
        this.id = id;
        this.sujet = sujet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    @Override
    public String toString() {
        return "Forum{" + "sujet=" + sujet + '}';
    }

    public Forum(String sujet) {
        this.sujet = sujet;
    }
    
}
