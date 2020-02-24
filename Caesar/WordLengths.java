import edu.duke.*;
import java.io.*;

public class WordLengths {
    public int[] countWordLengths(FileResource resource, int[] counts) {
        for (String s: resource.words()){
            //System.out.print(s);
            int cnt = 0;
            char[] rr = s.toCharArray();
            //for (char ch: s.toCharArray()) {
            //for (char ch: rr) {
            for (int k=0; k<rr.length; k++) {
                //boolean bb = (!Character.isLetter(ch)) && ((ch != rr[0]) || (ch != rr[rr.length-1]));
                boolean bb = (k > 0);
                if (Character.isLetter(rr[k])){
                    cnt += 1;                
                }            
            }
            //System.out.println(" is "+ cnt+ "long"); 
            //char[] rr = s.toCharArray();
            //System.out.println(s+" has "+ rr[rr.length-1]+" at end"); 
            counts[cnt] += 1;
        }
        return counts;        
    }
    
    public void testcountWordLengths(){
        FileResource ff = new FileResource();
        int[] cnts = new int[31];        
        int[] cts = countWordLengths(ff, cnts );
        for (int k=0; k < 31; k++){
            //System.out.println(k+" letters long===> "+ cnts[k]);                 
            if (cnts[k] > 0) {
                System.out.println(k+" letters long===> "+ cnts[k]);                 
            }
        }
        System.out.println(" max position: "+ indexOfMax(cnts));
    }
    
    public int indexOfMax(int[] values){
        int mx = 0;
        int pos = 0;
        for (int k=0; k < values.length; k++){
            if (values[k] > mx) {
                mx = values[k]; 
                pos = k;
            }
        }
        return pos;
    }
    
      
     
    
}//end class
