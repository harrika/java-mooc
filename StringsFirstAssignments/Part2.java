
/**
 * Write a description of Part2 here.
 * 
 * @author henry kirya
 * @version 1.01
 */
public class Part2 {
      public String findSimpleGene (String dna, String startcod, String stopcod) {       
        String res = "";
        startcod = startcod.toLowerCase();
        stopcod = stopcod.toLowerCase();       
        dna = dna.toLowerCase();        
        int starter = dna.indexOf(startcod);
        int stopper = dna.indexOf(stopcod, starter+3);
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
    
    public int second(String a, String b) {
          int pos1 = b.indexOf(a);
          int pos2 = b.indexOf(a, pos1+1);
          return pos2;        
    }
  

    public void testSimpleGene() {
        String dna = "ATGTACGTACTGTAA";
        System.out.println(dna);
        System.out.println(findSimpleGene(dna,"ATG","TAA"));
        
        String dna2 = "ATGTACACG";
        System.out.println(dna2);
        System.out.println(findSimpleGene(dna2,"ATG","TAA"));
        
        String dna3 = "TACGTACGTAA";
        System.out.println(dna3);
        System.out.println(findSimpleGene(dna3,"ATG","TAA"));
        
        String dna4 = "ATGTACGTACGTAA";
        System.out.println(dna4);
        System.out.println(findSimpleGene(dna4,"ATG","TAA"));
        
        String dna5 = "ATGTACGTACGCTAA";
        System.out.println(dna5);
        System.out.println(findSimpleGene(dna5,"ATG","TAA"));
        
        String dna6 = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println(dna6);
        System.out.println(findSimpleGene(dna6,"ATG","TAA"));    
        
        String aa = "HARI";
        String bb = "AAAHARIGGGGHARITAGATTAAGAAACC";
        System.out.println("SECOND HARI FOUND AT POSITIOON: "+second(aa,bb));
      }  

}
