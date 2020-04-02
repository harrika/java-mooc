//henry mukomba kirya
//march 28, 2020

import java.io.*;
import java.util.*;

public class MarkovFour extends AbstractMarkovModel{    
    public MarkovFour() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }    
    
    public String toString(){
         return "MarkovModel of order 4";
    }
           
    public String getRandomText(int numChars){        
        StringBuilder sb = new StringBuilder();
        int idx = myRandom.nextInt(myText.length()-4);        
        String key = myText.substring(idx, idx+4);
        sb.append(key);
        
        for(int k=0; k<numChars-4; k++){            
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
