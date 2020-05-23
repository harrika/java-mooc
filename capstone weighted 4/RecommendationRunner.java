//henry kirya

import java.util.*;

public class RecommendationRunner implements Recommender {
    
    public ArrayList<String> getItemsToRate() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        // GenreFilter dgenre = new  GenreFilter("Drama");
        // GenreFilter cgenre = new  GenreFilter("Crime");  
        // MinutesFilter fminute = new MinutesFilter(80, 160);
        TrueFilter tf = new TrueFilter();
        // AllFilters af = new AllFilters();
        // af.addFilter(dgenre);
        // af.addFilter(cgenre);
        // af.addFilter(fminute);
        ArrayList<String> movies = MovieDatabase.filterBy(tf); 
        Collections.shuffle(movies);
        ArrayList<String> mv2 = new ArrayList<String>();
        for(int i = 0; i < 20; i ++) {
            mv2.add(movies.get(i));
        }
        return mv2;     
    } //end getItemsToRate
    
    public void printRecommendationsFor(String webRaterID) {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("data/ratings.csv");
        GenreFilter fgenre = new  GenreFilter("Drama");
        GenreFilter f2genre = new  GenreFilter("Crime");
        MinutesFilter fminute = new MinutesFilter(80, 160);
        AllFilters af = new AllFilters();
        af.addFilter(fgenre);
        af.addFilter(f2genre);
        af.addFilter(fminute);    
        ArrayList<Rating> rets = FourthRatings.getSimilarRatingsByFilter(webRaterID,15,2,af);
        if(rets.size() == 0) {
            System.out.println("<p>No recommendations for you.</p>");
            System.exit(1);
        }
        
        System.out.println("<style>.tebo{color:#2b2d2f;border: 1px solid black;}");
        System.out.println(".thd{padding:10px;}");
        System.out.println("</style>");                
       
        System.out.print("<table class=\"tebo\">");
        System.out.print("<tr>");
            System.out.print("<th class=\"thd\">title</th>");
            System.out.print("<th class=\"thd\">length</th>");
            System.out.print("<th class=\"thd\">genre</th>");
            System.out.print("<th class=\"thd\">Rating</th>");            
        System.out.print("</tr>");
        
        //for (Rating ret: rets){
        for(int i = 0; i < 15; i ++) {
            String mvid = rets.get(i).getItem();
            double rating = rets.get(i).getValue();
            System.out.println("<tr>");
            System.out.println("<td>"+MovieDatabase.getTitle(mvid)+
            "</td><td>"+MovieDatabase.getMinutes(mvid)+
            "</td><td>"+MovieDatabase.getGenres(mvid)+
            "</td><td>"+mvid+"</td>");
             System.out.println("</tr>");
            
           // String mvid = ret.getItem(); 
           // double rating = ret.getValue();             
           // String title = MovieDatabase.getTitle(mvid);
           // String genres = MovieDatabase.getGenres(mvid);
           // int mins  = MovieDatabase.getMinutes(mvid);  
           // String minns = Integer.toString(mins); 
            // System.out.print("<tr>");
            // System.out.print("<td class=\"thd\">"+title+"</td>");
            // System.out.print("<td class=\"thd\">"+minns+"</td>");
            // System.out.print("<td class=\"thd\">"+genres+"</td>");
            // System.out.print("<td class=\"thd\">"+rating+"</td>");            
            // System.out.print("</tr>");
        } //end for-loop
        
        System.out.print("</table>");
        //System.out.print("</html>");
     } //printRecommendationsFor    
    
} //end class

//============================================================================================
// <td>"+(i+1)+"</td>

    