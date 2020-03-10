
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer obj = new LogAnalyzer();
        obj.readFile("short-test_log");
        obj.printAll();
    }
    public void testuniqip() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int uniqips = la.countUniqs();
        System.out.println("uniq ips: "+uniqips);      
    }
     public void testacode() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        System.out.println("status code higher than: 10");      
        la.printAllHigherThanNum(10);
    }
    public void testuniqvisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int un = la.uniqueIPVisitsOnDay("Mar 17").size();
        System.out.println("uniq visits:  "+un);
    }
    public void testcountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        //int res = la.countUniqueIPsInRange(200,299); //return 4
        //System.out.println("uniq ips in range 200,299: "+res);      
        int res2 = la.countUniqueIPsInRange(300,399); //return 2
        System.out.println("uniq ips in range 300,399: "+res2);      
    }
    
    //printAllHigherThanNum(int num)
    public void testprintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);        
    }
    
}


//LogAnalyze obj = new LogAnalyze();