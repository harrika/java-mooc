import java.util.*;
import edu.duke.*;
//import java.io.*;

public class VigenereBreaker {
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
         StringBuilder messg = new StringBuilder(message); 
         StringBuilder messg2 = new StringBuilder("");         
        for (int k=whichSlice; k < message.length(); k += totalSlices){
            char curr = messg.charAt(k);
            messg2.append(curr);
        }
        return messg2.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        CaesarCracker ccr = new CaesarCracker('e');
        int[] key = new int[klength];       
        for (int i=0; i<klength; i++){
            String slic = sliceString(encrypted, i, klength);                        
            key[i] = ccr.getKey(slic);            
            //System.out.println("key: "+key[i]);    
        }        
        return key;
    }

    public void breakVigenere() {       
       int keylen = 4; 
       int[] dakey = new int[keylen];   
       FileResource fr = new FileResource();
       String encmessg = fr.asString();
       dakey = tryKeyLength(encmessg, keylen, 'e');
       VigenereCipher vcipher = new VigenereCipher(dakey);
       String decmessg = vcipher.decrypt(encmessg);
       System.out.println(decmessg);
    }
    
}
