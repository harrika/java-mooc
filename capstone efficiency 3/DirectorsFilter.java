//henry 

public class DirectorsFilter  implements Filter {
    private String dirks;	
    public DirectorsFilter(String directors) {
    	dirks = directors;
    }
    
    @Override
    public boolean satisfies(String id) {
    	String[] dirkray = dirks.split(",");
    	for (String dr: dirkray) { 
    	    dr = dr.trim();
    	    if(MovieDatabase.getDirector(id).contains(dr)){
    	        return true;
    	    }    	   
    	}
    	return false;    	
    }

}
