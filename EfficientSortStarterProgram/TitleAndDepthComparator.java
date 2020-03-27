//kirya Henry

import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2){
        String sq1 = q1.getInfo();
        String sq2 = q2.getInfo();
        Double dq1 = q1.getDepth();
        Double dq2 = q2.getDepth();        
        int res = sq1.compareTo(sq2);
        if(res != 0){
            return res;
        }        
        return Double.compare(dq1, dq2);        
    }
}
//Collections.sort(list, new TitleAndDepthComparator());
