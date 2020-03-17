import java.util.*;
import edu.duke.*;
/*   henry kirya    */

public class DepthFilter implements Filter {
    private double min;
    private double max;    
    public DepthFilter (double mindeep, double maxdeep){
        min = mindeep;
        max = maxdeep;
    }
    public boolean satisfies(QuakeEntry qe){
        boolean res = ((qe.getDepth()>=min) &&
                      (qe.getDepth()<=max));
        return res;    
    }
    
}
 