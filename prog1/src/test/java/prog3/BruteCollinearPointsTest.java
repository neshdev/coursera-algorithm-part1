package prog3;
import static org.junit.Assert.*;

import org.junit.Test;

import prog3.BruteCollinearPoints;
import prog3.Point;


public class BruteCollinearPointsTest {

	@Test
	public void ConstructorNullArgumentThrowsException() {
		try{
			BruteCollinearPoints bcp = new BruteCollinearPoints(null);
			fail("should not reach here");
		} catch(NullPointerException ex){
			
		}
	}
	
	@Test
	public void ConstructorCollectionContainsNullElementsThrowsException() {
		try{
			Point[] points = new Point[1];
			points[0] = null;
			BruteCollinearPoints bcp = new BruteCollinearPoints(points);
			fail("should not reach here");
		} catch(NullPointerException ex){
			
		}
	}
	
	@Test
	public void ConstructorCollectionContainsRepeatingElementThrowsException() {
		try{
			Point[] points = new Point[2];
			points[0] = new Point(0, 0);
			points[1] = new Point(0, 0);
			BruteCollinearPoints bcp = new BruteCollinearPoints(points);
			fail("should not reach here");
		} catch(IllegalArgumentException ex){
			
		}
	}

}
