//kirya Henry
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private HashMap<Integer, ArrayList<String>> bmap;
    private String[] myText;
    private Random myRandom;
    private int myOrder;
       
    public EfficientMarkovWord(int norder) {
        bmap = new HashMap<Integer, ArrayList<String>>();
        myRandom = new Random();
        myOrder = norder;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
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
        String[] sabray = sab.split("\\s+");
        WordGram keygram = new WordGram(sabray, 0, myOrder);

        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(keygram);
            //if (follows.isEmpty()) {   
            if (follows == null) {                 
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
      
    
    public void buildMap() {
        ArrayList<String> fol;
        WordGram wgram;
        for(int k=0; k < myText.length-myOrder; k++){       
            wgram = new WordGram(myText, k, myOrder);
            int hsh = wgram.hashCode();            
            String nxt = myText[k+myOrder];     
            if ((k+myOrder) > (myText.length-1)){
                 if(!bmap.containsKey(hsh)){
                    fol = new ArrayList<String>(); 
                    bmap.put(hsh, fol);   
                    break;
                 }            
            }
            if(bmap.containsKey(hsh)){                
                fol = bmap.get(hsh);
                fol.add(nxt);
                bmap.put(hsh, fol);
            }else{
                fol = new ArrayList<String>();
                fol.add(nxt);  
                bmap.put(hsh, fol);                
            }
        }    
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {         
        return bmap.get(kGram.hashCode());
    }
     
    public void printHashMapInfo(){        
        int maxray = 0;
        int sizer = 0;
        String maxke = "";       
        for (int k : bmap.keySet()) {            
            ArrayList<String> ray = bmap.get(k);
            //System.out.print(k+": ");
            //System.out.println(ray);
            if (ray.size() > maxray){
                maxray = ray.size();                
            }
        }        
        System.out.println("number of keys in map: "+bmap.size());
        System.out.println("size of largest array in hashmap: "+maxray);
        for (int kk : bmap.keySet()) {
            if (bmap.get(kk).size() == maxray){
                System.out.println("largest size  key: "+kk);                        
            }
        }        
    }        

}
