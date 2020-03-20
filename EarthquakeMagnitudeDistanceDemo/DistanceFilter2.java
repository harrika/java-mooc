import java.util.*;
import edu.duke.*;
/*   henry kirya    */

// Location jakarta  = new Location(-6.211, 106.845);
//.getLocation().distanceTo(current)
// double distanceInMeters = jakarta.distanceTo(entry.getLocation());

public class DistanceFilter2 implements Filter {
    private Location locn;
    private double mxdist;    
    private String nem;
    public DistanceFilter2 (Location loc, double maxdist, String name){
        locn = loc;
        mxdist = maxdist;
        nem = name;
    }
    public boolean satisfies(QuakeEntry qe){
        double dd = qe.getLocation().distanceTo(locn);
        boolean tru = ((dd > mxdist-500000.0) &&
                      (dd < mxdist+500000.0) );
        System.out.println("dist to location: "+dd+" ----- "+tru);
        return tru;
    }
    public String getName(){
        return nem;
    }
    
}
 