import java.util.*;
import edu.duke.*;
/*   henry kirya    */
public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;   
    public MatchAllFilter(){
        filters = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f){
        filters.add(f);    
    }
    
    public String getName(){
        String ss = "";
        for(Filter f: filters){
            ss = ss+f.getName()+", ";        
        }
        return ss;    
    }
    
    public boolean satisfies(QuakeEntry qe){
        for(Filter f: filters){
            if (!f.satisfies(qe)){
                return false;
            }
        }
        return true;        
    }    
}
 