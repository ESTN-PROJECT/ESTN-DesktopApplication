/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Database.MyDB;
import Models.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import IServices.IServices;
import Models.Coach;
import Models.Member;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;
import java.sql.Date;
import java.util.Comparator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author HP
 */
public class UtilisateurController implements IServices {

    private Connection cnx;

    public UtilisateurController() {
        cnx = MyDB.getInstance().getcnx();
    }

    @Override
    public void SignUpMember(Member u) {
        String query = "INSERT INTO `utilisateur`(`username`, `date_naiss`, `ville`, `password`, `rank`, `description`, `cout`, `role` ,`email`) VALUES ('" + u.getUsername() + "','" + u.getDate_naiss() + "','" + u.getVille() + "','" + u.getPassword() + "','" + u.getRank() + "','" + "0" + "','" + "0" + "','" + "member" + "','" + u.getEmail() + "')";

        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void SignUpCoach(Coach u) {
        String query = "INSERT INTO `utilisateur`(`username`, `date_naiss`, `ville`, `password`, `rank`, `description`, `cout`, `role` ,`email`) VALUES ('" + u.getUsername() + "','" + u.getDate_naiss() + "','" + u.getVille() + "','" + u.getPassword() + "','" + u.getRank() + "','" + u.getDescription() + "','" + u.getCout() + "','" + "coach" + "','" + u.getEmail() + "')";

        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Utilisateur> afficher() {
        List<Utilisateur> utilisateurs = new ArrayList();

        try {
            Statement stm = cnx.createStatement();

            String querry = "SELECT * from utilisateur  WHERE `IsDeleted`=" + 0;
            ResultSet rs = stm.executeQuery(querry);

            while (rs.next()) {
                Utilisateur p = new Utilisateur();
                p.setId(rs.getInt(1));
                p.setUsername(rs.getString(2));
                p.setDate_naiss(rs.getDate(3));
                p.setVille(rs.getString(4));
                p.setPassword(rs.getString(5));
                p.setRole(rs.getString(9));
                p.setEmail(rs.getString(11));

                utilisateurs.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return utilisateurs;
    }

    @Override
    public void DeleteUser(int t) {
        PreparedStatement ps;

        String query = "UPDATE `utilisateur` SET `IsDeleted`=1 WHERE `ID`= " + t;

        try {
            ps = cnx.prepareStatement(query);

            ps.execute();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    @Override
    public void UpdateMember(Member u, int id) {
        PreparedStatement ps;
        String query = "UPDATE `utilisateur` SET `username`=?,`date_naiss`=?,`ville`=?,`password`=?,`rank`=?,`description`=0,`cout`=0, `email`=? WHERE `ID`= " + id;
        try {

            ps = cnx.prepareStatement(query);

            ps.setString(1, u.getUsername());
            ps.setDate(2, (Date) u.getDate_naiss());
            ps.setString(3, u.getVille());
            ps.setString(4, u.getPassword());
            ps.setString(5, u.getRank());
            ps.setString(6, u.getEmail());

            ps.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void UpdateCoach(Coach u, int id) {
        PreparedStatement ps;
        String query = "UPDATE `utilisateur` SET `username`=?,`date_naiss`=?,`ville`=?,`password`=?,`rank`=?,`description`=?,`cout`=?, `email`=? WHERE `ID`= " + id;
        try {
//        String query = "UPDATE `utilisateur` SET `username`='" + u.getUsername() + "',`date_naiss`='" + u.getDate_naiss() + "',`ville`='" + u.getVille() + "',`code_postal`='" + u.getCode_postal() + "',`rank`='" + u.getRank() + "',`description`='" + u.getDescription() + "',`cout`='" + u.getCout() + "',`role`='" + u.getRole() + "' WHERE `ID`=?";

            ps = cnx.prepareStatement(query);

            ps.setString(1, u.getUsername());
            ps.setDate(2, (Date) u.getDate_naiss());
            ps.setString(3, u.getVille());
            ps.setString(4, u.getPassword());
            ps.setString(5, u.getRank());
            ps.setString(6, u.getDescription());
            ps.setString(7, u.getCout());
            ps.setString(8, u.getEmail());

            ps.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void FindUser(int id) {
        PreparedStatement ps;
        String query = "SELECT * FROM `utilisateur` WHERE `id`=? ";

        try {
            Utilisateur p = new Utilisateur();

            ps = cnx.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("User Found");

            } else {
                System.out.println("User Not Found");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //features(search, filter,..) -> api stream

    public boolean Login(String username, String password) {

        PreparedStatement ps;
        String query = "select * from `utilisateur` where `username`= '" + username + "' and `password` = '" + password + "' ";

        try {
            ps = cnx.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("connected");
                return true;
            } else {
                System.out.println("You should try again!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public List<Utilisateur> RechercheById(int id) {
        return afficher().stream().filter(a -> a.getId() == id).collect(Collectors.toList());

    }

    public List<Utilisateur> RechercheByUsername(String username) {

        return afficher().stream().filter(a -> a.getUsername().equals(username)).collect(Collectors.toList());

    }

    public List<Utilisateur> TriParId() {
        List<Utilisateur> utilisateurs = afficher();
        List<Utilisateur> resultat = utilisateurs.stream().sorted(Comparator.comparingInt(Utilisateur::getId)).collect(Collectors.toList());
        return resultat;

    }

    public List<Utilisateur> TriParUsername() {
        List<Utilisateur> utilisateurs = afficher();
        List<Utilisateur> resultat = utilisateurs.stream().sorted(Comparator.comparing(Utilisateur::getUsername)).collect(Collectors.toList());
        return resultat;

    }

    public boolean VerifyIfMember(int id) {
        List<Utilisateur> utilisateurs = new ArrayList();
        Utilisateur p = new Utilisateur();

        try {
            Statement stm = cnx.createStatement();

            String querry = "SELECT * from utilisateur  WHERE `role`= '" + "member" + "' AND `id`='" + id + "'";
            ResultSet rs = stm.executeQuery(querry);

            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setUsername(rs.getString(2));
                p.setDate_naiss(rs.getDate(3));
                p.setVille(rs.getString(4));
                p.setPassword(rs.getString(5));
                p.setRole(rs.getString(9));
                p.setEmail(rs.getString(11));

                utilisateurs.add(p);
                return true;

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;
    }

    public void log() {
        //avec restfb

        String REDIRECT_URI = "https://www.facebook.com/";
        String MY_ACCESS_TOKEN = "EAAXMICMfLyIBAN2PdZAb0Rvf6ZBVG4gvhmTd96r3DuoZAs9ZAgNJVAUf84i0i9a6lrZAaZCxZARthL7PIKJ1TVHWPEbCcZCEZA4mA3DZC5K9w7jU4duZANzeRoGna93iRn0zoHhIO9F59Jn9Ye3A7uvWpmjZCVlXrOscRSi37gEa7gaIbBGA5KjA49vRC0IRcGj30ZA9ZBCSwHtlqnDQEJUee4snNvZBvAZB17RLPxnFz1fQDirt0UdbZBTqHFRok";
        String MY_APP_ID = "1631813283819298";
        String MY_APP_SECRET = "b3d3a1abe992f13f77dfdf86dafe5312";

        FacebookClient facebookClient = new DefaultFacebookClient(MY_ACCESS_TOKEN, Version.LATEST);
        User me = facebookClient.fetchObject("me", User.class);
        System.out.println(me.getName());

        /* avec selinium:
        System.setProperty("WebDriver.chrome.driver", "C:/Users/HP/Downloads/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");

        driver.manage().window().maximize();

        driver.findElement(By.id("email")).sendKeys("26399049");

        driver.findElement(By.id("pass")).sendKeys("cligaqw159295540nn45");

        driver.findElement(By.id("u_0_b")).click(); */
    }

    public String selectEmail(String username) {

        try {
            Statement stm = cnx.createStatement();
            String querry = "SELECT * from `utilisateur` where username= '" + username + "'";

            ResultSet rs = stm.executeQuery(querry);
            while (rs.next()) {

                return rs.getString(11);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "this username is not related to any account in estn";
    }

    public String selectPassword(String username) {

        try {
            Statement stm = cnx.createStatement();
            String querry = "SELECT * from `utilisateur` where username= '" + username + "'";

            ResultSet rs = stm.executeQuery(querry);
            while (rs.next()) {

                return rs.getString(5);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "this username is not related to any account in estn";
    }

    public Boolean loginJdida(String u, String p) throws SQLException {
        String req = "select * from `utilisateur` where `username`= '" + u + "' and `password` = '" + p + "' ";

        Utilisateur user = new Utilisateur();
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            System.out.println(rs);
            if (rs != null) {
                while (rs.next()) {

                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setDate_naiss(rs.getDate("date_naiss"));
                    user.setVille(rs.getString("ville"));
                    user.setPassword(rs.getString("password"));

                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return false;
    }

    public Utilisateur GetCurrentUser(String u, String p) throws SQLException {
        Utilisateur user = new Utilisateur();
        String req = "select * from `utilisateur` where `username`= '" + u + "' and `password` = '" + p + "' ";

        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            if (rs != null) {
                while (rs.next()) {
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setDate_naiss(rs.getDate("date_naiss"));
                    user.setVille(rs.getString("ville"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));

                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return user;
    }

    public List<Coach> afficherCoachs() {
        List<Coach> coachs = new ArrayList();

        try {
            Statement stm = cnx.createStatement();

            String querry = "SELECT * from utilisateur  WHERE `role`='" + "coach" + "'";
            ResultSet rs = stm.executeQuery(querry);

            while (rs.next()) {
                Coach p = new Coach();
                p.setId(rs.getInt(1));
                p.setUsername(rs.getString(2));
                p.setDate_naiss(rs.getDate(3));
                p.setVille(rs.getString(4));
                p.setPassword(rs.getString(5));
                p.setRank(rs.getString(6));
                p.setDescription(rs.getString(7));
                p.setCout(rs.getString(8));

                p.setRole(rs.getString(9));
                p.setEmail(rs.getString(11));

                coachs.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return coachs;
    }

}
