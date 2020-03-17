
import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        //TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;              
    }
    
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {      
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();        
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {      
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();        
        for (QuakeEntry qe : quakeData) {
            if ((qe.getDepth()>minDepth) && (qe.getDepth()<maxDepth)) {
                answer.add(qe);
                //System.out.println("depth:  "+qe.getDepth)
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){       
        ArrayList<QuakeEntry> res = new ArrayList<QuakeEntry>();        
        for (QuakeEntry qe : quakeData) {
            String tt = qe.getInfo();        
            if ((where=="start") && (tt.startsWith(phrase))){
                res.add(qe);
            }
            if ((where=="end") && (tt.endsWith(phrase))){
                res.add(qe);
            }
            if ((where=="any") && (tt.contains(phrase))){
                res.add(qe);
            }            
        }
        return res;
    }
    
     public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();        
        //String source = "data/nov20quakedatasmall.atom"; 
        String source = "data/nov20quakedata.atom";  
        ArrayList<QuakeEntry> list = parser.read(source);        
        ArrayList<QuakeEntry> list2 = filterByPhrase(list, "any", "Creek");
        
        System.out.println("read data for "+list.size()+" quakes"); 
        System.out.println("finding quakes of phrase");                 
        for (int i=0; i<list2.size(); i++) {
            QuakeEntry entry = list2.get(i);         
            System.out.println(entry);
        }
        System.out.println("found "+list2.size()+" that match explosion at start");

    }
    
     public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();        
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";    //nov20quakedata
        ArrayList<QuakeEntry> list = parser.read(source);
        ArrayList<QuakeEntry> deep = filterByDepth(list, -10000.0, -8000.0);
        System.out.println("read data for "+list.size()+" quakes"); 
        System.out.println("finding qualed of depth between -10000.0 and -8000.0");                 
        for (int i=0; i<deep.size(); i++) {
            QuakeEntry entry = deep.get(i);         
            System.out.println(entry.getInfo());
        }
        System.out.println("found "+deep.size()+" that match the criteria");

    }
    
     
            
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
    
    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";         
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        /*
        for (QuakeEntry qe : list) {
            if (qe.getMagnitude() > 5.0) {
                System.out.println(qe);
            }
        }
        */
        ArrayList<QuakeEntry> listBig5 = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : listBig5) {
           System.out.println(qe); 
        }
        System.out.println("big data size: " + listBig5.size() + " quakes");
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void closeToMe() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";    
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        //System.out.println("# quakes read: " + list.size());
        
        //Durham, NC (35.988, -78.907) and Bridgeport, CA (38.17, -118.82).         
        Location city = new Location(35.988, -78.907); //  //Durham, NC (35.988, -78.907)        
        //Location city = new Location(38.17, -118.82); //Bridgeport, CA (38.17, -118.82).  
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000*1000, city);
        for (int k=0; k< close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }
        System.out.println("# quakes read: " + close.size());

    }
}
