import edu.duke.*;
import java.io.*;

public class CaesarCipher {
    
    private String alphabet;
    private String alphaShifted;
    private int dakey;
    
    public CaesarCipher(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphaShifted = alphabet.substring(key)+alphabet.substring(0,key);    
        dakey = 26-key;
    }
        
    public String encrypt(String input){
        StringBuilder enc = new StringBuilder(input);         
        for (int i=0; i<enc.length(); i++){
            char current = enc.charAt(i);            
            int idx = alphabet.indexOf(current);            
            if (idx != -1){
                char newchar = alphaShifted.charAt(idx);
                enc.setCharAt(i, newchar);                
            }            
        }
        return enc.toString();   
    }
    
    public String decrypt(String input){
        //StringBuilder enc = new StringBuilder(input); 
        CaesarCipher cc = new CaesarCipher(dakey);
        return cc.encrypt(input);
    }
    
}
