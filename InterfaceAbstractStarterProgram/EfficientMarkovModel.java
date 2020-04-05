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
        buildMap();
        printHashMapInfo();
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
      
     public void buildMap(){ 
       ArrayList<String> fol;
       for(int k=0; k < myText.length()-n; k++){ 
           String ke = myText.substring(k, k+n);
           int nxtid = k+n;           
           String nxt = myText.substring(nxtid, nxtid+1);
            if(bmap.containsKey(ke)){                
                fol = bmap.get(ke);
                fol.add(nxt);
                bmap.put(ke, fol);
            }else{
                fol = new ArrayList<String>();
                fol.add(nxt);  
                bmap.put(ke, fol);                
            } 
        }
     }
    
    public String toString(){
         return "EfficientMarkovModel of order "+n;
    }
    
    public ArrayList<String> getFollows(String key){        
        return bmap.get(key); 
    } 
           
    public String getRandomText(int numChars){     
        //buildMap();
        StringBuilder sb = new StringBuilder();
        int idx = myRandom.nextInt(myText.length()-n);        
        String key = myText.substring(idx, idx+n);
        sb.append(key);        
        for(int k=0; k<numChars-n; k++){            
            ArrayList<String> follos = getFollows(key);                       
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
