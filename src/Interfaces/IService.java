/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;
import models.Commande;
import models.Produit;

public interface IService<T> {

    public void ajouter(T p);

    public boolean modifer(T p);

    public List<T> afficher();

    public boolean supprimer(T p);

    public void ajouter(Commande c);

    public void modifer(Commande c, int id);

    public void modifer(Produit p, int id);

    public void ajouter(Produit p);

    public void supprimer(int c);

}
