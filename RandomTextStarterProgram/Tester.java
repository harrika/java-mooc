//kirya henry
import java.io.*;
import java.util.*;
import edu.duke.*;

public class Tester {    
    public void testGetFollows(){
        ArrayList<String> res;
        //= new ArrayList<String>();
        String tt = "this is a test yes this is a test.";
        MarkovOne markov = new MarkovOne(); 
        markov.setTraining(tt);
        //String ke = "t";        
        res = markov.getFollows("es");
        for (String s : res) {
            System.out.println(s);    
        } 
        System.out.println("-----------------------------------");  
        System.out.println("The size of follows: "+res.size());    
    }
    
    public void testGetFollowsWithFile(){
        ArrayList<String> ret;
        FileResource fr = new FileResource();
	String st = fr.asString();
	st = st.replace('\n', ' ');
	MarkovOne markov = new MarkovOne();		
	markov.setTraining(st);      
	ret = markov.getFollows("he");
	System.out.println("Number of followers: "+ret.size());    
    }	
    
}
