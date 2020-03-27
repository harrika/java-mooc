//kirya henry

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2){        
        String[] arrq1 = q1.getInfo().split(" ");    
        String sq1 = arrq1[arrq1.length - 1];        
        String[] arrq2 = q2.getInfo().split(" ");    
        String sq2 = arrq2[arrq2.length - 1];

        Double mq1 = q1.getMagnitude();
        Double mq2 = q2.getMagnitude();        
        int res = sq1.compareTo(sq2);
        if(res != 0){
            return res;
        }        
        return Double.compare(mq1, mq2);        
    }
}
