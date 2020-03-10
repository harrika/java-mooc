
/**
 * caesar ciper 2 * 
 * @author henry kirya 
 */
import edu.duke.*;
import java.io.*;

public class CaesarCipher2 {
    public void eyballDecrypt(String encrypted){
        CaesarCipher cipher = new CaesarCipher();
        for (int k=0; k<26; k++){
            String s = cipher.encrypt(encrypted, k);
            System.out.println(k+":   "+s);
        }    
    } 
    
    public int[] countOccurrences(String message){ 
        //countletters counts occurences of every character in message
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k=0; k<message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1){
                counts[dex] += 1;
            }        
        }
        return counts;
    }
    
    public void teststring(){
        String newc = "";
        String mess = "abcdefqrstuvwxyz!";
        //for (int k=0; k<mess.length(); k++){
            int k = mess.length()-1;
            //System.out.println(mess.charAt(k));
            char ch = mess.charAt(k);
            System.out.println(ch);
            boolean bb = Character.isLetter(ch);
            if (!bb){
                //newc = substring(0,k);
                newc = mess.substring(0, k);
            }
            System.out.println(mess);
            System.out.println("newc: "+newc);            
        //}
    }
    
    
     public int maxIndex(int[] values){
        int mx = 0;
        int pos = 0;
        for (int k=0; k < values.length; k++){
            if (values[k] > mx) {
                mx = values[k]; 
                pos = k;
            }
        }
        return pos;
    }
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countOccurrences(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4; 
        //because 4 is the index of 'e', the most frequent character in the english language            
        if (maxDex < 4) {
            dkey = 26-(4-maxDex);
        }
        return cc.encrypt(encrypted, 26-dkey);        
    }
    
    public int getkey(String encrypted){
        //CaesarCipher cc = new CaesarCipher();
        int[] freqs = countOccurrences(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4; 
        //because 4 is the index of 'e', the most frequent character in the english language            
        if (maxDex < 4) {
            dkey = 26-(4-maxDex);
        }
        //return cc.encrypt(encrypted, 26-dkey);        
        return dkey;        
    }   
     
    public String decrypt2key(String encrypted){
        CaesarCipher cc = new CaesarCipher();        
        StringBuilder enc = new StringBuilder(encrypted);
        StringBuilder enc1 = new StringBuilder();
        StringBuilder enc2 = new StringBuilder();                
        for (int i=0; i < enc.length(); i++){
            char ci = enc.charAt(i);
            if (i%2 == 0){                
                enc1.append(ci);                     
            }else{                
                enc2.append(ci);                                     
            }          
        }            
        int dkey1 = getkey(enc1.toString());
        int dkey2 = getkey(enc2.toString());  
        System.out.print("dkey1: "+dkey1);
        System.out.println(" and dkey2: "+dkey2);
        String decryp2d = cc.encryptTwoKeys(encrypted, 26-dkey1, 26-dkey2);           
        return decryp2d;
    } 
     
    public void testdecrypt2key(){          
        FileResource fr = new FileResource();
        String message = fr.asString();       
        //String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";        
        String xx = decrypt2key(message);
        System.out.println(xx);
    }
    
}//end class
