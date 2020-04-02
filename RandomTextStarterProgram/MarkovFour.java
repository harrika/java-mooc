//henry mukomba kirya
//march 28, 2020

import java.io.*;
import java.util.*;

public class MarkovFour {
    private String myText;
    private Random myRandom;
    
    public MarkovFour() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
     
    public ArrayList<String> getFollows(String key){
        int slen = myText.length();
        int len = key.length();
        int stat = 0;
        ArrayList<String> follow = new ArrayList<String>();        
        while(stat < (slen-len)){
            int ix = myText.indexOf(key, stat);
            if (ix<0){
                return follow;
            }
            int nxit = ix+len;
            if (nxit < slen) {
                String nexter = myText.substring(nxit, nxit+1);            
                follow.add(nexter);                                   
            }
        stat = nxit;
        }
   return follow;
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
