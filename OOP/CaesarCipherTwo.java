import edu.duke.*;
import java.io.*;

public class CaesarCipherTwo {
    
    //private int dakey;
    private String alphabet;
    private String shiftedAlpha1;
    private String shiftedAlpha2;
    private int ke1;
    private int ke2;    
    
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlpha1 = alphabet.substring(key1)+alphabet.substring(0,key1);    
        shiftedAlpha2 = alphabet.substring(key2)+alphabet.substring(0,key2);   
        ke1 = key1;
        ke2 = key2;
        //dakey = 26-key;
    }
    
    public String encrypt(String input){
         StringBuilder enc = new StringBuilder(input);         
         for (int i=0; i<enc.length(); i++){
             char currchar = enc.charAt(i);
             char newchar;
             int idx = alphabet.indexOf(currchar);                            
                if (idx != -1){                    
                    if (i%2 == 0){
                        newchar = shiftedAlpha1.charAt(idx);
                    }else{
                        newchar = shiftedAlpha2.charAt(idx);
                    }
                    enc.setCharAt(i, newchar);                
                }
            }
         return enc.toString();   
    }
    
    public String decrypt(String input){
        //StringBuilder enc = new StringBuilder(input); 
        CaesarCipherTwo cc2 = new CaesarCipherTwo(26-ke1,26-ke2);
        return cc2.encrypt(input);
    }
    
    public void testencrypt(){
        String mm = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        int k1 = 21;
        int k2 = 8;
        CaesarCipherTwo cc2test = new CaesarCipherTwo(k1, k2);
        String encd = cc2test.encrypt(mm);
        System.out.println();        
    }
    
    
} //end class
        