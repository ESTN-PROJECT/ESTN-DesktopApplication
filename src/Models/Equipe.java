/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Guide Info
 */
public class Equipe {


    int id;
    String nom_equipe,jeu,team_leader_ign,p2,p3,p4,p5;
    public Equipe() {
    }
    public Equipe(int id, String nom_equipe, String team_leader_ign,  String jeu, String p2, String p3, String p4, String p5) {
        this.id = id;
        this.nom_equipe = nom_equipe;
        this.team_leader_ign = team_leader_ign; 
        this.p2 = p2; 
        this.p3 = p3; 
        this.p4 = p4; 
        this.p5 = p5; 
        this.jeu = jeu;
       
        
    }

    public Equipe(String nom_equipe, String team_leader_ign,  String jeu, String p2, String p3, String p4, String p5) {
        
        this.nom_equipe = nom_equipe;
        this.team_leader_ign = team_leader_ign;
        this.p2 = p2; 
        this.p3 = p3; 
        this.p4 = p4; 
        this.p5 = p5;
        this.jeu = jeu;
    }

    


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    
    
    public String getNom_equipe() {
        return nom_equipe;
    }
    public void setNom_equipe(String nom_equipe) {
        this.nom_equipe = nom_equipe;
    }
    
    
    
    
    public String getTeam_leader_ign(){
        return team_leader_ign;
    }
    public void setTeam_leader_ign(String team_leader_ign){
        this.team_leader_ign = team_leader_ign;
    }
    
    public String getP2() {
        return p2;
    }
    public void setP2(String p2) {
        this.p2 = p2;
        
        
    }public String getP3() {
        return p3;
    }
    public void setP3(String p3) {
        this.p3 = p3;
        
        
    }public String getP4() {
        return p4;
    }
    public void setP4(String p4) {
        this.p4 = p4;
    }public String getP5() {
        return p5;
    }
    public void setP5(String p5) {
        this.p5 = p5;
    }

    
    public String getJeu() {
        return jeu;
    }
    public void setJeu(String jeu) {
        this.jeu = jeu;
    }


    
    
    

    @Override
    public String toString(){
        return "Team name : " + nom_equipe +"----" + " Team leader : "+team_leader_ign+"----" +  " Game : " + jeu +"----" +  " Player 2 : " + p2+"----" +  " Player 3 : " + p3+ "----" + " Player 4 : " + p4+ "----" + " Player 5 : " + p5 ;
    }

}

