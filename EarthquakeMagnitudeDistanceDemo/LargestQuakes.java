import java.util.*;
import edu.duke.*;
/*   henry kirya    */

public class LargestQuakes {    
    
    public void findLargestQuakes(){                
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom"; 
        String source = "data/nov20quakedata.atom"; 
        ArrayList<QuakeEntry> largeray  = parser.read(source);
        ArrayList<QuakeEntry> large5  = new ArrayList<QuakeEntry>();
        System.out.println("data for large array "+largeray.size()); 
        // for(int k=0; k < largeray.size(); k++){
            // QuakeEntry quake = largeray.get(k);
            // System.out.println(quake); 
        // }  
        
        // int indxlarge = indexOfLargest(largeray);
        // QuakeEntry mxquake = largeray.get(indxlarge);
        // System.out.println("indexof largest quake: "+indxlarge); 
        // System.out.println("largest quake: "+mxquake); 
        
        large5 = getLargest(largeray, 5);     
        for(int i=0; i<large5.size(); i++){
            //QuakeEntry quake = large5.get(i);
            System.out.println(large5.get(i)); 
        }
    }    

     public int indexOfLargest(ArrayList<QuakeEntry> data){
         double mxlarge = 0.0;
         int indx = 0;
         for(int k=0; k < data.size(); k++){
                QuakeEntry quake = data.get(k);
                double mag = quake.getMagnitude();
                if(mag > mxlarge) {
                    mxlarge = mag;
                    indx = k;                    
                } 
         }
         return indx;
     }
     
      public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> res = new ArrayList<QuakeEntry>();
        for(int j=0; j < howMany; j++) {
            int indxlarge = indexOfLargest(copy);            
            res.add(copy.get(indxlarge));           
            copy.remove(indxlarge);
        }
        return res;
    }
                
}
