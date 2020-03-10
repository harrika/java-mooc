import edu.duke.*;
import java.util.*;

public class Codon {
    private HashMap<String,Integer> codons;
    
    public Codon(){        
        codons = new HashMap<String,Integer>();    
    }
    
    private void buildCodonMap(int start, String dna) {
        System.out.println(dna);
        //codons.clear();
        while ((start+3) <= dna.length()){
            int end = start+3;
            String cod = null;
            String codd = dna.substring(start, end); 
            if (Character.isLetter(codd.charAt(2))){
                cod = codd;
            }            
            start = end;      
            if (codons.keySet().contains(cod)){
                codons.put(cod,codons.get(cod)+1);            
            }else{
                codons.put(cod,1);          
            }                 
        }
        codons.remove(null);             
    }
    
    private String getMostCommonCodon() {
        int mxfreq = 0;        
        String topcod = "eee";        
        for (String cod : codons.keySet()){
            int freq = codons.get(cod);            
            if (freq >= mxfreq){
                mxfreq = freq; 
                topcod = cod;                  
            }
        }       
        return topcod;    
    }
    
    private void printCodonCounts(int start, int end) {
        for (String cod : codons.keySet()){
            int codcnt = codons.get(cod); 
            //if ((codcnt>=start) && (codcnt <=end)){
            if (codcnt == 7){
                System.out.println(cod+" 7 counts==> "+codcnt); 
                //System.out.println(cod+" === "+cod.charAt(2));      
            }     
        }
    }
    
   
    public void testbuildCodonMap() {
        codons.clear();
        buildCodonMap(0, "CTTGAGGAGGAGFSSFSSFSSFSSDHJHGT");    
    }    
    
    public void testgetMostCommonCodon() {
        testbuildCodonMap();
        String top = getMostCommonCodon();  
        System.out.println("top codon: "+top);                
    }      
    
    public void testermain() {
        FileResource fr = new FileResource();
        String strand = fr.asString().toUpperCase(); 
        System.out.println("dna strand: " + strand);
        for (int k=0; k<3; k++){
            System.out.println("\n------------------------------------------------------");
            System.out.println("frame starting at " + k);
            System.out.println("------------------------------------------------------");
            codons.clear();        
            buildCodonMap(k, strand);
            int uniq = 0;
            for (String cod : codons.keySet()){
                uniq += 1;
            }
            System.out.println("uniq codons in map: " + uniq);
            String comcod = getMostCommonCodon(); 
            int comcodcnt = codons.get(comcod);                
            System.out.println("most common codon: "+comcod+"--> "+comcodcnt); 
            System.out.println("codon in range"); 
            printCodonCounts(0,4);           
        }
    }
    
     
}//end class
