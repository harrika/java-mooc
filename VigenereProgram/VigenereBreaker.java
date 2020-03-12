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
        }        
        return key;
    }

    public void breakVigenere() {              
       FileResource frmessg = new FileResource(); //getting encrypted message
       String encmessg = frmessg.asString();
       FileResource frdict = new FileResource(); //getting dictionary
       HashSet<String> dicset = readDictionary(frdict);             
       String res = breakForLanguage(encmessg, dicset);    
       System.out.println(res);         
    }
       
    public HashSet<String> readDictionary(FileResource fr) { 
        HashSet<String> set = new HashSet<String>();
        for (String line : fr.lines()) {
            set.add(line.toLowerCase());
        }
        return set;
    }
    
    public int countWords (String message, HashSet<String> dictionary) {     
        int count = 0;
        String[] words = message.split("\\W+");
        for (String wrd : words) {
            wrd = wrd.toLowerCase();
            if (dictionary.contains(wrd)){
                //System.out.println(wrd);                
                count += 1;
            }            
        }
    return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int mx = 0;
        String bestdecrypt = "";         
        int[] dkey;        
        char ee = mostCommonCharIn(dictionary);         
        for(int i=1; i<101; i++){
            dkey = tryKeyLength(encrypted, i, ee);
            VigenereCipher vc = new VigenereCipher(dkey);
            String decdmessg = vc.decrypt(encrypted);
            int cnt = countWords(decdmessg, dictionary);
            if (cnt>mx){
                mx = cnt;
                bestdecrypt = decdmessg;
                System.out.println("current key: "+Arrays.toString(dkey));                
                System.out.println("key array length : "+dkey.length);                
            }
        }
        System.out.println("max valid word count:  "+mx);   
                
        return bestdecrypt;
    }
    
    public String breakForLanguage38(String encrypted, HashSet<String> dictionary) {
        int mx = 0;
        String bestdecrypt = "";         
        int[] dkey = tryKeyLength(encrypted, 38, 'e');
        VigenereCipher vc = new VigenereCipher(dkey);
        String decdmessg = vc.decrypt(encrypted);
        int cnt = countWords(decdmessg, dictionary);
        System.out.println("current key: "+Arrays.toString(dkey));                
        System.out.println("key array length : "+dkey.length);                
        System.out.println("valid word count:  "+cnt);                   
        return bestdecrypt;
    }
    
    public Character mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> dicmap = new HashMap<Character, Integer>();
        int mx = 0;
        Character commoncr = ' '; 
         for (String wrd : dictionary) {            
             StringBuilder sbcurren = new StringBuilder(wrd);
             for (int i=0; i<sbcurren.length(); i++){            
             char curr = sbcurren.charAt(i) ;
             if(dicmap.containsKey(curr)){
                 int val = dicmap.get(curr);
                 dicmap.put(curr, val+1);                 
             }else{
                 dicmap.put(curr, 1);                                                  
             }            
            }
        }        
        for (Character cr : dicmap.keySet()) {
            int dd = dicmap.get(cr);            
            if (dd>mx){
                mx = dd;  
                commoncr = cr;
            }
        }        
        return commoncr;
    } 
        
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {   
        HashMap<String, Integer> langCount = new HashMap<String, Integer>();
        int maxcount = 0;
        String maxlang = "";
        String maxdec = "";
        for (String lang : languages.keySet()) {     
            String bestdec = breakForLanguage(encrypted, languages.get(lang));
            System.out.println("decryption for "+lang+" completed");            
            int count  = countWords(bestdec, languages.get(lang)); 
            System.out.println("counting words for "+lang+" done");            
            if (count > maxcount){
                maxcount = count;
                maxlang = lang;
                maxdec = bestdec;
            }
        }
        System.out.println("decrypted message: "+maxdec);
        System.out.println("language identified: "+maxlang);
    }
    
}// class ends
