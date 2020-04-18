// Henry kirya mukomba
import java.util.*;

public class MovieRunnerSimilarRatings  {    

    public void printAverageRatings(){      
      //FourthRatings  sr = new FourthRatings("data/ratings_short.csv");
      FourthRatings  sr = new FourthRatings();
      //int numraters = sr.getRaterSize();
      MovieDatabase mbase = new MovieDatabase();      
      mbase.initialize("ratedmovies_short.csv");      
      //System.out.println("Number of raters: "+numraters);  
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
    
     public void printSimilarRatings (){    
        FourthRatings  sr = new FourthRatings();       
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("data/ratings.csv"); 
        ArrayList<Rating> rets = sr.getSimilarRatings("65",20,5);
        for (Rating ret: rets){
             String mvid = ret.getItem(); 
             double rating = ret.getValue();             
             String title = MovieDatabase.getTitle(mvid);             
             System.out.println(title+"    "+rating);              
        }            
    }
    
     public void printSimilarRatingsByGenre (){    
        FourthRatings  sr = new FourthRatings();       
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("data/ratings.csv"); 
        String genre = "Action";
        ArrayList<Rating> rets = sr.getSimilarRatings("65",20,5);
        for (Rating ret: rets){
             String mvid = ret.getItem(); 
             double rating = ret.getValue();             
             String title = MovieDatabase.getTitle(mvid);  
             String genres = MovieDatabase.getGenres(mvid);             
             if (genres.contains(genre)){
                 System.out.println(title+"    "+rating);
                 System.out.println(genres);
                 System.out.println("--------------------------------------------------------------");
             }
        }    
    }    
    
    public void printSimilarRatingsByDirector (){    
        FourthRatings  sr = new FourthRatings();       
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("data/ratings.csv"); 
        //String drk = "Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone";
        String drk1 = "Clint Eastwood";
        String drk2 = "Sydney Pollack";
        String drk3 = "David Cronenberg";
        String drk4 = "Oliver Stone";        
        ArrayList<Rating> rets = sr.getSimilarRatings("1034",10,3);        
        for (Rating ret: rets){
             String mvid = ret.getItem(); 
             double rating = ret.getValue();             
             String title = MovieDatabase.getTitle(mvid);                           
             String drktors = MovieDatabase.getDirector(mvid);             
         if (drktors.contains(drk1)||drktors.contains(drk2)||drktors.contains(drk3)||drktors.contains(drk4)){
                 System.out.println(title+"    "+rating);
                 System.out.println(drktors);
                 System.out.println("--------------------------------------------------------------");
         }    
        }
   }
        
    
     public void printAverageRatingsByYearAfterAndGenre(){ 
        MovieDatabase mbase = new MovieDatabase();
        mbase.initialize("ratedmovies_short.csv");   
        FourthRatings  sr = new FourthRatings();        
        //FourthRatings  sr = new FourthRatings("data/ratings_short.csv");        
        
        YearAfterFilter yr1980 = new  YearAfterFilter(1980);
        GenreFilter movgenre = new  GenreFilter("Romance");
        AllFilters af = new AllFilters();
        af.addFilter(yr1980);
        af.addFilter(movgenre);
        
        ArrayList<Rating> rets =  sr.getAverageRatingsByFilter(1, af);        
        int numovies = rets.size();
        System.out.println("Number of movies found: "+numovies);        
        for (Rating ret: rets){
             String mvid = ret.getItem(); 
             double rating = ret.getValue();
             String title = mbase.getTitle(mvid);
             String genres = mbase.getGenres(mvid);
             int year = mbase.getYear(mvid);             
             System.out.println(rating+"  "+year+"  "+title);
             System.out.println(genres); 
             System.out.println();    
        }        
    }
} //end class

   
  