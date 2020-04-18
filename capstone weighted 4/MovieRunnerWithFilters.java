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
       
    } //end method  byYear    
    
    public void printAverageRatingsByGenre(){ 
        MovieDatabase mbase = new MovieDatabase();
        mbase.initialize("ratedmovies_short.csv");   
        ThirdRatings  sr = new ThirdRatings("data/ratings_short.csv");        
        GenreFilter movgenre = new  GenreFilter("Crime");
        ArrayList<Rating> rets =  sr.getAverageRatingsByFilter(1, movgenre);
        int numovies = rets.size();
        System.out.println("Number of movies found: "+numovies);
        for (Rating ret: rets){
             String mvid = ret.getItem(); 
             double rating = ret.getValue();
             String title = mbase.getTitle(mvid);
             String genrs = mbase.getGenres(mvid);
             System.out.println(rating+"   "+title);
             System.out.println(genrs);
             System.out.println();             
        }         
       
    } //end method byGenre
    
     public void printAverageRatingsByMinutes(){ 
        MovieDatabase mbase = new MovieDatabase();
        mbase.initialize("ratedmovies_short.csv");   
        ThirdRatings  sr = new ThirdRatings("data/ratings_short.csv");     //initialize ratings
        
        MinutesFilter  movmins = new  MinutesFilter(110,170); //create filter
        ArrayList<Rating> rets =  sr.getAverageRatingsByFilter(1, movmins); //use filter in average ratings
        
        //printing
        int numovies = rets.size();
        System.out.println("Number of movies found: "+numovies);
        for (Rating ret: rets){
             String mvid = ret.getItem(); 
             double rating = ret.getValue();
             String title = mbase.getTitle(mvid);
             int runn = mbase.getMinutes(mvid);            
             //rating, its running time, and its title on one line. 
             System.out.println(rating+"   "+runn+" mins     "+title);                 
        }                
    } //end method by minutes
    
    public void printAverageRatingsByDirectors(){ 
        MovieDatabase mbase = new MovieDatabase();
        mbase.initialize("ratedmovies_short.csv");   
        ThirdRatings  sr = new ThirdRatings("data/ratings_short.csv");
        
        DirectorsFilter movidirs = new DirectorsFilter("Charles Chaplin, Michael Mann, Spike Jonze");
        ArrayList<Rating> rets =  sr.getAverageRatingsByFilter(1, movidirs);
        
        int numovies = rets.size();
        System.out.println("Number of movies found: "+numovies);        
        for (Rating ret: rets){
             String mvid = ret.getItem(); 
             double rating = ret.getValue();
             String title = mbase.getTitle(mvid);             
             String drks = mbase.getDirector(mvid);            
             System.out.println(rating+"    "+title);
             System.out.println(drks); 
             System.out.println();    
        }         
       
    } //end method by directors
    
     public void printAverageRatingsByYearAfterAndGenre(){ 
        MovieDatabase mbase = new MovieDatabase();
        mbase.initialize("ratedmovies_short.csv");   
        ThirdRatings  sr = new ThirdRatings("data/ratings_short.csv");        
        
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
    } //end method all.
    
     public void printAverageRatingsByDirectorsAndMinutes(){ 
        MovieDatabase mbase = new MovieDatabase();
        mbase.initialize("ratedmovies_short.csv");   
        ThirdRatings  sr = new ThirdRatings("data/ratings_short.csv");  
        //create filters        
        MinutesFilter  movmins = new  MinutesFilter(30,170);
        DirectorsFilter movidirs = new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola");
        //combine filters
        AllFilters af = new AllFilters();        
        af.addFilter(movmins);
        af.addFilter(movidirs);
        
        ArrayList<Rating> rets =  sr.getAverageRatingsByFilter(1, af);        
        int numovies = rets.size();
        System.out.println("Number of movies found: "+numovies);        
        for (Rating ret: rets){
             String mvid = ret.getItem(); 
             double rating = ret.getValue();
             String hwlong = mbase.getTitle(mvid);             
             String drks = mbase.getDirector(mvid);
             System.out.println(rating+"    Time:"+hwlong);
             System.out.println(drks); 
             System.out.println();    
        }        
    } //end method all.
    // for each movie, print its rating, its time length,
    //and its title on one line, and all its directors on the next line.
    
} //end class MovieRunnerWithFilters

//5873
 // print its rating and its title on one line, and its genres on the next line




