//henry kirya

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord m = new MarkovWord(5);
        runModel(m, st, 150, 844);  
        
    } 
    
    public void testHashMap() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        //String st = "this is a test yes this is really a test";
        //String st = "this is a test yes this is really a test yes a test this is wow";
        EfficientMarkovWord mm = new EfficientMarkovWord(2);       
        runModel(mm, st, 50, 65);        
    } 
     public void compareMethods(){
        FileResource fr = new FileResource(); //hawthorne.txt
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 100;
        int sd = 42;        
        
        MarkovWord  m2 = new MarkovWord(2);
        long mod1 = System.nanoTime();
        runModel(m2, st, size, sd);
        long mod2 = System.nanoTime();
        long dmod = mod2-mod1;        
        
        EfficientMarkovWord em2 = new EfficientMarkovWord(2);
        long emod1 = System.nanoTime();        
        runModel(em2, st, size, sd);           
        long emod2 = System.nanoTime(); 
        long dmode = emod2-emod1;
        System.out.println("time for markovmodel: "+(dmod));
        System.out.println("time for effieicent markovmodel: "+(dmode)); 
        System.out.println("modelmarkov less efficient: "+(dmod>dmode)); 
        System.out.println("by milli seconds: "+(dmod-dmode)/1000000);         
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
