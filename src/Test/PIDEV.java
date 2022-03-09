/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Services.UtilisateurController;
import Models.Coach;
import Models.Member;
import Models.Utilisateur;
import com.restfb.DefaultFacebookClient;
import com.restfb.DefaultWebRequestor;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.WebRequestor;
import com.restfb.types.User;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;


/**
 *
 * @author HP
 */
public class PIDEV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        UtilisateurController uc = new UtilisateurController();

        //Utilisateur a = Utilisateur.getInstance();
        //  System.out.println(uc.afficher().toString());
        //uc.SignUpCoach(Coach.getInstance("taw", "10-10-1999", "yalellee", "5500", "plat", "hehehe", "50.2", " "));
        // Member m1 = Member.getInstance("tryrty", "30-10-2008", "mahddfia", "565500", "plfgat", " ");
        // uc.SignUpMember(m1);
        //uc.UpdateCoach(Coach.getInstance("koko", "10-12-2021", "yalellee", "5500", "plat", "hehehe", 50.2f, " "), 28);
        //uc.UpdateMember(Member.getInstance("la", "30-10-2011", "mahddfia", "565500", "plfgat", " "), 18);
        //uc.DeleteUser(28);
        //uc.FindUser(25);
        // uc.Login("koko", "5500");
        //System.out.println(uc.TriParId());
        //System.out.println(uc.TriParUsername());
        //System.out.println(uc.RechercheById(28));
        //System.out.println(uc.RechercheByUsername("koko"));
        //  String accessToken = "EAAN4HbFyepkBAG2nJ8EJek7Ry3eEllZCT9VxqDrKyn9LPHnkXK18GrIhQ4bxnKYR7bwzko3iuZCvR1Boi3EUZBmhzVyEPOcdK0gd7310nFVe6AK3FKDZB493nJ5E2jJHbHTps8E2TcgPPGE91PwtZCUV4ay47LmkwT7Jwrxb0rvcPS0txlkuZAmlQUj244x45LuymzVH52lpdqYZAKZAbfTd6Br4jn1BgM9NXZCFHopaL4aaw1d2VhFbO";
        // FacebookClient fbclient = new DefaultFacebookClient();

        /*
    private FacebookClient.AccessToken getFacebookUserToken(String code, String redirectUrl) throws IOException {
        String appId = "YOUR_APP_ID";
        String secretKey = "YOUR_SECRET_KEY";

        WebRequestor wr = new DefaultWebRequestor();
        WebRequestor.Response accessTokenResponse = wr.executeGet(
                "https://graph.facebook.com/oauth/access_token?client_id=" + appId + "&redirect_uri=" + redirectUrl
                + "&client_secret=" + secretKey + "&code=" + code);

        return DefaultFacebookClient.AccessToken.fromQueryString(accessTokenResponse.getBody());
    }

    uc.log (); */ /*
        String REDIRECT_URI = "https://www.facebook.com/";
        String MY_ACCESS_TOKEN = "EAAXMICMfLyIBAEKZBPZCZBTcOZCCL1aYUOLlIZAcmdX89Cv35yy6yFNesK53ymH3V7m6yvr7Uy1WIaZB1nZBZBZAU1Rh9YG7ZB1Ya447DHTvlpmIUO7ZAm6h4qJY2do7aLB9RogUY9IZC1ZAA58ZAGNaAK4jmA8RF2gBIWgegHYKWIQRCf914h4bL0jwFrMRKJZCVVrXqsqvFdvz6ornVjtlvUd48oV7bi66UBy8SQvdioBDeZAE64IP7lAzKsri";
        String MY_APP_ID = "1631813283819298";
        String MY_APP_SECRET = "b3d3a1abe992f13f77dfdf86dafe5312";

        FacebookClient facebookClient = new DefaultFacebookClient(MY_ACCESS_TOKEN, Version.LATEST);
        User me = facebookClient.fetchObject("me", User.class);
        String name = me.getName();
        //String bd = me.getFirstName();
               // String name = me.getBirthday();

        System.out.println(name);
         */

        //  System.out.println(uc.selectEmail("ok"));
        //System.out.println(uc.selectPassword("ok"));
       /* if (uc.loginJdida("jj", "2955") == true) {
            System.out.println("connected");

        }*/
       // System.out.println(uc.GetCurrentUser("moez", "0000"));
        System.out.println(uc.afficherCoachs());
    }
}
