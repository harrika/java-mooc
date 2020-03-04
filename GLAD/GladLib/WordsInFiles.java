import edu.duke.*;
import java.util.*;
import java.io.File; 

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> mapp;    
    
    public WordsInFiles() {
        mapp  = new HashMap<String, ArrayList<String>>();    
    }   
  
        
    public void addWordsFromFile(File f){        
        ArrayList<String> filo;
        FileResource resource = new FileResource(f);
        String ff = f.getName();
        for(String wrd : resource.words()){ //for wrd in file f         
            if (mapp.keySet().contains(wrd)) {            
                filo = mapp.get(wrd); //get the associated fiel array
                filo.add(ff); //add file f to filo       
            }else{
                //filo = create a new array of files and add file f to it
                filo = new ArrayList<String>();
                filo.add(ff); //add file f to filo                                                                            
            }   
         mapp.put(wrd,filo);
        }
    }
    
    public void buildWordFileMap(){   
        mapp.clear();
        DirectoryResource dr  = new DirectoryResource();
        for (File ff: dr.selectedFiles()){
            addWordsFromFile(ff);
        }
    }
    
    public int maxNumber(){        
        ArrayList<String> fil;
        int maxx = 0;
        for(String wrd : mapp.keySet()){ 
            fil = mapp.get(wrd); 
            int len = fil.size();
            if (len>maxx){
                maxx = len;
            }
        }
        return maxx;
    }
    
    public ArrayList wordsInNumFiles(int number){
         ArrayList<String> wards = new ArrayList<String>();
         for(String wrd : mapp.keySet()){ 
            int raylen = mapp.get(wrd).size();
            if (raylen == number){
                wards.add(wrd);            
            }            
        }
        return wards;
    }     
    
    public void tester(){   
        buildWordFileMap();   
        int maxnum = maxNumber();
        ArrayList<String> fill;
        //ArrayList<String> maxwards = wordsInNumFiles(maxnum);
        ArrayList<String> wards5 = wordsInNumFiles(4);
        System.out.println("words in 5 files: "+wards5.size());
        // for(String wod : wards5){
            // fill = mapp.get(wod); 
             // System.out.println("each word: "+wod+"       file: "+fill);        
        // }
        
        // for(String wrd : maxwards){
            // ArrayList<String> ray = mapp.get(wrd);
            // for(String wd : ray){
                // System.out.println("word: "+wrd+"       file: "+wd);
            // }
        // }
    }
    
              
} //end class
