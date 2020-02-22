
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int mny = 0;        
        int idx = 0;
        int aa = stringa.length();
        while (true) {
            int fndidx = stringb.indexOf(stringa, idx);            
            if (fndidx != -1) {
                mny = mny+1;
                idx = fndidx+aa;
            }else{
                break;
            }
        } 
        return mny;
    }
    
    public void testHowMany(){
        String b =  "ATGTACACGTGAATGTAGATGACGATTTAACTAATCGATGGGGTGTATTACGATT";
        System.out.println(howMany("ACG", b));
    }
}
