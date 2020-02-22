
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class CSVTemps {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord smallest = null;
        for (CSVRecord current : parser){
            if (smallest == null) {
                smallest = current;            
            }else{
                double currentemp = Double.parseDouble(current.get("TemperatureF"));
                double smallestemp = Double.parseDouble(smallest.get("TemperatureF"));
                if ((currentemp < smallestemp) && (currentemp > -9999)) {
                    smallest = current;
                }                
            }
        }  
        return smallest;
    } //method coldest
    
    public double averageTemperatureInFile(CSVParser parser){
        double tot =  0.0;
        int cnter = 0;
        for (CSVRecord current : parser){
            double currentemp = Double.parseDouble(current.get("TemperatureF"));
            tot += currentemp;
            cnter += 1;            
            }
        return tot/cnter;   
        //return avg; 
        }  
        
     public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        double avg  = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("average temperature in file "+ avg);
     }
    
     public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double tot =  0.0;
        int cnter = 0;
        for (CSVRecord current : parser){
            double currentemp = Double.parseDouble(current.get("TemperatureF"));
            int currenthmd = Integer.parseInt(current.get("Humidity"));
            if (currenthmd >= value){
                tot += currentemp;
                cnter += 1;                        
            }            
        }
        return tot/cnter;           
      }  
      
       public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        double avg2  = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        System.out.println("average temperature in file "+ avg2);
     }            
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord smallest = null;
        for (CSVRecord current : parser){
            if (smallest == null) {
                smallest = current;            
            }else{               
                //int currenthmd = Integer.parseInt(current.get("Humidity"));  
                int currenthmd = 9999;
                try{
                    currenthmd = Integer.parseInt(current.get("Humidity"));
                }catch (NumberFormatException ex) {
                        //handle exception here
                        //pass;
                }
                int smallesthmd = Integer.parseInt(smallest.get("Humidity"));
                if (currenthmd < smallesthmd){
                    smallest = current;
                }                
            }
        }  
        return smallest;
    } //method coldest
    
    public void testlowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVRecord lowest = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("lowest humidity "+ lowest.get("Humidity")+ " at "+ lowest.get("DateUTC"));
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coolest temperature was "+ smallest.get("TemperatureF"));
        //+ ", happened at: "+ smallest.get("TimeEST"));
    }
    
    public File fileWithColdestTemperature(){
        CSVRecord smallest = null;
        File smallfile = null;
        DirectoryResource dr  = new DirectoryResource();
        for (File ff: dr.selectedFiles()){
            FileResource fr = new FileResource(ff);
            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            if (smallest == null) {
               smallest = current;
               smallfile = ff;
            }else{
                double currentemp = Double.parseDouble(current.get("TemperatureF"));
                double smallestemp = Double.parseDouble(smallest.get("TemperatureF"));
                if (currentemp < smallestemp){
                    smallest = current;
                    smallfile = ff;
                }                
            }            
        }
        //return smallest;   
        return smallfile;
    } // end method
    
     public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord smallest = null;
        File smallfile = null;
        DirectoryResource dr  = new DirectoryResource();
        for (File ff: dr.selectedFiles()){
            FileResource fr = new FileResource(ff);
            CSVRecord current = lowestHumidityInFile(fr.getCSVParser());
            if (smallest == null) {
               smallest = current;
               //smallfile = ff;
            }else{
                int currenthmd = Integer.parseInt(current.get("Humidity"));
                int smallesthmd = Integer.parseInt(smallest.get("Humidity"));
                if (currenthmd < smallesthmd){
                    smallest = current;
                    //smallfile = ff;                    
                }               
            }            
        }
        return smallest;   
        //return smallfile;
    } // end method
    
    
    public void testLowestHumidityInManyFiles() {                 
        CSVRecord smallest = lowestHumidityInManyFiles();      
        System.out.println("Lowest Humidity was "+ smallest.get("Humidity") +" at"+smallest.get("DateUTC")); 
    }
     
    public void testFileWithColdestTemperature(){        
        //CSVRecord smallestinmany = fileWithColdestTemperature(); 
        File smallfile = fileWithColdestTemperature(); 
        FileResource fr = new FileResource(smallfile);
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());        
        System.out.println("coldest day was in file "+ smallfile);        
        System.out.println("coolest temperature on that day was "+ smallest.get("TemperatureF"));                
        System.out.println("All the temps on that coldest day were: ");
        for (CSVRecord curr : fr.getCSVParser()){
            System.out.print(curr.get("DateUTC") +" "); 
            System.out.print(curr.get("TimeEST") +" ");
            System.out.println(curr.get("TemperatureF") +" ");
        }
    }
    
      
     

}// class
