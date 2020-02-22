
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {    
      public Boolean twoOccurrences(String a, String b) {       
          int alen = a.length(); 
          int one = b.indexOf(a);
          if (one  == -1) {
              return false;
            }        
          String bb = b.substring(one+alen);
          int two = bb.indexOf(a);
          if (two  == -1) {
              return false;
          }
          return true;              
       }   
       
        public String lastPart(String a, String b) {       
          int alen = a.length(); 
          int one = b.indexOf(a);
          if (one  == -1) {
              return b;
            }        
          String bb = b.substring(one+alen);
          return bb;
       }   
       
       public void testing() {  
            System.out.println("an - banana");
            System.out.println(twoOccurrences("an","banana"));   
            
            System.out.println("by - A story by Abby Long");
            System.out.println(twoOccurrences("by","A story by Abby Long"));   
            
            System.out.println("atg - ctgtatgta");
            System.out.println(twoOccurrences("atg","ctgtatgta")); 
            
            String aa = "an";
            String bb = "banana";
            String cc = lastPart(aa,bb);
            System.out.println("The part of the string after "+aa+" in "+bb+" is "+cc);  
            
            String aa2 = "zoo";
            String bb2 = "forest";
            String cc2 = lastPart(aa2,bb2);
            System.out.println("The part of the string after "+aa2+" in "+bb2+" is "+cc2);        
        }        
        
}
