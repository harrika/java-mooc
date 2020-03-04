import edu.duke.*;
import java.io.*;
import java.util.ArrayList;

public class WordFrequencies {
     private ArrayList<String> myWords;
     private ArrayList<Integer> myFreqs;     
    
    public WordFrequencies() {        
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
        
    }    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource res = new FileResource();
        for (String s: res.words()){
            s = s.toLowerCase();
            int idx = myWords.indexOf(s);
            if (idx == -1){
                myWords.add(s); 
                myFreqs.add(1);                
            }else{
                int val = myFreqs.get(idx);
                myFreqs.set(idx,val+1);
            }            
        }
    }
    public void tester(){
        findUnique();        
        System.out.println("---------------------------------------");            
        for (int k=0; k<myWords.size(); k++){                        
            System.out.print(k+": word:"+myWords.get(k)+"  ===>");
            System.out.println(myFreqs.get(k)+" freq");            
        }
        System.out.println("---------------------------------------");
        int kk = indexOfMax();
        System.out.print("The words that occurs most often : "+myWords.get(kk));
        System.out.println(" and its count is : "+myFreqs.get(kk));        
        System.out.println("number uniques words: "+myWords.size());
    }    
    
    public int indexOfMax(){
            int mxfrq = 0;
            int idxpos = 0;
            for (int k=0; k<myFreqs.size(); k++){
                if (myFreqs.get(k) > mxfrq) {
                    mxfrq = myFreqs.get(k); 
                    idxpos = k;
            }
      }
    return idxpos;
    }
        
        
}//end class
