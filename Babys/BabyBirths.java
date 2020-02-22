import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    
    public void printNames() {        
        FileResource fr = new FileResource();
        for (CSVRecord rec: fr.getCSVParser(false)){
            int numborn = Integer.parseInt(rec.get(2));
            System.out.println("Name: "+ rec.get(0)+
                               "Gender: "+ rec.get(1));
                               
        }
    } //end method
    
    public void totalBirths(FileResource fr) { 
        int totals = 0;    
        int totboys = 0;
        int totgals = 0;
        for (CSVRecord rec: fr.getCSVParser(false)){
            int numborn = Integer.parseInt(rec.get(2));
            totals += numborn;   
            if (rec.get(1).equals("M")) {
                totboys += numborn;            
            }else{
                totgals += numborn;
            }
        }
        System.out.println("Total births: "+ totals);
        System.out.println("Total boyz: "+ totboys);
        System.out.println("Total Gals: "+ totgals);
    } //end method
    
   
    
     public int getRank(int year, String name, String gender) { //gender: M or F
         FileResource fr = new FileResource("babynames/by_year/yob"+year+".csv");
         int rankboyz = 0;         
         int rankgalz = 0;
         for (CSVRecord rec: fr.getCSVParser(false)){
             if (rec.get(1).equals("M")) {
                rankboyz += 1;
             }else{
                 rankgalz += 1;
             }
             if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                 //return Integer.parseInt(rec.get(2));
                 if (rec.get(1).equals("M")) {
                     return rankboyz;                 
                 }else{
                     return rankgalz; 
                 }                 
            } //end for
            
        } //end method
        return -1;
     }    
    
     public int getTotalBirthsRankedHigher(int year, String name, String gender) {         
         int bbmain = 0;         
         int totbbs = 0;
         FileResource fr = new FileResource("babynames/by_year/yob"+year+".csv");
         for (CSVRecord rec: fr.getCSVParser(false)){             
             if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {  
                 bbmain = Integer.parseInt(rec.get(2));    
                 System.out.println("main births: "+bbmain);
              }
         }
         for (CSVRecord rec: fr.getCSVParser(false)){ 
             int bbs = Integer.parseInt(rec.get(2));              
             if (rec.get(1).equals(gender) && bbs > bbmain) {                 
                 System.out.print("adding : "+ bbs);
                 System.out.println("  from : "+ rec.get(0));
                 totbbs += bbs;
              }
         }         
         return totbbs;
     }    
     
    
     
     public String getName (int year, int rank, String gender) {
         int rankb = 0;
         int rankg = 0;
         FileResource fr = new FileResource("babynames/by_year/yob"+year+".csv");
          for (CSVRecord rec: fr.getCSVParser(false)){
              if (rec.get(1).equals("M")) {
                rankb += 1;
              }else{
                 rankg += 1;
              }
              if ((rankb == rank || rankg == rank) && rec.get(1).equals(gender)) {
                  return rec.get(0);                         
              }
            }     
         return "NO NAME";
     }
     
     public void whatIsNameInYear(String name, int year, int newyr, String gender) {
          int rnk = getRank(year, name, gender);
          String nem = getName(newyr, rnk, gender);
          System.out.println(name+" born in "+year+" would be "+nem+" if she was born in "+newyr);                   
     }
     
     
     public void yearOfHighestRank(String name, String gender) {
         int rankmin = 99999;
         File ffselect = null;
         int year = 0;
         DirectoryResource dr  = new DirectoryResource();         
         for (File ff: dr.selectedFiles()){
            FileResource fr = new FileResource(ff);            
             int rankboyz = 0;         
             int rankgalz = 0;
             for (CSVRecord rec: fr.getCSVParser(false)){
                 if (rec.get(1).equals("M")) {
                     rankboyz += 1;
                 }else{
                     rankgalz += 1;
                 }
                 if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {                 
                     if (rec.get(1).equals("M")) {                        
                         if (rankboyz < rankmin) {
                             rankmin = rankboyz;                        
                             ffselect = ff;
                             String fname = ffselect.getName();
                             year = Integer.parseInt(fname.substring(3,7));                             
                         }                         
                 }else if (rec.get(1).equals("F")) {            //end male if            
                     if (rec.get(1).equals("F")) {                         
                         if (rankgalz < rankmin) {
                             rankmin = rankgalz;                        
                             ffselect = ff;
                             String fname = ffselect.getName();
                             year = Integer.parseInt(fname.substring(3,7));                             
                         }                         
                     }                    
                 } //end female if
                } //end found if
            } // end record if
            } // end file direcotry if
           System.out.println("returning year: " +year);
        }       
        
     public double getAverageRank(String name, String gender) {
         int totranks = 0;
         double totfiles = 0.0;
         DirectoryResource dr  = new DirectoryResource();         
         for (File ff: dr.selectedFiles()){
             String fname = ff.getName();
             int yr = Integer.parseInt(fname.substring(3,7));             
             int rnk = getRank(yr, name, gender);             
             totranks += rnk;
             totfiles += 1;             
         }
         return totranks/totfiles;
     }
     
     //==============================================teting ========================     
     public void testgetTotalBirthsRankedHigher() {            
              int tot = getTotalBirthsRankedHigher(1990,"Drew","M");         
          System.out.println("higher births than emily: "+tot);
     }
     
     public void testtotalBirths() {      
         FileResource fr = new FileResource();
         totalBirths(fr);
     }
     
         
     public void testgetAverageRank() { 
         double aveg = getAverageRank("Robert", "M");
         System.out.println("avereage rank: "+ aveg);
     }
         
     public void testyearOfHighestRank() {  
         yearOfHighestRank("Mich", "M");
     }
     
     public void testwhatIsNameInYear() {  
          whatIsNameInYear("Owen", 1974, 2014, "M");
        }        
    
     public void testgetRank() {          
         int nn = getRank(1971,"Frank","M");
         System.out.println("rank for frnk 1971: "+ nn);
        }    
     
     public void testgetName() {          
         String neim = getName(1982, 450,"M");
         System.out.println("name for rank 450: "+ neim);
        }     
    
    
} //end class
