
/**
 * Write a description of Part1 here.
 * 
 * @author henry kirya
 * @version (1.01)
 */
public class Part1 {
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
    
    public void testPrintAll(){
        String dnaa =  "ATGTACACGTGAATGTAGATGACGATTTAACTAATCGATGGGGTGTATTTGA";
        String dnaax = "ATGTACACGAGCTAAGCGATGTACACTTAA";
        System.out.println(dnaa);
        System.out.println("================================================"); 
        printAllGenes(dnaa);        
    }
    
//    "ATG TAC ACG TAG
//     ATG TAG
//     ATG ACG ATT TAA
//     CTAATCG ATG GGG TGT ATT TGA";
    
     public void testfindGene() {     
         //DNA with no “ATG”, 
         String dna = "TACACGAGTAGACGATTAGACTACT";
         System.out.println(dna);
         System.out.println( "DNA with no “ATG” firsatg");        
         System.out.println(findGene(dna,0));        
         System.out.println("-----------------------");         
         
                  
         //DNA with “ATG and one valid stop codon”
         String dna2 = "ATGTACACGATGTAGATGAGACTACT";
         System.out.println(dna2);
         System.out.println("DNA with ATG and one valid stop codon");        
         System.out.println( findGene(dna2,0));        
         System.out.println("-----------------------");         
         
         //DNA with “ATG” and multiple valid stop codons, 
         String dna3 = "ATGTACACGATGTAGACGATGAACTACT";
         System.out.println(dna3);
         System.out.println("DNA with “ATG” and multiple valid stop codons");        
         System.out.println(findGene(dna3,0));     
         System.out.println("-----------------------");         
         
         
         // DNA with “ATG” and no valid stop codons. 
         String dna4 = "ATGTACACGATGTACGATGCTACT";
         System.out.println(findGene(dna4,0));        
         System.out.println("DNA with “ATG” and no valid stop codons");        
         System.out.println(findGene(dna4,0));     
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

