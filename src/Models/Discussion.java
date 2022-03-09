/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Firas
 */
public class Discussion {
    private int id;
    private String  contenu;

    public Discussion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Discussion(int id, String contenu) {
        this.id = id;
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "Discussion{" + "id=" + id + ", contenu=" + contenu + '}';
    }

    public Discussion(String contenu) {
        this.contenu = contenu;
    }
    
}
