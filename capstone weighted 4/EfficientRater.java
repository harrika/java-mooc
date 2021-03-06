//henry kirya

import java.util.*;

public class EfficientRater implements Rater {
    private String myID;
    //private ArrayList<Rating> myRatings;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        if(myRatings.containsKey(item)){
            return true;
        }else{
            return false;
        }
        //return myRatings.containsKey(item);        
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        if (hasRating(item)){
            Rating  rr = myRatings.get(item);
            double reting = rr.getValue();
            return reting;
        }
        return -1;
    }
        
    public HashMap<String,Rating> getMyRatings(){
        return myRatings;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String movie : myRatings.keySet()){
            list.add(myRatings.get(movie).getItem());
        }
        // for(int k=0; k < myRatings.size(); k++){           
                // list.add(myRatings.get(k).getItem());                     
        // }
        
        return list;
    }
}
