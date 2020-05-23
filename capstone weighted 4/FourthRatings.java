//kirya henry watentena mukomba/
import java.util.*;

public class FourthRatings { 
            
    public static double getAverageByID(String id, int minimalRaters){ //to copy over
        ArrayList<Double> ratings = new ArrayList<Double>();        
        for(Rater rter : RaterDatabase.getRaters()) {
            if (rter.getRating(id) != -1){ //rating for movie exists
                ratings.add(rter.getRating(id));                
            }            
        }
        int sz = ratings.size();
        double totl = 0;
        for(Double data : ratings){
            totl += data;
        }        
        if (sz >= minimalRaters){
            return totl/sz;
        }
        return 0.0;
    }
    
    private static double dotProduct(Rater me, Rater r){        
        ArrayList<String> mitems = me.getItemsRated(); //get list of items from me
        double dotprod = 0;
        for (String item : mitems) {  //for each item in meitems
            if (r.hasRating(item)) {
                double newrateme = 5 - me.getRating(item);
                double newrater = 5 - r.getRating(item);
                double dot = newrateme*newrater;
                dotprod += dot;
            }
        }
        return dotprod;         
    }
    
      private static ArrayList<Rating> getSimilarities(String id){ //id of rater to compare to others in dbase
          ArrayList<Rating> lst = new ArrayList<Rating>();                   
          ArrayList<Rater> myRaters = RaterDatabase.getRaters();
          Rater me = RaterDatabase.getRater(id);          
          double dp = 0;
          for (Rater rt: myRaters){
              if (!id.equals(rt.getID())){
                  dp = dotProduct(me, rt);
              }
              if (dp>0){
                    Rating rating = new Rating(rt.getID(), dp);
                    lst.add(rating);
              }          
          }
          Collections.sort(lst, Collections.reverseOrder());
          return lst; // rater id me - [id1:5, id7:3, id3:1]
        }   
        
        public static ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){                            
              ArrayList<Rating> simlist = getSimilarities(id);
              int ss = simlist.size();              
              List<Rating> lstop = simlist.subList(0,numSimilarRaters);
              ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());              
              ArrayList<Rating> movratings = new ArrayList<Rating>();
              for (String mid: movies) { 
                   double totavg = 0.0;
                   int min = 0;
                   for (Rating rt: lstop){
                       String rterid = rt.getItem();
                       Rater rter = RaterDatabase.getRater(rterid);                        
                       if (rter.getRating(mid) != -1){ //if rating for movie exists
                           double mrating = rter.getRating(mid);
                           double dp = rt.getValue();
                           double wav = mrating*dp;
                           totavg += wav;  
                           min +=1;
                       }                       
                   }
                   if(min >= minimalRaters){                       
                       double weighted = totavg/min;
                       Rating rateen = new Rating(mid, weighted);
                       movratings.add(rateen);                    
                   }                   
              }
              return movratings;              
          }
     
public static ArrayList<Rating> getSimilarRatingsByFilter(String id,int numSimilarRaters,int minimalRaters,Filter f){                    
          ArrayList<Rating> simlist = getSimilarities(id);
          int ss = simlist.size();
          List<Rating> lstop = simlist.subList(0,numSimilarRaters); // *** are they sorted??          
          ArrayList<String> movies = MovieDatabase.filterBy(f); 
          ArrayList<Rating> movratings = new ArrayList<Rating>();
          for (String mid: movies) { 
               double totavg = 0.0;
               int min = 0;
               for (Rating rt: lstop){
                   String rterid = rt.getItem();                   
                   Rater rter = RaterDatabase.getRater(rterid);
                   if (rter.getRating(mid) != -1){ //if rating for movie exists
                       double mrating = rter.getRating(mid);
                       double dp = rt.getValue();
                       double wav = mrating*dp;
                       totavg += wav;  
                       min +=1;
                   }                       
               }
               if(min >= minimalRaters){                   
                   double weighted = totavg/min;
                   Rating rateen = new Rating(mid, weighted);
                   movratings.add(rateen);                    
               }                   
          }
          return movratings;
          //return Collections.reverse(movratings);
      }
    
public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){ //to copy over
    ArrayList<Rating> retings = new ArrayList<Rating>(); 
     ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);         
     for (String id: movies) {             
         double avg = getAverageByID(id, minimalRaters);
         if (avg != 0.0){
              Rating rting = new Rating(id, avg);
              retings.add(rting);
         }
      }
      return retings;
}

 public static ArrayList<Rating> getAverageRatings(int minimalRaters){ //to copy over
     ArrayList<Rating> retings = new ArrayList<Rating>(); 
     ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());         
     for (String id: movies) {             
         double avg = getAverageByID(id, minimalRaters);
         if (avg != 0.0){
              Rating rting = new Rating(id, avg);
              retings.add(rting);
         }
      }
      return retings;
 }     
        
} // class end
