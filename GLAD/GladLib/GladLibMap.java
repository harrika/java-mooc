import edu.duke.*;
import java.util.*;

public class GladLibMap {

    private ArrayList<String> usedList;
    private ArrayList<ArrayList<String>> master;
    private HashMap<String, ArrayList<String>> meMap;
    
    private Random myRandom;
    private int subbed = 0;
    public int raytot = 0;
    private ArrayList<String> raylist; 
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";
    //private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        meMap = new HashMap<String, ArrayList<String>>();
        master = new ArrayList<ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        meMap = new HashMap<String, ArrayList<String>>();
        master = new ArrayList<ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {   
        usedList = new ArrayList<String>();
        String[] labels = {"adjective", "noun", "color",
                        "country", "name", "animal",
                        "timeframe", "verb", "fruit" };
        for (String s: labels){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            meMap.put(s, list);
          }
        
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }        
        return randomFrom(meMap.get(label));
        // return "**UNKNOWN**";        
    }
    
    public boolean inlist(String wrd){       
        int idx = usedList.indexOf(wrd);
        if (idx == -1){
            return false;                      
        }else{
            return true;        
        }
    }    

    public int totalWordsInMap(){
        int totwrds = 0;
        for (String labl : meMap.keySet()){
             int rayleng = meMap.get(labl).size();
             totwrds += rayleng;           
        }   
        return totwrds;
    }       
    
     
    private String processWord(String w){           
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String susu = w.substring(first+1,last);        
        String sub = getSubstitute(w.substring(first+1,last));
        while (inlist(sub)){
            sub = getSubstitute(w.substring(first+1,last));       
          }
        usedList.add(sub);        
        String lebel = w.substring(first+1,last);
        ArrayList<String> susuray = meMap.get(susu);
        //System.out.println("susuray:  "+susuray);  
        if ( !master.contains(susuray) && (susuray!= null)){
            raytot += susuray.size(); 
            master.add(susuray);
        }          
            
        subbed += 1;
        return prefix+sub+suffix;
    }
        
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        usedList.clear();
        subbed = 0;
        raytot = 0;
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        //System.out.println("\n words replaced: "+subbed);       
        System.out.println("\n All words in map: "+totalWordsInMap()); 
        System.out.println("Total words considered in map: "+raytot);       
    }
    


}
