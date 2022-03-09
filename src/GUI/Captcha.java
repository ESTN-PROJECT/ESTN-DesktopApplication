/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Random;

/**
 *
 * @author HP
 */
public class Captcha {

    public String getCaptcha() {

        char[] data = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
       char index[]= new char[6];
       Random r = new Random();
       int i =0;
       for(i=0; i < (index.length); i++){
           int ran=r.nextInt(data.length);
           index[i]=data[ran];
           
       }
        
        return new String(index);
    }
  
   /* 
    public static void main(String[] Args){
    Captcha c = new Captcha();
        System.err.println(c.getCaptcha());
    
    }
    */
}
