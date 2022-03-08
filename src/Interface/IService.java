/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Model.Entrainement;
import Model.Jeu;
import Model.Session;
import java.util.List;

/**
 *
 * @author bilel
 */
    
public interface IService<T> {

    public void ajouter(Jeu j);
    public List<T> afficher();
    public void modifer(Jeu j,int id);
    public void supprimer(int t);

    
    public void ajouter(Session s);
    public void modifer(Session s,int id);
    
    
    public void ajouter(Entrainement e);
        public void modifer(Entrainement e,int id);

    
}
