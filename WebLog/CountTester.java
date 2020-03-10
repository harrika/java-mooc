import java.util.*;
import edu.duke.*;

public class CountTester {
    
    public void testCounts(){
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("short-test_log");
    HashMap<String,Integer> cnts = la.countVisitsPerIP();
    System.out.println(cnts);    
    }   
        
    public void testuniqueIPVisitsOnDay(){
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("Weblog2_log");
    ArrayList<String> unqday = la.uniqueIPVisitsOnDay("Sep 24");
    int ss = unqday.size();
    System.out.println("uniq visits on day  "+ss);    
    }   
    
    public void testcountUniqueIPsInRange(){
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("Weblog2_log");    
    int ips = la.countUniqueIPsInRange(400,499);
    System.out.println("ips in range: "+ips);    
    }   
    
    public void testCountUniqips(){
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("Weblog2_log");
    int unqs = la.countUniqs();
    System.out.println("unique ips: "+unqs);    
    }   
    
    public void testmaxVisits(){
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("weblog3-short_log");
    HashMap<String,Integer> cnts = la.countVisitsPerIP();
    int cc = la.mostNumberVisitsByIP(cnts);
    System.out.println("maximum no visitor: "+cc);    
    }
    
    public void testiPsForDays(){
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("weblog3-short_log");    
    HashMap<String,ArrayList<String>> maps = la.iPsForDays();    
    System.out.println(maps);    
    }
    
    public void testdayWithMostIPVisits(){
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("weblog2_log"); 
    HashMap<String,ArrayList<String>> dmap = la.iPsForDays(); 
    String topdawg = la.dayWithMostIPVisits(dmap);    
    System.out.println(topdawg);    
    }
    
    public void testiPsWithMostVisitsOnDay(){
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("weblog2_log"); 
    HashMap<String,ArrayList<String>> dmap = la.iPsForDays(); 
    ArrayList<String>  res = la.iPsWithMostVisitsOnDay(dmap, "Sep 29");    
    System.out.println(res);    
    }
    
    public void testiPsMostVisits(){
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("weblog2_log");
    HashMap<String,Integer> cnts = la.countVisitsPerIP();
    ArrayList<String> hh = la.iPsMostVisits(cnts);
    //System.out.println("Expected 2 IP addresses, 61.15.121.171 and 84.133.195.161.");    
    System.out.println(hh);    
    }
    
    public void testmostNumberVisitsByIP(){
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("weblog2_log");
    HashMap<String,Integer> cnts = la.countVisitsPerIP();
    int res = la.mostNumberVisitsByIP(cnts);
    System.out.println(res);    
    }

}
