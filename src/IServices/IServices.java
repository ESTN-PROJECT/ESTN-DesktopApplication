/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Models.Coach;
import Models.Member;
import Models.Utilisateur;
import java.util.List;

/**
 *
 * @author HP
 * @param <T>
 */
public interface IServices<T> {

    public void SignUpMember(Member u);

    public void SignUpCoach(Coach u);

    public List<T> afficher();

    public void UpdateMember(Member u, int id);

    public void UpdateCoach(Coach u, int id);

//modifier profile
    public void DeleteUser(int t);
    //to do 
//login (gestion des roles )
}
