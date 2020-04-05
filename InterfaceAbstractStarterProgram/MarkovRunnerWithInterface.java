
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {        
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 1; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov(int sd) {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;        
        
        MarkovZero mz = new MarkovZero();
        //mz.setRandom(1024);
        runModel(mz, st, size, sd);
    
        // MarkovOne mOne = new MarkovOne();
        // runModel(mOne, st, size, sd);
        
        // MarkovModel mThree = new MarkovModel(3);
        // runModel(mThree, st, size, sd);
        
        // MarkovFour mFour = new MarkovFour();
        // runModel(mFour, st, size, sd);

    }
    public void compareMethods(){
        FileResource fr = new FileResource(); //hawthorne.txt
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int sd = 42;        
        
        MarkovModel m2 = new MarkovModel(2);
        long mod1 = System.nanoTime();
        runModel(m2, st, size, sd);
        long mod2 = System.nanoTime();
        long dmod = mod2-mod1;        
        
        EfficientMarkovModel em2 = new EfficientMarkovModel(2);
        long emod1 = System.nanoTime();        
        runModel(em2, st, size, sd);           
        long emod2 = System.nanoTime(); 
        long dmode = emod2-emod1;
        System.out.println("time for markovmodel: "+(dmod));
        System.out.println("time for effieicent markovmodel: "+(dmode)); 
        System.out.println("modelmarkov less efficient: "+(dmod>dmode)); 
        System.out.println("by milli seconds: "+(dmod-dmode)/1000000);         
    }
    
 public void testHashMap(){
     //String st = "yes-this-is-a-thin-pretty-pink-thistle";
     FileResource fr = new FileResource();
     String st = fr.asString();
     st = st.replace('\n', ' ');
     int size = 500;             
     int sd = 531;             
     
     EfficientMarkovModel em5 = new EfficientMarkovModel(5);
     runModel(em5, st, size, sd);     
     //MarkovModel m2 = new MarkovModel(2);
     //runModel(m2, st, size, sd);     
    }

private void printOut(String s){
    String[] words = s.split("\\s+");
    int psize = 0;
    System.out.println("----------------------------------");
    for(int k=0; k < words.length; k++){
        System.out.print(words[k]+ " ");
        psize += words[k].length() + 1;
        if (psize > 60) {
            System.out.println();
            psize = 0;
        }
    }
    System.out.println("\n----------------------------------");
}
    
}
