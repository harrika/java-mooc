import java.util.*;
import edu.duke.*;
/*   henry kirya    */

// Location jakarta  = new Location(-6.211, 106.845);
//.getLocation().distanceTo(current)
// double distanceInMeters = jakarta.distanceTo(entry.getLocation());

public class DistanceFilter implements Filter {
    private Location locn;
    private double mxdist;    
    private String nem;
    public DistanceFilter (Location loc, double maxdist, String name){
        locn = loc;
        mxdist = maxdist;
        nem = name;
    }
    public boolean satisfies(QuakeEntry qe){
        boolean tru = (qe.getLocation().distanceTo(locn) < mxdist);
        //System.out.println(tru);
        return tru;
    }
    public String getName(){
        return nem;
    }
    
}
 