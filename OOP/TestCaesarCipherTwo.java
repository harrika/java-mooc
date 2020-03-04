import edu.duke.*;
import java.io.*;

public class TestCaesarCipherTwo {    
    //private String alphabet;
    //private String shiftedAlpha1;
    //private String shiftedAlpha2;
    
    // public TestCaesarCipherTwo(int key1, int key2){
        // //alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // //shiftedAlpha1 = alphabet.substring(key1)+alphabet.substring(0,key1);    
        // //shiftedAlpha2 = alphabet.substring(key2)+alphabet.substring(0,key2);    
        // //dakey = 26-key;        
    // }
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        String message = fr.asString();   
        CaesarCipherTwo cc2 = new CaesarCipherTwo(17,3);
        String encd = cc2.encrypt(message);
        System.out.println("encrypted string:  "+encd);
        
        //==============decrypting=========================         
        String decd = cc2.decrypt(encd);
        System.out.println("decrypting message: "+decd);
        String brokn = breakCaesarCipher(encd);
        System.out.println("using broken: "+brokn);        
    }    
     
    public String breakCaesarCipher(String input){
        StringBuilder enc = new StringBuilder(input);
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
        CaesarCipherTwo cctwo = new CaesarCipherTwo(26-dkey1,26-dkey2);
        return cctwo.encrypt(input);
    }
    
    private int[] countOccurrences(String message){ 
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
    
    private int maxIndex(int[] values){
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
    
    private int getkey(String encrypted){        
            int[] freqs = countOccurrences(encrypted);
            int maxDex = maxIndex(freqs);
            int dkey = maxDex - 4;         
            if (maxDex < 4) {
                dkey = 26-(4-maxDex);
            }        
            return dkey;        
        }   

}
