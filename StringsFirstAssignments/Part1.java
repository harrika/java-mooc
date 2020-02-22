
/**
 * Description 
 * @author Henry Kirya
 * @version 1.00
 */

public class Part1 {
    
    public String findSimpleGene (String dna) {
        String res = "";
        int starter = dna.indexOf("ATG");
        int stopper = dna.indexOf("TAA", starter+3);
        if (starter == -1) {
            return res;
        }
        if (stopper == -1) {
            return res;
        }        
        if ((stopper-starter)%3 == 0){
            res = dna.substring(starter, stopper+3);            
        }        
        return res;        
    }
    
    public void testSimpleGene() {
        String dna = "ATGTACGTACGTAA";
        System.out.println(dna);
        System.out.println(findSimpleGene(dna));
        
        String dna2 = "ATGTAGTAGTAA";
        System.out.println(dna2);
        System.out.println(findSimpleGene(dna2));
        
        String dna3 = "ATGTACACG";
        System.out.println(dna3);
        System.out.println(findSimpleGene(dna3));
        
        String dna4 = "TACGTACGTAA";
        System.out.println(dna4);
        System.out.println(findSimpleGene(dna4));
    }

} //end class






