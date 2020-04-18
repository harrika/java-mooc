//henry 

public class MinutesFilter implements Filter {
    private int minmn;
    private int maxmn;
    	
    public MinutesFilter(int min, int max) {
    	minmn = min;
    	maxmn = max;
    }
    
    @Override
    public boolean satisfies(String id) {
        int mm = MovieDatabase.getMinutes(id);
    	return (( mm >= minmn) && (mm <= maxmn));
    }    	
}

