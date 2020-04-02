//henry mukomba kirya
//march 28, 2020
import java.io.*;
import java.util.*;

public class MarkovOne extends AbstractMarkovModel{   
    
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }    
 
    public void setTraining(String s){
        myText = s.trim();
    }  
    
    public String toString(){
         return "MarkovModel of order 1";
    }
   
    public String getRandomText(int numChars){        
        StringBuilder sb = new StringBuilder();
        int idx = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(idx, idx+1);
        sb.append(key);
        
        for(int k=0; k<numChars-1; k++){            
            ArrayList<String> follos = getFollows(key);
            if (follos.size() == 0){
                break;
            }                    
            int index = myRandom.nextInt(follos.size());
            String next = follos.get(index);
            sb.append(next);
            key = next;
        }        
        return sb.toString();
    }
  }
