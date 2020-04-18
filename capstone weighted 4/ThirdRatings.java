//kirya henry watentena mukomba/
import java.util.*;

public class ThirdRatings {
    //private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("data/ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {        
        FirstRatings fr = new FirstRatings();       
        myRaters = fr.loadRaters(ratingsfile);        
    }    
   
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters){ 
        ArrayList<Double> ratings = new ArrayList<Double>(); 
        for (Rater rter: myRaters){            
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
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
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
    
     public ArrayList<Rating> getAverageRatings(int minimalRaters){
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
