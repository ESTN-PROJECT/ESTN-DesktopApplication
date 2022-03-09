/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Database.MyDB;
import IServices.Participation_Crud;
import static Services.Evenement_Controller.cnx;
import Models.Evenement;
import Models.Participation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AZIZ
 */
public class Participation_Controller implements Participation_Crud {

    static java.sql.Connection cnx = MyDB.getInstance().getcnx();

    /**
     *
     * @return
     */
    Participation p = new Participation();
    Evenement e = new Evenement();

    @Override
    public List<Participation> afficher_participation() {

        List<Participation> participations = new ArrayList<>();
        try {
            String querydisplay = " SELECT * FROM `participation` where `isdeleted`= " + 0;

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(querydisplay);

            while (rs.next()) {

                e.setId_event(rs.getInt(1));
                //u.setId_user(rs.getInt(2));
                p.setDate_participation(rs.getDate(3));
                p.setIsdeteled(rs.getInt(4));

                participations.add(p);
            }

        } catch (SQLException ex) {
        }
        return participations;

    }

    @Override
    public void Inscrire_Participation(Participation p) {
        String query = "INSERT INTO `participation`( `name_user`, `lastname_user`, `date_participation`,`titre_event`) VALUES ('" + p.getNom_part() + "','" + p.getPrenom_part() + "','" + p.getDate_participation() + "','" + p.getTitre_event() + "')";

        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Participation_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Modifier_Participation(Participation p, int id_user) {

        //String query = "UPDATE `participation` SET `date_inscription_participateur`=? WHERE `id_participation`= " + id_particiaption;
        PreparedStatement ps;
        String query = "UPDATE `participation` SET `date_participation`=? WHERE `id_user`= " + id_user;
        try {

            ps = cnx.prepareStatement(query);

            ps.setDate(1, p.getDate_participation());

            ps.execute();
            System.out.println("Done!");

        } catch (SQLException ex) {
        }
    }

    @Override
    public void Supprimer_Participation(int user) {
        PreparedStatement ps;

        String query = "update 'participation' set 'isdeleted' = 1 where 'id_user'= " + user;

        try {

            ps = cnx.prepareStatement(query);
            ps.execute();

            System.out.println("Participation deleted");
        } catch (SQLException ex) {
            System.out.println(user);
        }
    }

}
