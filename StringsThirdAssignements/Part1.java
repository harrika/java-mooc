/**
 * Write a description of Part1 here.
 * 
 * @author henry kirya
 * @version (1.01)
 */
import edu.duke.*;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {       
        int curridx = dna.indexOf(stopCodon, startIndex+3);
        while (curridx != -1) {
             if ((curridx-startIndex)%3 == 0){
                 return curridx;
             }else{
                 curridx = dna.indexOf(stopCodon, curridx+1);                
            }        
        }            
        return dna.length();        
    }   
    
       
    public String findGene (String dna, int where) {       
        //String sss = "";
        int statidx = dna.indexOf("ATG", where);
        if (statidx == -1) {            
            return "";
        }
        int taaidx = findStopCodon(dna, statidx, "TAA");         
        int tagidx = findStopCodon(dna, statidx, "TAG"); 
        int tgaidx = findStopCodon(dna, statidx, "TGA");         
        int temp = Math.min(taaidx, tagidx);
        int minidx = Math.min(temp, tgaidx);
        if (minidx == dna.length()) {
            return "";
        }
        return dna.substring(statidx, minidx+3);        
    }
    
     public void printAllGenes(String dna) {       
        int startidx = 0;
        while (true) {
            String currGene = findGene(dna, startidx); 
            if (currGene.isEmpty()) {
                break;
            }
            System.out.println(currGene);            
            startidx = dna.indexOf(currGene, startidx) + currGene.length();        
        }        
    }
  
    public int howmanyCgs(String stringa, String dna) {
        int mny = 0;        
        int idx = 0;
        //int aa = 1;
        int aa = stringa.length();
        while (true) {
            int fndidx = dna.indexOf(stringa, idx);            
            if (fndidx != -1) {
                mny = mny+1;
                idx = fndidx+aa;
            }else{
                break;
            }
        } 
        return mny;
    }
    
    public double cgRatio(String dna){        
        int cs = howmanyCgs("C", dna);
        int gs = howmanyCgs("G", dna);
        int cgs = cs+gs;
        return (float) cgs/dna.length();                               
    }
    
    public void testcgRatio(){
        String dd =  "ATGTACACGTAAATTTTTTT";
        System.out.println(cgRatio(dd));  
    }    
    
    public int countCTG(String dna){
        return howmanyCgs("CTG", dna);
    }
    
    public void processGenes(StorageResource sr){  
        int sr9 = 0;
        int sr35 = 0;
        int mx = 0;
        for (String ss : sr.data()) {
            int sl = ss.length();
            if (sl > mx) {
                mx = sl;
            }
            if (sl > 0){   
                System.out.println("a gene: "+ss);
                sr9 = sr9+1;                
            }
            if (cgRatio(ss) > 0.35) {                
                sr35 = sr35+1;  
            }
        }
        System.out.println("longer than 0: "+sr9);
        System.out.println("cgratio higher than 0.35: "+sr35);
        System.out.println("longest gene is "+mx+" long");        
    }    
    
    public StorageResource getAllGenes(String dna) {       
        StorageResource genelist = new StorageResource();
        int startidx = 0;
        while (true) {
            //System.out.println("stsrting idx at: "+startidx);
            String daGene = findGene(dna, startidx); 
            if (daGene.isEmpty()) {
                break;
            }else{
                genelist.add(daGene);
                startidx = dna.indexOf(daGene, startidx) + daGene.length();                    
            }            
        }    
        return genelist;
    }
    
    public void testProcessGenes() {
        //String strg = "ATGTACACGTGA ATGTAG ATGACGATTTAA ATGGGGTGTATTTGA ATGCTAACCTGA ATGTAG ";
        //String strg = "AtGMyGeneOneAATGGGGTAATGATAGAACCCGGYGGGGTAGGGCTGCCCATGendOneTAA";
//      FileResource rr = new FileResource("brca1line.fa");
        StorageResource stor = new StorageResource();
        FileResource rr = new FileResource("GRch38dnapart.fa");
        String strg = rr.asString().toUpperCase(); 
        System.out.println("number of ctgs: " + countCTG(strg));                
        System.out.println("=========================================================");                        
        processGenes(getAllGenes(strg));          
    }
    
    public void testgetAllGenes(){
        //String dna =  "ATGTACACGTGAATGTAGATGACGATTTAACTAATCGATGGGGTGTATTTGACCATGATATGGTAG";
        String dna = "TTTATGTACACGTGA ggATGTAG ATGACGATTTAA ttATGGGGTGTATTTGA ggATGCTAACCTaA ATGggtTAG";
        System.out.println(dna);
        System.out.println("================================================"); 
        StorageResource gnbook = getAllGenes(dna);        
        for (String gen : gnbook.data()) {
            System.out.println(gen);
        }
    }
       
    
    public void testPrintAllGenes(){
        String dna =  "ATGTACACGTGAATGTAGATGACGATTTAACTAATCGATGGGGTGTATTTGA";
        String dnaa = "GAATGTAGATGAATGTACTAA";
        System.out.println(dnaa);
        System.out.println("================================================"); 
        printAllGenes(dnaa);        
    }
    
     public void testfindGene() {  
         String dna = "GGATGTACACGTGGATGTTGATGACGATTTATCTAATCGATGGGGTGTATTGAGT";
         System.out.println(dna);               
         System.out.println(findGene(dna,0));        
         System.out.println("-----------------------");             
        }
    
     public void testFindStopCodon() {
        String dna = "ATGTACGTATGTAA";
        System.out.println(dna);
        System.out.println(findStopCodon(dna,0,"TAA"));
        
        String dna2 = "ATGTACACGATGTAGACGATGTAGACTACT";
        System.out.println(dna2);
        System.out.println(findStopCodon(dna2,9,"TAC"));
    }        
    
}


