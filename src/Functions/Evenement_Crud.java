/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;

import Model.Evenement;
import java.util.List;

/**
 *
 * @author AZIZ
 */
public interface Evenement_Crud <T> {
     public List<Evenement> afficher();
    
     public void Ajouter_Evenement(Evenement e);
      public void Modifer_Evenement(Evenement e , int id_event);
     public void Supprimer_Evenement(int e);
     public List<Evenement> Recherche_Evenement(String titre_event);
     public List<Evenement> Trie_Evenement();
     public List<Evenement> TriParId();
     public List<Evenement> TriParTitre();
    public List<Evenement> RechercheByTitleEvent(String titre_event); 
}
