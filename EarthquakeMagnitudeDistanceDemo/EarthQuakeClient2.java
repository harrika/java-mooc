import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {    
        
        public ArrayList<QuakeEntry> quakesWithFilter(ArrayList<QuakeEntry> quakedata, Filter f1, Filter f2){
             ArrayList<QuakeEntry> reslt = new ArrayList<QuakeEntry>();
             ArrayList<QuakeEntry> reslt2 = new ArrayList<QuakeEntry>();
             // for (QuakeEntry qe: quakedata){
                 // if(f1.satisfies(qe)){
                     // reslt.add(qe);                    
                    // }
             // }
            //for (QuakeEntry qe2: reslt){
            for (QuakeEntry qe2: quakedata){
                 if(f2.satisfies(qe2)){
                     reslt2.add(qe2);                    
                    }
             }             
             return reslt2;
        } //end filter
        
        // public void testfiltered(){
             // EarthQuakeParser parser = new EarthQuakeParser();               
             // String source = "data/nov20quakedatasmall.atom";   
             // ArrayList<QuakeEntry> data = parser.read(source);
             // Filter f1 = new MagnitudeFilter(4.0, 5.0);
             // Filter f2 = new DepthFilter(-35000.0, -12000.0);        
             // ArrayList<QuakeEntry> filtered = quakesWithFilter(data, f1, f2); 
             // for (QuakeEntry ee: filtered ){
                 // System.out.println(ee);
             // }
        // } //end testfiltered
        
        //those that are 10,000,000 meters (10,000 km) from Tokyo, Japan whose location is (35.42, 139.43),
        //and that are in Japan (this means "Japan" should be the last word in the title). 
     
        public void testfiltered2(){
             EarthQuakeParser parser = new EarthQuakeParser();               
             String source = "data/nov20quakedatasmall.atom";   
             ArrayList<QuakeEntry> data = parser.read(source);
             
              // for (QuakeEntry ee: data ){
                 // System.out.println(ee);
             // }
             Location jap = new Location(35.42, 139.43);                         
             Filter f2 = new PhraseFilter("end", "Japan");        
             Filter f1 = new DistanceFilter(jap, 12000000); 
             ArrayList<QuakeEntry> filtered = quakesWithFilter(data, f1, f2); 
             for (QuakeEntry ee: filtered ){
                 System.out.println(ee);
             }
        } //end testfiltered2
           
        
} //end class
