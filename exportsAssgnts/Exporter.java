
/**
 * Write a description of Exporter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class Exporter {
    
    public void tester(){
        FileResource fr = new FileResource();
        // CSVParser parser = fr.getCSVParser();
        // countryInfo(parser, "Nauru");
        // ======================================
        // CSVParser parse2 = fr.getCSVParser();
        // listExportersTwoProducts(parse2, "cotton", "flowers");
        
        //======================================
        // CSVParser parse3 = fr.getCSVParser();
        // int nn = numberOfExporters(parse3, "cocoa");
        // System.out.println(nn +" countrys export cocoa");    
        
        // // ======================================        
        CSVParser parse4 = fr.getCSVParser();
        bigExporters(parse4, "$999,999,999,999");
    }
    
    public void countryInfo(CSVParser parser, String country){
        for (CSVRecord record: parser){
            String cntry = record.get("Country");
            if (cntry.contains(country)){
                String exports = record.get("Exports");  
                if (exports.isEmpty()){
                    System.out.println("NOT FOUND");                                               
                }else{
                    String val = record.get("Value (dollars)");                                
                    System.out.print(cntry);
                    System.out.print(": "); 
                    System.out.println(exports);                 
                    System.out.print(": ");
                    System.out.println(val);                                                               
                }           
            }
        } //for loop
    } //country info method
    
    public void listExportersTwoProducts(CSVParser parser, String ExportItem1, String ExportItem2){
         for (CSVRecord record: parser){
             String exports = record.get("Exports");  
             if (exports.contains(ExportItem1) && exports.contains(ExportItem2)) {
                 String cntry = record.get("Country");
                 System.out.println(cntry);                
             }            
         }
    
    }
    
   public int numberOfExporters(CSVParser parser, String exportItem){
       int nbr = 0;
       for (CSVRecord record: parser){
           String exports = record.get("Exports");  
           if (exports.contains(exportItem)) {
               nbr += 1;                 
           }            
       }
       return nbr;    
   }
   
   
    public void bigExporters(CSVParser parser, String amount){
       int amtlen = amount.length();
       for (CSVRecord record: parser){
           String cntry = record.get("Country");
           String val = record.get("Value (dollars)");
           int valen = val.length();
           if (valen > amtlen) {               
                System.out.print(cntry +(" ")); 
                System.out.println(val);                 
           }
       }       
   }
    
    

} //exporter mother class

