import edu.duke.*;
import java.io.*;
import java.util.ArrayList;

   
public class CharactersInPlay {
    
    private ArrayList<String> xters;
    private ArrayList<Integer> xcounts;  
    
    public CharactersInPlay() {
        xters = new ArrayList<String>();
        xcounts = new ArrayList<Integer>();  
    }    
    
    public void update(String person){       
        int idx = xters.indexOf(person);
        if (idx == -1){
            xters.add(person); 
            xcounts.add(1);                
        }else{
            int val = xcounts.get(idx);
            xcounts.set(idx,val+1);
        }
    }
    
    public void findAllCharacters(){
        //xters.clear();
        //xcounts.clear();        
        FileResource res = new FileResource();
        for (String line : res.lines()) {
            line = line.toLowerCase();
            int idx = line.indexOf(".");
            if (idx >= 0){                               
                String actor = line.substring(0,idx);
                update(actor);                
            }            
        }    
    }
    
     public int indexOfMax(){
            int mxfrq = 0;
            int idxpos = 0;
            for (int k=0; k<xcounts.size(); k++){
                if (xcounts.get(k) > mxfrq) {
                    mxfrq = xcounts.get(k); 
                    idxpos = k;
            }
      }
    return idxpos;
    }
    
    public void tester(){
        findAllCharacters(); 
        for (int k=0; k<xters.size(); k++){  
            if (xcounts.get(k)>2){
                System.out.print(k+": actor:"+xters.get(k)+"  ===>");
                System.out.println(xcounts.get(k)+" freq");                            
            }            
        }        
    }  
    
    public void tester2(){
        charactersWithNumParts(10,15);
    }
    
    
    public void charactersWithNumParts(int n1, int n2){
        findAllCharacters(); 
        for (int k=0; k<xters.size(); k++){  
            if ((xcounts.get(k)>=n1) && (xcounts.get(k)<=n2)){
                System.out.print(k+": actor:"+xters.get(k)+"  ===>");
                System.out.println(xcounts.get(k)+" freq");                            
            }            
        }        
    }   
    

}// end class
