// Henry kirya mukomba
import java.util.*;

public class MovieRunnerSimilarRatings  {    

    public void printAverageRatings(){      
      //FourthRatings  sr = new FourthRatings("data/ratings_short.csv");
      //FourthRatings  sr = new FourthRatings();
      //int numraters = sr.getRaterSize();
      //MovieDatabase mbase = new MovieDatabase();      
      MovieDatabase.initialize("ratedmoviesfull.csv");  
      
      //System.out.println("Number of raters: "+numraters);  
      int numovies =  MovieDatabase.size();
      System.out.println("Number of movies: "+numovies);   
      ArrayList<Rating> rets =  FourthRatings.getAverageRatings(1);      
      System.out.println("movies with ratings: "+ rets.size());
      
      Collections.sort(rets); //print average ratings
      for (Rating ret: rets){
          String iid = ret.getItem();         
          String tit = MovieDatabase.getTitle(iid);
          System.out.print(ret.getValue());
          System.out.println("   "+tit);
      }      
    }     
    
     public void printSimilarRatings (){    
        //FourthRatings  sr = new FourthRatings();       
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("data/ratings.csv"); 
        ArrayList<Rating> rets = FourthRatings.getSimilarRatings("71",20,5);
        for (Rating ret: rets){
             String mvid = ret.getItem(); 
             double rating = ret.getValue();             
             String title = MovieDatabase.getTitle(mvid);             
             System.out.println(title+"    "+rating);              
        }            
    }
    
     public void printSimilarRatingsByGenre (){    
        //FourthRatings  sr = new FourthRatings();       
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("data/ratings.csv");
        GenreFilter fgenre = new  GenreFilter("Mystery");
        ArrayList<Rating> rets = FourthRatings.getSimilarRatingsByFilter("964",20,5,fgenre);        
        for (Rating ret: rets){
             String mvid = ret.getItem(); 
             double rating = ret.getValue();             
             String title = MovieDatabase.getTitle(mvid);  
             String genres = MovieDatabase.getGenres(mvid);            
                 System.out.println(title+"    "+rating);
                 System.out.println(genres);
                 System.out.println("--------------------------------------------------------------");             
        }    
    }  
    
    public void printSimilarRatingsByDirector (){    
        FourthRatings  sr = new FourthRatings();       
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("data/ratings.csv");        
     DirectorsFilter fdrkt = new  DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> rets = sr.getSimilarRatingsByFilter("120",10,2,fdrkt);        
        for (Rating ret: rets){
             String mvid = ret.getItem(); 
             double rating = ret.getValue();             
             String title = MovieDatabase.getTitle(mvid);  
             String directors = MovieDatabase.getDirector(mvid);            
                 System.out.println(title+"    "+rating);
                 System.out.println(directors);
                 System.out.println("--------------------------------------------------------------");             
        }    
    }  
    
    public void printSimilarRatingsByGenreAndMinutes (){    
        //FourthRatings  sr = new FourthRatings();       
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("data/ratings.csv");
        GenreFilter fgenre = new  GenreFilter("Drama");
        MinutesFilter fminute = new MinutesFilter(80, 160);
        AllFilters af = new AllFilters();
        af.addFilter(fgenre);
        af.addFilter(fminute);    
        ArrayList<Rating> rets = FourthRatings.getSimilarRatingsByFilter("168",10,3,af);        
        for (Rating ret: rets){
             String mvid = ret.getItem(); 
             double rating = ret.getValue();             
             String title = MovieDatabase.getTitle(mvid);
             String genres = MovieDatabase.getGenres(mvid);
             int mins  = MovieDatabase.getMinutes(mvid);
             System.out.println(title+"    "+mins+"     "+rating);
             System.out.println(genres);
             System.out.println("--------------------------------------------------------------");             
        }    
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes (){    
        //FourthRatings  sr = new FourthRatings();       
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("data/ratings.csv");        
        YearAfterFilter fyear = new  YearAfterFilter(1975);
        MinutesFilter fminute = new MinutesFilter(70, 200);
        AllFilters af = new AllFilters();
        af.addFilter(fyear);
        af.addFilter(fminute);    
        ArrayList<Rating> rets = FourthRatings.getSimilarRatingsByFilter("314",10,5,af);
        
        for (Rating ret: rets){
             String mvid = ret.getItem(); 
             double rating = ret.getValue();             
             String title = MovieDatabase.getTitle(mvid);             
             int mins  = MovieDatabase.getMinutes(mvid);
             int year  = MovieDatabase.getYear(mvid);             
             System.out.println(title+"    "+year+"  "+mins+"     "+rating);
             
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

   
  