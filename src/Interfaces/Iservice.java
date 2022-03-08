/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Models.Scrimms;
import java.util.List;

/**
 *
 * @author Guide Info
 * @param <T>
 */
public interface Iservice<T> {

    public void ajouter(T s);

    public boolean modifer(T s);

    public List<T> afficher();

    public boolean supprimer(T s);

    public void ajouter(Scrimms s);

    public void modifer(Scrimms s, int id);


}
