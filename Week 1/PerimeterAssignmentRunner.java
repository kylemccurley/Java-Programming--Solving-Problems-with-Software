import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public int getNumPoints (Shape s) {
        int numPoints = 0;
        for(Point p : s.getPoints()){
            numPoints += 1;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {

        double length = getPerimeter(s);
        double numSides = (double) getNumPoints(s);
        double avgLength = length / numSides;
        return avgLength;
    }

    public double getLargestSide(Shape s) {
        Point lastPoint = s.getLastPoint();
        double largestSide = 0.0;
        
        for(Point p : s.getPoints()){
            double dist = lastPoint.distance(p);
            if(dist > largestSide) {
                largestSide = dist;
            }
            lastPoint = p;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        Point lastPoint = s.getLastPoint();
        int lastPointX = lastPoint.getX();
        double largestX = (double) lastPointX;
        
        for(Point p : s.getPoints()){
            int newX = p.getX();
            if(newX > largestX) {
                largestX = (double) newX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0.0;
        FileResource largestFile = null;

        for(File f : dr.selectedFiles()){
            FileResource file = new FileResource(f);
            Shape shape = new Shape(file);
            double perim = getPerimeter(shape);
            if(perim > largestPerim) {
                largestPerim = perim;
            }
        }
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0.0;
        File largestFile = null;

        for(File f : dr.selectedFiles()){
            FileResource file = new FileResource(f);
            Shape shape = new Shape(file);
            double perim = getPerimeter(shape);
            if(perim > largestPerim) {
                largestPerim = perim;
                largestFile = f;
            }
        }

        return largestFile.getName();
    }

    public void testPerimeterMultipleFiles() {
        
        double largest = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter is: " + largest);
    }

    public void testFileWithLargestPerimeter() {
        String file = getFileWithLargestPerimeter();
        System.out.println("Largest perimeter file is: " + file);
    }
    
    public double getPerimeter (Shape s) {
        double totalPerim = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
            prevPt = currPt;
        }
        return totalPerim;
    }
    
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numPoints = getNumPoints(s);
        double averageLength = getAverageLength(s);
        double largestSide = getLargestSide(s);
        double largestX = getLargestX(s);
        
        System.out.println("perimeter = " + length);
        System.out.println("Number of Points: " + numPoints);
        System.out.println("Average Length: " + averageLength);
        System.out.println("Largest Side: " + largestSide);
        System.out.println("Largest X is: " + largestX );
        testPerimeterMultipleFiles();
        testFileWithLargestPerimeter();
    }

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
