import java.util.*;
import edu.duke.*;
/*   henry kirya    */

// Location jakarta  = new Location(-6.211, 106.845);
//.getLocation().distanceTo(current)
// double distanceInMeters = jakarta.distanceTo(entry.getLocation());

public class DistanceFilter implements Filter {
    private Location locn;
    private double mxdist;    
    public DistanceFilter (Location loc, double maxdist){
        locn = loc;
        mxdist = maxdist;
    }
    public boolean satisfies(QuakeEntry qe){
        boolean tru = (qe.getLocation().distanceTo(locn) < mxdist);
        System.out.println(tru);
        return tru;
    }
    
}
 