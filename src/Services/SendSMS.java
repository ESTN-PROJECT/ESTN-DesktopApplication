/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import GUI.EquipeFrontController;
import Models.Equipe;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SendSMS {
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = System.getenv("AC18acd991efe08e7ffb2fcd3059bfacb2");
    public static final String AUTH_TOKEN = System.getenv("6777c1a55cfc4eb944cc0712d3ab3311");

    public static void SendSMS (Equipe E) {
        Twilio.init("AC18acd991efe08e7ffb2fcd3059bfacb2", "6777c1a55cfc4eb944cc0712d3ab3311");
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21629837831"),
                new com.twilio.type.PhoneNumber("+17152604498"),
                "Your team has been created succefully :    Team name: " + E.getNom_equipe() + " Team leader: " + E.getTeam_leader_ign() + " Player 2: " + E.getP2()+ " Player 3: " + E.getP3()+ " Player 4: " + E.getP4()+ " Player 5: " + E.getP5()+ "  Game : " + E.getJeu()).create();

        System.out.println(message.getSid());
    }
}

