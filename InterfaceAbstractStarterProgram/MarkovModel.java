//henry mukomba kirya
//march 28, 2020
import java.io.*;
import java.util.*;

public class MarkovModel extends AbstractMarkovModel{   
    private int n;
    
    public MarkovModel(int nn) {
        myRandom = new Random();
        n = nn;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }   
    
    public String toString(){
         return "MarkovModel of order "+n;
    }
           
    public String getRandomText(int numChars){        
        StringBuilder sb = new StringBuilder();
        int idx = myRandom.nextInt(myText.length()-n);        
        String key = myText.substring(idx, idx+n);
        sb.append(key);        
        for(int k=0; k<numChars-n; k++){            
            ArrayList<String> follos = getFollows(key);
            if (follos.size() == 0){
                break;
            }             
            int index = myRandom.nextInt(follos.size());
            String next = follos.get(index);
            sb.append(next);           
            key = key.substring(1)+next;
        }        
        return sb.toString();
    }    
  }
