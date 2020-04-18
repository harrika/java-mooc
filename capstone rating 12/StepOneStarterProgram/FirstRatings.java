//kirya Henry
import edu.duke.*;
import java.util.*;
import java.io.*;
import org.apache.commons.csv.*;

public class FirstRatings{      
    
    public  ArrayList<Movie> loadMovies (String filocsv) {
        ArrayList<Movie> movlist = new ArrayList<Movie>();
        FileResource fr = new FileResource(filocsv);
        CSVParser parser = fr.getCSVParser();        
        for (CSVRecord rec : parser) {
            String id = rec.get("id");
            String title = rec.get("title");
            String year = rec.get("year");
            String country = rec.get("country");
            String genre = rec.get("genre");
            String director = rec.get("director");           
            String minutes = rec.get("minutes");
            String poster = rec.get("poster");
          Movie mv = new Movie(id, title, year, genre, director, country, poster,
            Integer.parseInt(rec.get("minutes").trim()));    
             
            movlist.add(mv);            
        }
        return movlist;        
    }
    
     public  ArrayList<Rater> loadRaters(String filocsv) {
          ArrayList<Rater> raters = new ArrayList<Rater>();
          ArrayList<String> raterz = new ArrayList<String>();  //create an arraylist of ids raterz           
          FileResource fr = new FileResource(filocsv);
          CSVParser parser = fr.getCSVParser();
          for (CSVRecord rec : parser) {              
              String ratid = rec.get("rater_id");
              String movid = rec.get("movie_id");
              String rating = rec.get("rating");
              String time = rec.get("time");
              double drating = Double.parseDouble(rec.get("rating").trim());
              if(!raterz.contains(ratid)){ //if ratid not in raterz 
                  Rater rr = new Rater(ratid);  //creates new rater class
                  raterz.add(ratid);                  
                  rr.addRating(movid, drating); //adds new ratings class
                  raters.add(rr);
              } else {  
                  //find Rater corresponding to id ratid and add rating to it
                  for (Rater rt: raters) {
                      if (rt.getID().equals(ratid)){                          
                          rt.addRating(movid, drating) ;
                       }
                  } //endfor
              } //end if_else
            } //end for  
            return raters;
        } //end method 
        
      public void testLoadRaters() {               
          //ArrayList<Rater> raterz = loadRaters("data/ratings_short.csv");
          ArrayList<Rater> raterz = loadRaters("data/ratings.csv");           
          System.out.println(raterz.size());           
          // for (Rater rr: raterz) {
              // System.out.print("rater id: "+rr.getID()+"   ");
              // System.out.println("No. ratings: "+rr.numRatings());              
              // ArrayList<String> movies = rr.getItemsRated(); //get list of movies rated
              // for (String mov: movies) {
                  // System.out.print("movie: "+mov+"      ");
                  // System.out.println("rating: "+rr.getRating(mov)); //rating given                    
              // }  
              // System.out.println("---------------------------------------------------");              
           // }
           //***************************num ratings for given rater id*****************************
           String ratidxx = "193";
           for (Rater zz: raterz) {
                if (zz.getID().equals(ratidxx)){ 
                    System.out.print("rater id: "+zz.getID()+" ===>  ");
                    System.out.println("No. ratings: "+zz.numRatings());        
                }
           }     
           //***********************************rater id with maxmum ratings***********************
           int maxrats = 0;
           for (Rater zz: raterz) {
               if (zz.numRatings() > maxrats){
                   maxrats = zz.numRatings();                   
               }
           }
           for (Rater pp: raterz) {
               if (pp.numRatings() == maxrats){
                   System.out.print("maxrater: "+pp.getID()+"              ");
                   System.out.println("ratings: "+maxrats);                   
               }
           }           
           //********************number of ratings for movie*********************
           int numraters = 0;
           String mvid = "1798709";
           for (Rater qq: raterz) {
               ArrayList<String> mvlst = qq.getItemsRated();
               if(mvlst.contains(mvid)){
                   numraters += 1;                
                }            
            }
            System.out.println("number of raters for "+mvid+" : "+numraters);
            //**********************different movies rated********************************
            ArrayList<String> mother = new ArrayList<String>();
            for (Rater ss: raterz) {
                ArrayList<String> lst = ss.getItemsRated();
                mother.addAll(lst);
            }                
            Set set = new HashSet(mother);
            System.out.println("different movies rated: "+set.size());
            //*********************************************************************************
      }
    
     public void testLoadMovies() {
         ArrayList<Movie> rmovlist;      
         HashMap<String, Integer> dmap = new HashMap<String, Integer>();
         //rmovlist = loadMovies("data/ratedmovies_short.csv");
         rmovlist = loadMovies("data/ratedmoviesfull.csv");
         System.out.println("Number of movies: "+rmovlist.size());
         int comgen = 0;
         int min150 = 0;
         int maxd = 0;
         String maxs = "";
         for (Movie mm: rmovlist) {             
             String gen = mm.getGenres();             
             String dr = mm.getDirector();
             String[] drs = dr.split(",");
             //String[] drs = dr.split("\\s+");
             for (String drkt: drs) {
                 if (dmap.containsKey(drkt)){
                     int vv = dmap.get(drkt);
                     vv += 1;
                     dmap.put(drkt, vv);
                 }else{
                     dmap.put(drkt, 1);                     
                 }             
             }
             for (String dd : dmap.keySet()) {
                 if (dmap.get(dd) > maxd){                     
                     maxd = dmap.get(dd);
                     maxs = dd;
                  }
             } 
             if (gen.contains("Comedy")){
                 comgen +=1;         
             }
             if (mm.getMinutes() > 150){
                 min150 +=1;         
             }             
         }
         for (String aa : dmap.keySet()) {
             if (dmap.get(aa) == maxd){ 
                 System.out.println("High max director: "+aa);
              } 
         }
         System.out.println("Highest number of movies directed: "+maxd); 
         System.out.println("Number of comdey genres: "+comgen); 
         System.out.println("minutes greater than 150: "+min150);         
     }
     
     
} //end class

