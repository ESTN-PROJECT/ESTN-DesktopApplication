/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Models.Participation;
import java.util.List;

/**
 *
 * @author AZIZ
 */
public interface Participation_Crud <T> {
    public List <Participation> afficher_participation();
    public void Inscrire_Participation(Participation p);
    public void Modifier_Participation(Participation p, int id_particiaption);
    public void Supprimer_Participation(int p);
    
    
}
