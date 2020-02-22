
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {       
        int res = dna.length();           
        int stopper = dna.indexOf(stopCodon, startIndex+3);
        if (stopper == -1) {
            return res;
        }        
        if ((stopper-startIndex)%3 == 0){
            res = stopper;
        }        
        return res;        
    }   
    
    public String findGene (String dna, int where) {       
        int frstatg = dna.indexOf("ATG", where);
        if (frstatg == -1) {
            return "";
        }
        int mini = 0;
        int frsttaa = findStopCodon(dna, frstatg, "TAA"); 
        int frsttga = findStopCodon(dna, frstatg, "TGA"); 
        int frsttag = findStopCodon(dna, frstatg, "TAG"); 
        
        if (frsttaa == -1 || (frsttga != -1 && frsttga < frsttaa)){
            mini = frsttga;
        } else {
            mini = frsttaa;
        }

        if (mini == -1 || (frsttag != -1 && frsttag < mini)){
            mini = frsttag;
        } 
        System.out.println("mini: "+mini);
        String dina = dna.substring(frstatg,mini+3);        
        return dina;        
    }
    
    public void printAllGenes(String dna) {       
        int startidx = 0;
        while (true) {
            String nowGene = findGene(dna, startidx); 
            if (nowGene.isEmpty()) {
                break;
            }
        System.out.println(nowGene);
        startidx = dna.indexOf(nowGene, startidx) + nowGene.length();        
        }        
    }
    
    public int countGenes(String dna) {  
        int cnt = 0;
        int startidx = 0;
        while (true) {
            String newGene = findGene(dna, startidx); 
            if (newGene.isEmpty()) {
                break;
            }
            System.out.println(newGene);
            cnt = cnt+1;
            startidx = dna.indexOf(newGene, startidx) + newGene.length();        
        }        
        return cnt;    
    }
    
     public void testcountGenes(){
        String b =  "ATGTACACGTGAATGTAGATGACGATTTAACTAATCGATGGGGTGTAGTTAGTT";
        System.out.println(countGenes(b));
    }

}
