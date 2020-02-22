import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }
      
    public int getNumPoints (Shape s) {
        int nm = 0;        
        for (Point p : s.getPoints()){
            nm = nm+1;            
        }        
        return nm;
    }

    public double getAverageLength(Shape s) {
        double len = getPerimeter(s);
        int nmb = getNumPoints(s); 
        double avg = len/nmb;        
        return avg;
    }    

    public double getLargestSide(Shape s) {    
        double max = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (currDist > max) {
                max = currDist;
            }            
            prevPt = currPt;
        }
        return max;
    }
    
    public double getLargestX(Shape s) {    
        double maxx = 0.0;        
        for (Point pt : s.getPoints()) {
            if (pt.getX() > maxx) {
                maxx =  pt.getX();
            }                        
        }
        return maxx;
    }    

    public double getLargestPerimeterMultipleFiles() {
        double lgperim = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {            
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perim = getPerimeter(s);
            if (perim > lgperim) {
                lgperim = perim;
            } 
        }
        return lgperim;
    }

    public String getFileWithLargestPerimeter() {
        double lgperim = 0.0;
        File fff = null;
        String bigfile = "";
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {            
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perim = getPerimeter(s);
            if (perim > lgperim) {                
                lgperim = perim;   
                bigfile = f.getName();
            } 
        } 
        return bigfile;
    }
    
//    fff = f

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        int nmb = getNumPoints(s);        
        System.out.println("number of points = " + nmb);
        double length = getPerimeter(s);        
        System.out.println("perimeter = " + length);
        double avg = getAverageLength(s);        
        System.out.println("average length: " + avg);
        double large = getLargestSide(s);
        System.out.println("longest side:  " + avg);
        double largX = getLargestX(s);
        System.out.println("largest X point value:  " + largX);
    }
    
    public void testPerimeterMultipleFiles() {
        double largest = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter multiple files:  "+largest);
    }

    public void testFileWithLargestPerimeter() {
        String large_one = getFileWithLargestPerimeter();
        System.out.println("file with largest perimeter:  "+large_one);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
