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
        int[] ky = new int[38];
        FileResource fr = new FileResource();
        String encmessage = fr.asString(); 
        ky = vbr.tryKeyLength(encmessage, 38, 'e');                    
        }
        
    public void testbreakVigenere(){
         VigenereBreaker vbr = new VigenereBreaker(); 
         vbr.breakVigenere();         
    }
          
    public void testcountWords(){
        VigenereBreaker vbr = new VigenereBreaker();     
        String messg = "This is a sample sentence with []s, and I, jut going to make it longer now";
        FileResource frdic = new FileResource();
        HashSet<String> dic = vbr.readDictionary(frdic);                
        int cnt = vbr.countWords (messg, dic);        
        System.out.println("English words found:   "+cnt);                 
    }
    
    public void testmostCommonCharIn(){
        VigenereBreaker vbr = new VigenereBreaker();     
        FileResource frdic = new FileResource();
        HashSet<String> dic = vbr.readDictionary(frdic);
        Character commn = vbr.mostCommonCharIn(dic);
        System.out.println("most comon character tested: "+commn); 
    }
    
   
     public void testbreakForAllLangs(){
         VigenereBreaker vbr = new VigenereBreaker();           
         HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
         ArrayList<String> langlist = new ArrayList<String>(); //creating list of languages
         langlist.add("Danish");
         langlist.add("Dutch");
         langlist.add("English");
         langlist.add("French");
         langlist.add("German");
         langlist.add("Italian");
         langlist.add("Portuguese");
         langlist.add("Spanish");
         
         System.out.println("Select encrypted file");  
         FileResource fr = new FileResource();
         String encd = fr.asString();                  
         for (String lang : langlist) {
            System.out.println("select dictionary for language: "+lang);  
            FileResource frdict = new FileResource(); //getting dictionary
            HashSet<String> dict = vbr.readDictionary(frdict);
            languages.put(lang, dict);
        }
         vbr.breakForAllLangs(encd, languages);
        }
    
    
} //main class
