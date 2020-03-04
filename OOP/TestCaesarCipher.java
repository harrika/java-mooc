import edu.duke.*;
import java.io.*;

public class TestCaesarCipher {
     
        public void simpleTests(){
            //FileResource fr = new FileResource();
            //String message = fr.asString();
            String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
            CaesarCipher cc = new CaesarCipher(15);
            String encd = cc.encrypt(message);
            System.out.println("encrypted:  "+encd);
            // String decd = cc.decrypt(encd);
            // System.out.println("decrypted: "+decd);
            // String broknd = breakCaesarCipher(encd);
            // System.out.println("decd using breakCeasar methos: "+broknd);            
        }
        
        public String breakCaesarCipher(String input){   
            int ke = getkey(input);
            CaesarCipher cc = new CaesarCipher(ke);
            String decdt = cc.decrypt(input);            
            return decdt;
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
        
        public int getkey(String encrypted){        
            int[] freqs = countOccurrences(encrypted);
            int maxDex = maxIndex(freqs);
            int dkey = maxDex - 4;         
            if (maxDex < 4) {
                dkey = 26-(4-maxDex);
            }        
            return dkey;        
        }   
    } //end class