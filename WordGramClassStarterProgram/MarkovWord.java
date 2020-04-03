//kirya Henry

import java.util.*;

public class MarkovWord  implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int norder) {
        myRandom = new Random();
        myOrder = norder;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
     public void testIndexOf() {
         String aa = "this is just a test yes this is a simple test";
         String[] ss = aa.split("\\s+");  
         WordGram wgram1 = new WordGram(ss, 0, 2); 
         int a1 = indexOf(ss, wgram1, 0);
         
         WordGram wgram2 = new WordGram(ss, 4, 2); 
         int a2 = indexOf(ss, wgram2, 0);         
         
         System.out.println("wordgram index found: "+a1);
     }
    
    public int indexOf(String[] words, WordGram target, int start) {
        int len = target.length();
        int wlen = words.length;
        //make wordgram out of first len words
        WordGram wgram = new WordGram(words, start, len);                
        for(int k=start; k < words.length; k++){ 
            if (k+len > wlen-1){
                return -1;
            }
            if (wgram.equals(target)){
                return k;
            }
            String kword = words[k+len];
            wgram = wgram.shiftAdd(kword);
        }
        return -1;
    }
    
     public String getRandomText(int numWords){ 
        StringBuilder ab = new StringBuilder(); //for wordgram formation
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);
        
        for(int a=0; a < myOrder; a++){ 
            String key = myText[index+a];
            ab.append(key);
            ab.append(" ");
        } 
        sb = ab;
        String sab = ab.toString().trim();
        //turn sab into WordGram keygram
        String[] sabray = sab.split("\\s+");
        WordGram keygram = new WordGram(sabray, 0, myOrder);

        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(keygram);
            if (follows.size() == 0) {
                break;
            }            
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");  
            keygram = keygram.shiftAdd(next);
        }        
        return sb.toString().trim();
    }   
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int stat = 0;     
        int lng = kGram.length();
        while(stat < myText.length){            
            int ix = indexOf(myText,kGram,stat);
            if (ix == -1){
                break;
            }            
            if ( (ix+1) >= myText.length-1){
                break;
            }
            String nexter = myText[ix+lng];
            follows.add(nexter);                                   
            stat = ix+lng;
        }        
        return follows;
        }   

}
