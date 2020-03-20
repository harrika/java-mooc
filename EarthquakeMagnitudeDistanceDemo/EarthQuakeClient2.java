import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {    
        
        public ArrayList<QuakeEntry> quakesWithFilter2(ArrayList<QuakeEntry> quakedata, Filter f1, Filter f2){
             ArrayList<QuakeEntry> reslt = new ArrayList<QuakeEntry>();
             ArrayList<QuakeEntry> reslt2 = new ArrayList<QuakeEntry>();
             for (QuakeEntry qe: quakedata){
                 if(f1.satisfies(qe)){
                     reslt.add(qe);                    
                    }
             }
            for (QuakeEntry qe2: reslt){            
                 if(f2.satisfies(qe2)){
                     reslt2.add(qe2);                    
                    }
             }             
             return reslt2;
        } //end filter
        
         public ArrayList<QuakeEntry> quakesWithFilter(ArrayList<QuakeEntry> quakedata, Filter f){
             ArrayList<QuakeEntry> reslt = new ArrayList<QuakeEntry>();
             //ArrayList<QuakeEntry> reslt2 = new ArrayList<QuakeEntry>();
             for (QuakeEntry qe: quakedata){
                 if(f.satisfies(qe)){
                     reslt.add(qe);                    
                 }
             }                
             return reslt;
        } //end filter
        
        public void testfiltered(){
             EarthQuakeParser parser = new EarthQuakeParser();               
             String source = "data/nov20quakedata.atom";   
             ArrayList<QuakeEntry> data = parser.read(source);
             System.out.println("data size: "+data.size());                
             //Location jap = new Location(35.42, 139.43);  
             Filter f1 = new MagnitudeFilter(3.5, 4.5, "magni");
             Filter f2 = new DepthFilter(-55000.0, -20000.0, "deepen");   // -55,000.0 and -20,000.0 inclusive.     
             //Location denver = new Location(39.7392, -104.9903);
             //Filter f1 = new DistanceFilter(denver, 1000000.0, "locodistance");   
             //Filter f2 = new PhraseFilter("end", "a", "phrasing");              
             ArrayList<QuakeEntry> filtered = quakesWithFilter2(data, f1, f2); 
             for (QuakeEntry ee: filtered){
                 System.out.println(ee);
             }
             System.out.println("size filtered: "+filtered.size());             
        } //end testfiltered
        
        //those that are 10,000,000 meters (10,000 km) from Tokyo, Japan whose location is (35.42, 139.43),
        //and that are in Japan (this means "Japan" should be the last word in the title). 
     
        public void testfiltered2(){
             EarthQuakeParser parser = new EarthQuakeParser();               
             String source = "data/nov20quakedatasmall.atom";   
             ArrayList<QuakeEntry> dat = parser.read(source);            
             Location jap = new Location(35.42, 139.43);                         
             //Filter f1 = new PhraseFilter("any", "Japan");        
             Filter f2 = new DistanceFilter2(jap, 10000000.0, "locodistance");              
             //ArrayList<QuakeEntry> filtered = quakesWithFilter(data, f1, f2); 
             ArrayList<QuakeEntry> filtered = quakesWithFilter(dat, f2); 
             for (QuakeEntry ee: filtered ){             
                 System.out.println(ee);
             }             
        } //end testfiltered2
        
        public void testMatchAllFilter(){
            EarthQuakeParser parser = new EarthQuakeParser();               
            String source = "data/nov20quakedata.atom";   
            ArrayList<QuakeEntry> socdata = parser.read(source);
            // for (QuakeEntry qq: socdata ){
                 // System.out.println(qq);
            // }                   
            System.out.println("Number of quakes: "+socdata.size());
            MatchAllFilter maf = new MatchAllFilter();
            maf.addFilter(new MagnitudeFilter(0.0, 2.0, "magnitude")); 
            //maf.addFilter(new DepthFilter(-100000.0, -10000.0, "deepening"));  -180,000.0 and -30,000.0
            //maf.addFilter(new PhraseFilter("any", "a", "phrasing"));
            ArrayList<QuakeEntry> newlist = quakesWithFilter(socdata, maf); 
            for(QuakeEntry qe: newlist){
                 System.out.println(qe);
            }  
            System.out.print("Filters used are: ");
            System.out.println(maf.getName());
            System.out.println("size sorted: "+newlist.size());
        }
        public void testMatchAllFilter2(){
            EarthQuakeParser parser = new EarthQuakeParser();               
            String source = "data/nov20quakedata.atom";   
            ArrayList<QuakeEntry> soc = parser.read(source);
            // for (QuakeEntry qq: socdata ){
                 // System.out.println(qq);
            // }       
            System.out.println("Number of quakes: "+soc.size());
            MatchAllFilter maf2 = new MatchAllFilter();
            //maf2.addFilter(new DepthFilter(-180000.0, -30000.0, "deepening"));  
            Location loco = new Location(55.7308, 9.1153);
            maf2.addFilter(new DistanceFilter(loco, 3000000, "locodistance"));            
            maf2.addFilter(new MagnitudeFilter(0.0, 5.0,"magnitude"));            
            maf2.addFilter(new PhraseFilter("any", "e", "phrasing"));
            ArrayList<QuakeEntry> nlist = quakesWithFilter(soc, maf2); 
            for(QuakeEntry q: nlist){
                 System.out.println(q);
            } 
            System.out.println("size sorted: "+nlist.size());            
        }
           
        
} //end class
