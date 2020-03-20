import java.util.*;
import edu.duke.*;
/*   henry kirya    */

public class DepthFilter implements Filter {
    private double min;
    private double max;    
    private String nem;
    public DepthFilter (double mindeep, double maxdeep, String name) {
        min = mindeep;
        max = maxdeep;
        nem = name;
    }
    public boolean satisfies(QuakeEntry qe){
        boolean res = ((qe.getDepth()>=min) &&
                      (qe.getDepth()<=max));
        return res;    
    }
    
    public String getName(){
        return nem;
    }
    
}
 