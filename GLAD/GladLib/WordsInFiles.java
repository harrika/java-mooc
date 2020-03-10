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
            //String wrd = teststring(wd); //testing word for non alpahabeticals at the end            
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
    
    // ======== baby file1 file2
    
        
    public String teststring(String mess){
        String newc = "";
        //String mess = "abcdefqrstuvwxyz!";
        //for (int k=0; k<mess.length(); k++){
            int k = mess.length()-1;
            //System.out.println(mess.charAt(k));
            char ch = mess.charAt(k);
            //System.out.println(ch);
            boolean bb = Character.isLetter(ch);
            if (!bb){
                //newc = substring(0,k);
                newc = mess.substring(0, k);
                return newc;
            }else{
                return mess;
            }
            //System.out.println(mess);
            //System.out.println("newc: "+newc);  
            //return newc;
        //}
    }
    
    
     public void testmenee(){ 
         buildWordFileMap();   
         int mm = 0;
         int n4 = 0;
         for (String ww: mapp.keySet()){
             int ss = mapp.get(ww).size(); 
             if (ss == 4){
                 n4 += 1;
             }
             //mm = mm+1;
         }
         //System.out.println("number words: "+mm);
         System.out.println("number in 4 files: "+n4);
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
    
    public void tester2sea(){  
        mapp.clear();
        ArrayList<String> aray = new ArrayList<String>();
        buildWordFileMap();          
        aray = mapp.get("tree");             
        for (String el : aray) {          
           System.out.println(el);      
       }
    }
    
     public void tester3(){  
        ArrayList<String> filz;
        int hwmany = 0;
        buildWordFileMap();                      
        for (String wd : mapp.keySet()) {               
           int nn = mapp.get(wd).size();
           if(nn == 4 ){
               System.out.println(wd);
               hwmany += 1; 
               //dwords.add(wd)
            }           
       }
       System.out.println("how many words for 4 files: "+hwmany);       
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
