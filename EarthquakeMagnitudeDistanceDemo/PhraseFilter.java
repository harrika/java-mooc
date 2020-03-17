import java.util.*;
import edu.duke.*;
/*   henry kirya    */
public class PhraseFilter implements Filter {
    private String where;
    private String frez;
    public PhraseFilter (String where, String frez){
        where = where;
        frez = frez;        
    }
    public boolean satisfies(QuakeEntry qe){
         String tt = qe.getInfo();   
         boolean gud = ( ((where=="start") && (tt.startsWith(frez))) ||
         ((where=="end") && (tt.endsWith(frez))) ||
         ((where=="any") && (tt.contains(frez))));
         return gud;
    }    
}
 