/** caesar and other encryption algorithms 
 * @author henry kirya
 * @version 1.00 */
 
import edu.duke.*;
import java.io.*;
//import org.apache.commons.csv.*;
 
public class WordPlay {
    public boolean isVowel(char ch){
        ch = Character.toLowerCase(ch);        
        boolean tt = false;
         if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ){
            tt = true;
         }
         return tt;
    } //end method    
    
    public String replaceVowels(String phase, char ch){
        StringBuilder phrase = new StringBuilder(phase);
        StringBuilder phrase2 = phrase;        
        for (int i=0; i<phrase.length(); i++){
            char ssi = phrase.charAt(i);
            if (isVowel(ssi)) {
                phrase2.setCharAt(i, ch);            
            }else{
                phrase2.setCharAt(i, ssi);           
            }
        }
        return phrase2.toString();
    } //end method    
    
     public String emphasise(String phase, char ch){
        StringBuilder phrase = new StringBuilder(phase);
        StringBuilder phrase2 = phrase;        
        for (int i=0; i<phrase.length(); i++){
            char ssi = phrase.charAt(i);
            //int a = i+1;
            if (ssi == ch) {
                if (i%2 == 0){ 
                    phrase2.setCharAt(i, '*');                    
                } else {
                    phrase2.setCharAt(i, '+');                    
                }            
            }else{
                phrase2.setCharAt(i, ssi);               
            }            
        }
        return phrase2.toString();
    } //end method  
    
    //==================testing =========================
    public void testisVowel(){
        System.out.println(isVowel('E'));
    }
    
    public void testreplaceVowels(){
        System.out.println(replaceVowels("Henry is an Encryption expert", '*'));
    }
    
    public void testemphasise(){
        System.out.println(emphasise("dna ctgaaactga", 'a'));
    }
    
} //end class 
    
