
import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     private  LogEntry le;
     
     public LogAnalyzer() {        
          records = new ArrayList<LogEntry>();          
     }
        
     public void readFile(String filename) {         
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()) {
             LogEntry ls = WebLogParser.parseEntry(line);
             records.add(ls);             
             //records.add(WebLogParser.parseEntry(line));
         }         
     }
     
     public int countUniqs() { 
          ArrayList<String> uniqips = new ArrayList<String>();
          for (LogEntry le : records){
              String addrss = le.getIpAddress();
              //System.out.println("printing address:  "+addrss);
              if (!uniqips.contains(addrss)){
                  uniqips.add(addrss);
                  System.out.println("adding uniques address:  "+addrss);
              }
            }
            return uniqips.size();
        }
        
    public void printAllHigherThanNum(int num) { 
          for (LogEntry le : records){
               int stat  = le.getStatusCode();
               if (stat > num){
                   System.out.println("status code: "+stat);
                }
          }
    }
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> visitors = new ArrayList<String>();
          for (LogEntry le : records){
              String datestr = le.getAccessTime().toString();
              datestr = datestr.substring(4,10);              
              if (datestr.equals(someday)){                  
                  String addrss = le.getIpAddress();                  
                  if (!visitors.contains(addrss)){
                      visitors.add(addrss);
                      System.out.println("visitor address added "+addrss); 
                    } 
              }            
          }   
          return visitors;
     }
     
    public int countUniqueIPsInRange(int low, int high) { 
        ArrayList<String> codrange = new ArrayList<String>();
        for (LogEntry le : records){
               int stat  = le.getStatusCode();
               if (stat>=low &&  stat<=high){                   
                   String addrss = le.getIpAddress();                    
                   if (!codrange.contains(addrss)){
                      codrange.add(addrss);
                      //System.out.println("codrange uniq address added "+addrss); 
                    }                    
                }
          }
          return codrange.size();
    }
    
    public HashMap<String, Integer> countVisitsPerIP() { 
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry le: records){
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip)){
                counts.put(ip,1);
            }else{
                counts.put(ip,counts.get(ip)+1);            
            }           
        }
         return counts;            
    }
    
    public int mostNumberVisitsByIP(HashMap<String,Integer> cnts) { 
        int mx = 0;
        for (String iprec : cnts.keySet()) {
            int tt = cnts.get(iprec);
            if (tt > mx){
                mx = tt;            
            }
        }
        return mx;
    }
    
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> cnts) { 
          ArrayList<String> mxddresses = new ArrayList<String>();
         int mxvisits = mostNumberVisitsByIP(cnts);
          for (String iprec : cnts.keySet()) {
              int tt = cnts.get(iprec);
              if (tt == mxvisits){
                  mxddresses.add(iprec);                  
              }
          }
          return mxddresses;
     }
     
     
    
     public HashMap<String,ArrayList<String>> iPsForDays() { 
          HashMap<String,ArrayList<String>> daymap = new HashMap<String,ArrayList<String>>();          
          for (LogEntry le : records){
              String datestr = le.getAccessTime().toString().substring(4,10);
              String addrss = le.getIpAddress();              
              if (!daymap.containsKey(datestr)){ 
                  ArrayList<String> newray = new ArrayList<String>(); //create new arraylist    
                  newray.add(addrss); //add addrss to the arraylist                  
                  daymap.put(datestr,newray); //put (datestr,newray) to daymap                  
              }else{
                  ArrayList<String> newray = daymap.get(datestr); //get arraylist corresponding to datestr
                  newray.add(addrss); //add addrss to the arraylist   
                  daymap.put(datestr,newray); //put (datestr,newray) to daymap                  
              }           
        }
        return daymap;               
    }
    
    public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> dmap) { 
        int ssmax = 0;
        String kky;
        String topday = "";
        for (String ky : dmap.keySet()) {        
            int ss = dmap.get(ky).size();
            kky = ky;
            if(ss>ssmax){
              ssmax = ss;
              topday = kky;        
            }
        }
        return topday;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> dmap, String dey) {         
         HashMap<String,Integer> counts = new HashMap<String, Integer>();         
         for (LogEntry le: records){
              String datestr = le.getAccessTime().toString().substring(4,10);              
              if (datestr.equals(dey)){  
                  String ip = le.getIpAddress();
                  if (!counts.containsKey(ip)){
                      counts.put(ip,1);
                  }else{
                      counts.put(ip,counts.get(ip)+1);            
                  }           
              }         
         }
      return iPsMostVisits(counts);  
    }


    public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }   
     
}




  