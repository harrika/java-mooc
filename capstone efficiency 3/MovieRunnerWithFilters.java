// Henry kirya mukomba
import java.util.*;

public class MovieRunnerWithFilters  {    

    public void printAverageRatings(){      
      //ThirdRatings  sr = new ThirdRatings("data/ratings_short.csv");
      ThirdRatings  sr = new ThirdRatings("data/ratings.csv");      
      int numraters = sr.getRaterSize();      
      //MovieDatabase.initialize("ratedmovies_short.csv");   
      MovieDatabase.initialize("ratedmoviesfull.csv"); 
      System.out.println("Number of raters: "+numraters);  
      //int numovies =  mbase.size();
      int numovies =  MovieDatabase.size();    
      System.out.println("Number of movies: "+numovies);   
      ArrayList<Rating> rets =  sr.getAverageRatings(30); //minimal raters = 1      
      System.out.println("movies with ratings: "+ rets.size());
      
      Collections.sort(rets); //print average ratings
      for (Rating ret: rets){
          String iid = ret.getItem();         
          String tit = MovieDatabase.getTitle(iid);
          System.out.print(ret.getValue());
          System.out.println("   "+tit);
      }      
    }
   
    public void printAverageRatingsByYear(){         
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings  sr = new ThirdRatings("data/ratings.csv");
        //ThirdRatings  sr = new ThirdRatings("data/ratings_short.csv");
        YearAfterFilter yr2000 = new  YearAfterFilter(2000);        
        ArrayList<Rating> rets =  sr.getAverageRatingsByFilter(20, yr2000);
        int numovies = rets.size();
        System.out.println("Number of movies found: "+numovies);
        for (Rating ret: rets){
             String mvid = ret.getItem(); 
             double rating = ret.getValue();
             String title = MovieDatabase.getTitle(mvid);
             int year = MovieDatabase.getYear(mvid);
             System.out.println(rating+"   "+year+"   "+title);
        }         
       
    } //end method  byYear    
    
    public void printAverageRatingsByGenre(){ 
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings  sr = new ThirdRatings("data/ratings.csv");        
        //MovieDatabase.initialize("ratedmovies_short.csv");   
        //ThirdRatings  sr = new ThirdRatings("data/ratings_short.csv");        
        GenreFilter movgenre = new  GenreFilter("Comedy");
        ArrayList<Rating> rets =  sr.getAverageRatingsByFilter(20, movgenre);
        int numovies = rets.size();
        System.out.println("Number of movies found: "+numovies);
        for (Rating ret: rets){
             String mvid = ret.getItem(); 
             double rating = ret.getValue();
             String title = MovieDatabase.getTitle(mvid);
             String genrs = MovieDatabase.getGenres(mvid);
             System.out.println(rating+"   "+title);
             System.out.println(genrs);
             System.out.println();             
        }         
       
    } //end method byGenre
    
     public void printAverageRatingsByMinutes(){
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings  sr = new ThirdRatings("data/ratings.csv");  
        //MovieDatabase.initialize("ratedmovies_short.csv");   
        //ThirdRatings  sr = new ThirdRatings("data/ratings_short.csv");        
        MinutesFilter  movmins = new  MinutesFilter(105,135); //create filter
        ArrayList<Rating> rets =  sr.getAverageRatingsByFilter(5, movmins); //use filter in average ratings
        int numovies = rets.size();
        System.out.println("Number of movies found: "+numovies);
        for (Rating ret: rets){
             String mvid = ret.getItem(); 
             double rating = ret.getValue();
             String title = MovieDatabase.getTitle(mvid);
             int runn = MovieDatabase.getMinutes(mvid);            
             //rating, its running time, and its title on one line. 
             System.out.println(rating+"   "+runn+" mins     "+title);                 
        }                
    } //end method by minutes
    
    public void printAverageRatingsByDirectors(){ 
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings  sr = new ThirdRatings("data/ratings.csv");          
        //MovieDatabase.initialize("ratedmovies_short.csv");   
        //ThirdRatings  sr = new ThirdRatings("data/ratings_short.csv");        
        DirectorsFilter movidirs = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> rets =  sr.getAverageRatingsByFilter(4, movidirs);
        
        int numovies = rets.size();
        System.out.println("Number of movies found: "+numovies);        
        for (Rating ret: rets){
             String mvid = ret.getItem(); 
             double rating = ret.getValue();
             String title = MovieDatabase.getTitle(mvid);             
             String drks = MovieDatabase.getDirector(mvid);            
             System.out.println(rating+"    "+title);
             System.out.println(drks); 
             System.out.println();    
        }         
       
    } //end method by directors
    
     public void printAverageRatingsByYearAfterAndGenre(){ 
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings  sr = new ThirdRatings("data/ratings.csv");        
        //MovieDatabase.initialize("ratedmovies_short.csv");   
        //ThirdRatings  sr = new ThirdRatings("data/ratings_short.csv");        
        YearAfterFilter yr1990 = new  YearAfterFilter(1990);
        GenreFilter movgenre = new  GenreFilter("Drama");        
        AllFilters af = new AllFilters();
        af.addFilter(yr1990);
        af.addFilter(movgenre);
        
        ArrayList<Rating> rets =  sr.getAverageRatingsByFilter(8, af);        
        int numovies = rets.size();
        System.out.println("Number of movies found: "+numovies);        
        for (Rating ret: rets){
             String mvid = ret.getItem(); 
             double rating = ret.getValue();
             String title = MovieDatabase.getTitle(mvid);
             String genres = MovieDatabase.getGenres(mvid);
             int year = MovieDatabase.getYear(mvid);             
             System.out.println(rating+"  "+year+"  "+title);
             System.out.println(genres); 
             System.out.println();    
        }        
    } //end method all.
    
     public void printAverageRatingsByDirectorsAndMinutes(){ 
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings  sr = new ThirdRatings("data/ratings.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");   
        //ThirdRatings  sr = new ThirdRatings("data/ratings_short.csv");        
        MinutesFilter  movmins = new  MinutesFilter(90,180);
        DirectorsFilter movidirs = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        //combine filters
        AllFilters af = new AllFilters();        
        af.addFilter(movmins);
        af.addFilter(movidirs);        
        ArrayList<Rating> rets =  sr.getAverageRatingsByFilter(3, af);        
        int numovies = rets.size();
        System.out.println("Number of movies found: "+numovies);        
        for (Rating ret: rets){
             String mvid = ret.getItem(); 
             double rating = ret.getValue();
             //String hwlong = MovieDatabase.getTitle(mvid); 
             int hwlong = MovieDatabase.getMinutes(mvid);        
             String drks = MovieDatabase.getDirector(mvid);
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




