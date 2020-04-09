//kirya henry watentena mukomba/
import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {        
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr. loadRaters(ratingsfile);        
    }
    
    public int getMovieSize(){
        return myMovies.size();
    }  
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters){ //movie id
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
    
     public ArrayList<Rating> getAverageRatings(int minimalRaters){
         ArrayList<Rating> retings = new ArrayList<Rating>(); 
         for (Movie mm: myMovies){
             String id = mm.getID(); 
             double avg = getAverageByID(id, minimalRaters);
             if (avg != 0.0){
                  Rating rting = new Rating(id, avg);
                  retings.add(rting);
             }
          }
          return retings;
     }
     
     public String getTitle(String id){         
         for (Movie mm: myMovies){
             if (id.equals(mm.getID())){         
                 return mm.getTitle();                
              }
         }
         return "no movie with id: "+id+" found";          
     }
     
     public String getID(String title){
         for (Movie mm: myMovies){
             if (title.equals(mm.getTitle())){
                 return mm.getID();                 
             }
          }
          return "no such title found!";
     }
    
} // class end
