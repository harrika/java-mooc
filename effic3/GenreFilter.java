//henry 

public class GenreFilter implements Filter {
    private String dagenre;
	
    public GenreFilter(String genre) {
    	dagenre = genre;
    }
    
    @Override
    public boolean satisfies(String id) {
    	return MovieDatabase.getGenres(id).contains(dagenre);
    	// return MovieDatabase.getGenres(id) == dagenre;
    	//return true;
    }

}
