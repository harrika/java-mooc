// Henry kirya mukomba
import java.util.*;

public class MovieRunnerWithFilters  {    

    public void printAverageRatings(){      
      ThirdRatings  sr = new ThirdRatings("data/ratings_short.csv");      
      int numraters = sr.getRaterSize();
      MovieDatabase mbase = new MovieDatabase();
      mbase.initialize("ratedmovies_short.csv");      
      System.out.println("Number of raters: "+numraters);  
      int numovies =  mbase.size();
      System.out.println("Number of movies: "+numovies);   
      ArrayList<Rating> rets =  sr.getAverageRatings(1);      
      System.out.println("movies with ratings: "+ rets.size());
      
      Collections.sort(rets); //print average ratings
      for (Rating ret: rets){
          String iid = ret.getItem();         
          String tit = mbase.getTitle(iid);
          System.out.print(ret.getValue());
          System.out.println("   "+tit);
      }      
    }
   
    public void printAverageRatingsByYear(){ 
        MovieDatabase mbase = new MovieDatabase();
        mbase.initialize("ratedmovies_short.csv");   
        ThirdRatings  sr = new ThirdRatings("data/ratings_short.csv");
        YearAfterFilter yr2000 = new  YearAfterFilter(2000);
        ArrayList<Rating> rets =  sr.getAverageRatingsByFilter(1, yr2000);
        int numovies = rets.size();
        System.out.println("Number of movies found: "+numovies);
        for (Rating ret: rets){
             String mvid = ret.getItem(); 
             double rating = ret.getValue();
             String title = mbase.getTitle(mvid);
             int year = mbase.getYear(mvid);
             System.out.println(rating+"   "+year+"   "+title);
        }         
       
    } //end method   
} //end class

//5873




