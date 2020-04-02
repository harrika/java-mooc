//kirya Henry

import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
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
         int a1 = indexOf(ss, "this","is", 0);
         int a2 = indexOf(ss, "is","a", 3);
         int a3 = indexOf(ss, "simple","test", 0);          
         System.out.println("index: "+a2);
     }
    
    public int indexOf(String[] words, String target1, String target2, int start) {
        for(int k=start; k < words.length; k++){ 
            if(words[k].equals(target1) && words[k+1].equals(target2)){
                return k;
            }        
        }
        return -1;
    }
    
    public String getRandomText(int numWords){ 
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);
        String key1 = myText[index];
        String key2 = myText[index+1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);        
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key1, key2);
            if (follows.size() == 0) {
                break;
            }            
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");            
            key1 = key2;
            key2 = next;
        }        
        return sb.toString().trim();
    }   
    
   
    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int stat = 0;        
        while(stat < myText.length){
            int ix = indexOf(myText,key1,key2,stat); //ix+1 also taken
            if (ix == -1){
                break;
            }            
            if (ix+2 >= myText.length-1){
                break;
            }
            String nexter = myText[ix+2];
            follows.add(nexter);                                   
            stat = ix+2;
        }        
        return follows;
        }
       
   
}
