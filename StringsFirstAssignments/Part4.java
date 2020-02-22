import edu.duke.*;
//import java.io.File;

/**
 * using url resource to get youtube links
 *  * 
 * @author Henry M.Kirya
 * @version 2.00
 */
public class Part4 {
    public void readUrl() {
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String s : ur.lines()) {
            String sss = sorting(s);
            if (sss != "null") {
                System.out.println(sss);              
            }
        }
    } //readurl   
    
    public String sorting(String lnupper) {
        String ln = lnupper.toLowerCase();
        int you = ln.indexOf("ube.");
        if (you  == -1) {
             return "null";
        }
        String aa = ln.substring(0,you);
        int st1 = aa.lastIndexOf("\"http");
        int st2 = ln.indexOf(">",st1);
        String res = lnupper.substring(st1+1,st2-1);            
        return res;
    }
    

} //class

