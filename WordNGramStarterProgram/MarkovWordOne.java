//kirya Henry

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
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
         int a1 = indexOf(ss, "this", 0);
         int a2 = indexOf(ss, "this", 3);
         int a3 = indexOf(ss, "frog", 0);
         int a4 = indexOf(ss, "frog", 5);
         int a5 = indexOf(ss, "simple", 2);
         int a6 = indexOf(ss, "test", 5);   
         System.out.println("index: "+a3);
     }
    
    public int indexOf(String[] words, String target, int start) {
        for(int k=start; k < words.length; k++){ 
            if(words[k].equals(target)){
                return k;
            }        
        }
        return -1;
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }            
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");            
            //System.out.println("key: "+key);
            //System.out.println("follos: "+follows);            
            key = next;
        }        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int stat = 0;        
        while(stat < myText.length){
            int ix = indexOf(myText,key,stat);
            if (ix == -1){
                break;
            }            
            if ( (ix+1) >= myText.length-1){
                break;
            }
            String nexter = myText[ix+1];
            follows.add(nexter);                                   
            stat = ix+1;
        }        
        return follows;
        }
    
    // private ArrayList<String> getFollows(String key) {
        // ArrayList<String> follows = new ArrayList<String>();
        // int stat = 0;        
        // while(stat < myText.length){
            // int ix = indexOf(myText,key,stat);
            // if (ix == -1){
                // break;
            // }            
            // if (ix+key.length() >= myText.length-1){
                // break;
            // }
            // String nexter = myText[ix+1];
            // follows.add(nexter);                                   
            // stat = ix+key.length();
        // }        
        // return follows;
        // }

}
