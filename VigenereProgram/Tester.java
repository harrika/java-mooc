import java.util.*;
import edu.duke.*;
import java.io.*;

public class Tester
{
    public void testCaesarCipher() { 
        CaesarCipher cc = new CaesarCipher(14);
        FileResource fr = new FileResource();
        String cont = fr.asString();        
        String encd = cc.encrypt(cont);
        System.out.println(encd);
        String decd = cc.decrypt(encd);
        System.out.println(decd);
        }
    
    public void testCaesarCrackerEng() { 
        CaesarCracker ccr = new CaesarCracker();
        FileResource fr = new FileResource();
        String enc_message = fr.asString();            
        System.out.println(enc_message);     
        String decd = ccr.decrypt(enc_message);        
        System.out.println(decd);           
    }
    
     public void testCaesarCrackerPot() {         
        CaesarCracker ccr = new CaesarCracker('a');
        FileResource fr = new FileResource();
        String enc_message = fr.asString();            
        System.out.println(enc_message);     
        String decd = ccr.decrypt(enc_message);        
        System.out.println(decd);           
    }
    
     public void testVigenereCipher() {  
        int[] ky = {17,14,12,4};
        VigenereCipher vgr = new VigenereCipher(ky);
        FileResource fr = new FileResource();
        String message = fr.asString(); 
        String encd = vgr.encrypt(message);      
        System.out.println(encd);
        String decd = vgr.decrypt(encd);        
        System.out.println(decd);           
    }
    
    public void testsliceString(){
        VigenereBreaker vbr = new VigenereBreaker();        
        String res = vbr.sliceString("abcdefghijklm", 4, 5);
        System.out.println(res);         
    }
    
    public void testtryKeyLength() {        
        VigenereBreaker vbr = new VigenereBreaker();       
        int[] ky = new int[4];
        FileResource fr = new FileResource();
        String encmessage = fr.asString(); 
        ky = vbr.tryKeyLength(encmessage, 4, 'e');                    
        }
        
    public void testbreakVigenere(){
         VigenereBreaker vbr = new VigenereBreaker(); 
         vbr.breakVigenere();         
    }
    
    
} //main class
