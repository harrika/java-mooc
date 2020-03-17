import java.util.*;
import edu.duke.*;
/*   henry kirya    */

public class MagnitudeFilter implements Filter {
    private double min;
    private double max;    
    public MagnitudeFilter(double minmag, double maxmag){
        min = minmag;
        max = maxmag;
    }
    public boolean satisfies(QuakeEntry qe){
        boolean res = ((qe.getMagnitude()>=min) &&
                      (qe.getMagnitude()<=max));
        return res;    
    }
    
}
 