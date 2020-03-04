
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class CaesarCipher {
    
    public String encrypt(String input, int key){
        StringBuilder enc = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabet2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();
        String shifted = alphabet.substring(key)+alphabet.substring(0,key);
        String shifted2 = alphabet2.substring(key)+alphabet2.substring(0,key);
        
        for (int i=0; i<enc.length(); i++){
            char current = enc.charAt(i);
            if (Character.isUpperCase(current)) {
                int idx = alphabet.indexOf(current);            
                if (idx != -1){
                    char newchar = shifted.charAt(idx);
                    enc.setCharAt(i, newchar);                
                }
            }else{
                int idx = alphabet2.indexOf(current);            
                if (idx != -1){
                    char newchar = shifted2.charAt(idx);
                    enc.setCharAt(i, newchar);                
                }            
            }            
        } //end forloop
        return enc.toString();        
    }
    
     public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder enc = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabet2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();        
        String shifted = alphabet.substring(key1)+alphabet.substring(0,key1);
        String shifted2 = alphabet2.substring(key1)+alphabet2.substring(0,key1);        
        String shiftedb = alphabet.substring(key2)+alphabet.substring(0,key2);
        String shiftedb2 = alphabet2.substring(key2)+alphabet2.substring(0,key2);
        
        for (int i=0; i<enc.length(); i++){
            char current = enc.charAt(i);
            char newchar;
            if (Character.isUpperCase(current)) {
                int idx = alphabet.indexOf(current);            
                
                if (idx != -1){                    
                    if (i%2 == 0){
                        newchar = shifted.charAt(idx);
                    }else{
                        newchar = shiftedb.charAt(idx);
                    }
                    enc.setCharAt(i, newchar);                
                }
            }else{
                int idx = alphabet2.indexOf(current);            
                if (idx != -1){
                    if (i%2 == 0){
                        newchar = shifted2.charAt(idx);
                    }else{
                        newchar = shiftedb2.charAt(idx);
                    }                    
                    enc.setCharAt(i, newchar);                
                }            
            }            
        } //end forloop
        return enc.toString();        
    }
    
    
    //==testing =======================================
    public void testCaesar(){
        int ke = 15;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = encrypt(message, ke);        
        System.out.println("message:  "+message);
        //System.out.println("key is: "+ ke);
        System.out.println("encrypted message: "+encrypted);
        
    }
    
     public void testencryptTwoKeys(){
        int key1 = 14;
        int key2 = 24;  
        int akey1 = 26-key1;
        int akey2 = 26-key2;
        
        //FileResource fr = new FileResource();
        String message = "Hfs cpwewloj loks cd Hoto kyg Cyy";
        //String message = " Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String enc = encryptTwoKeys(message, akey1, akey2);        
        System.out.println("message:  "+message);     
        System.out.println("dencrypted message: "+enc);
        
    }
    
}//end class

