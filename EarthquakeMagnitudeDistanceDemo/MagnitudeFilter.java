import java.util.*;
import edu.duke.*;
/*   henry kirya    */

public class MagnitudeFilter implements Filter {
    private double min;
    private double max;  
    private String nem;
    public MagnitudeFilter(double minmag, double maxmag, String name){
        min = minmag;
        max = maxmag;
        nem = name;
    }
    public boolean satisfies(QuakeEntry qe){
        boolean res = ((qe.getMagnitude()>=min) &&
                      (qe.getMagnitude()<=max));
        return res;    
    }
    
    public String getName(){
        return nem;
    }
    
}
 