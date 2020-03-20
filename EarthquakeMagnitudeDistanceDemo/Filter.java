import java.util.*;
import edu.duke.*;
/*   henry kirya    */

public interface Filter {
    public boolean satisfies (QuakeEntry qe);
    public String getName();
}
