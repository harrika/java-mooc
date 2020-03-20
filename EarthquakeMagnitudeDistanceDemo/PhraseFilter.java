import java.util.*;
import edu.duke.*;
/*   henry kirya    */
public class PhraseFilter implements Filter {
    private String where;
    private String frez;
    private String nem;
    
    public PhraseFilter (String place, String phrase, String name){
        where = place;
        frez = phrase;        
        nem = name;
    }
    public boolean satisfies(QuakeEntry qe){
         String tt = qe.getInfo();   
         boolean gud = ( ((where=="start") && (tt.startsWith(frez))) ||
         ((where=="end") && (tt.endsWith(frez))) ||
         ((where=="any") && (tt.contains(frez))));
         return gud;
    }  
    public String getName(){
        return nem;
    }
}
 