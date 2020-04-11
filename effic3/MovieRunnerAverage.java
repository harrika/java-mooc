//kirya henry watentena mukomba/
import java.util.*;

public class MovieRunnerAverage {    
    
     public void printAverageRatings(){         
          //SecondRatings sr = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
          SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
          int numovies =  sr.getMovieSize();
          int numraters = sr.getRaterSize();
          System.out.println("Number of movies: "+numovies);
          System.out.println("Number of raters: "+numraters);   
          ArrayList<Rating> rets =  sr.getAverageRatings(3);
          Collections.sort(rets);
          for (Rating ret: rets){
              String iid = ret.getItem();
              String titl = sr.getTitle(iid);              
              System.out.println(ret.getValue()+"  "+titl);              
            }          
     }
     
       public void getAverageRatingOneMovie(){
            //SecondRatings sr = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
            SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
            //ArrayList<Double> ratinray = new ArrayList<Double>();            
            String titl = "The Godfather";
            String aid = sr.getID(titl);
            double retin = sr.getAverageByID(aid,1);            
            // for (Rater rater: sr.myRaters){
                // double ratin = rater.getRating(aid); 
                    // if (ratin != -1){
                        // ratinray.add(ratin);
                    // }  
                // }            
            // int ss = ratinray.size();
            // double tot = 0;
            // for(Double dat : ratinray){
              // tot += dat;
            //}
            System.out.println("Average rating for movie '"+titl+"': "+retin);       
        }
       
       
       
}

