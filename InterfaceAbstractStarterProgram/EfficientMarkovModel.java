//henry mukomba kirya
//march 28, 2020
import java.io.*;
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel{   
    private int n;
    private HashMap<String, ArrayList<String>> bmap;
    
    
    public EfficientMarkovModel(int nn) {
        bmap = new HashMap<String, ArrayList<String>>();
        myRandom = new Random();
        n = nn;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    } 
    
    public void printHashMapInfo(){        
        int maxray = 0;
        String maxke = "";       
        for (String k : bmap.keySet()) {            
            ArrayList<String> ray = bmap.get(k);
            //System.out.print(k+": ");
            //System.out.println(ray);
            if (ray.size() > maxray){
                maxray = ray.size();                
            }
        }  
        System.out.println("number of keys in map: "+bmap.size());
        System.out.println("size of largest array in hashmap: "+maxray);
        for (String kk : bmap.keySet()) {
            if (bmap.get(kk).size() == maxray){
                System.out.println("largest size  key: "+kk);                        
            }
        }        
    }
    
    protected ArrayList<String> getfella(String key){
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
    
    // public ArrayList<String> getfella (String key){
        // int slen = myText.length();
        // int len = key.length();
        // int stat = 0;
        // ArrayList<String> follow = new ArrayList<String>();     
        // int diff = myText.length() - key.length();
        // while(stat < diff) {
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
    
     public void buildMap(){ 
       bmap = new HashMap<String, ArrayList<String>>();
       int stat = 0;       
       while(stat < (myText.length()-n)){
           String ke = myText.substring(stat, stat+n);
           ArrayList<String> fello = getfella(ke);
           if (!bmap.containsKey(ke) && (!fello.isEmpty())){
               bmap.put(ke, fello);
            }  
            stat = stat+1;             
        }   
        printHashMapInfo();            
     }
    
  
    public String toString(){
         return "EfficientMarkovModel of order "+n;
    }
    
    public ArrayList<String> getFollows2(String key){        
        return bmap.get(key); 
    }  
     
    public ArrayList<String> getFollows3(String key){          
        if (bmap.containsKey(key)){
            return bmap.get(key); 
        }else{
            ArrayList<String> fello = getfella(key);
            bmap.put(key, fello);
            return fello;
        }        
    }  
           
    public String getRandomText(int numChars){     
        //buildMap();
        StringBuilder sb = new StringBuilder();
        int idx = myRandom.nextInt(myText.length()-n);        
        String key = myText.substring(idx, idx+n);
        sb.append(key);        
        for(int k=0; k<numChars-n; k++){            
            ArrayList<String> follos = getFollows3(key);                       
            if (follos == null){    
               break;
            } 
            if (follos.size() == 0){
                break;
            }
            int index = myRandom.nextInt(follos.size());
            String next = follos.get(index);
            sb.append(next);           
            key = key.substring(1)+next;
        }
         //printHashMapInfo();
         return sb.toString();        
    }    
  }
