//kirya

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }    
    public void setTraining(String s) {
        myText = s.trim();
    }
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }   
    
    // protected ArrayList<String> getFollows(String key){
        // int slen = myText.length();
        // int len = key.length();
        // int stat = 0;
        // ArrayList<String> follow = new ArrayList<String>();        
        // while(stat < (slen-len)){
            // int ix = myText.indexOf(key, stat);
            // if (ix<0){
                // return follow;
            // }
            // int nxit = ix+len;
            // if (nxit < slen) {
                // String nexter = myText.substring(nxit, nxit+1);            
                // follow.add(nexter);                                   
            // }
            // stat = nxit;
        // }
        // return follow;
    // }  
    
     protected ArrayList<String> getFollows(String key){
        ArrayList<String> follow = new ArrayList<String>();          
        int stat = 0;        
        while(stat < myText.length()){
            int ix = myText.indexOf(key, stat);
            if (ix == -1){
                break;
            }            
            if (ix+key.length() >= myText.length()-1){
                break;
            }
            String nexter = myText.substring(ix+key.length(), ix+key.length()+1);
            follow.add(nexter);                                   
            stat = ix+key.length();
        }
        return follow;
    }  
    
    abstract public String getRandomText(int numChars);
}
