import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;


public class PointTest {

	@Test
	public void Point1_X1_GreaterThan_Point0_X1() {
		int x0 = 0;
		int y0 = 0;
		
		int x1 = 1;
		int y1 = 0;
		
		Point p0 = new Point(x0, y0);
		Point p1 = new Point(x1, y1);
		int actual = p0.compareTo(p1);
		
		assertEquals(-1, actual);
	}
	
	@Test
	public void Point1_X1_LessThan_Point0_X1() {
		int x0 = 1;
		int y0 = 0;
		
		int x1 = 0;
		int y1 = 0;
		
		Point p0 = new Point(x0, y0);
		Point p1 = new Point(x1, y1);
		int actual = p0.compareTo(p1);
		
		assertEquals(1, actual);
	}
	
	@Test
	public void Point1_Equals_Point0() {
		int x0 = 0;
		int y0 = 0;
		
		int x1 = 0;
		int y1 = 0;
		
		Point p0 = new Point(x0, y0);
		Point p1 = new Point(x1, y1);
		int actual = p0.compareTo(p1);
		
		assertEquals(0, actual);
	}
	
	@Test
	public void Point1_Y1_GreaterThan_Point2_Y0() {
		int x0 = 0;
		int y0 = 0;
		
		int x1 = 0;
		int y1 = 1;
		
		Point p0 = new Point(x0, y0);
		Point p1 = new Point(x1, y1);
		int actual = p0.compareTo(p1);
		
		assertEquals(-1, actual);
	}
	
	@Test
	public void Point1_Y1_LessThan_Point2_Y0() {
		int x0 = 0;
		int y0 = 1;
		
		int x1 = 0;
		int y1 = 0;
		
		Point p0 = new Point(x0, y0);
		Point p1 = new Point(x1, y1);
		int actual = p0.compareTo(p1);
		
		assertEquals(1, actual);
	}
	
	@Test
	public void ValidateSlopeBetweenTwoDistinctPoints(){
		int x0 = 0;
		int y0 = 0;
		
		int x1 = 1;
		int y1 = 1;
		
		Point p0 = new Point(x0, y0);
		Point p1 = new Point(x1, y1);
		double actual = p0.slopeTo(p1);
		double expectedSlope = (y1 - y0) / (x1 - x0);
		
		assertEquals(expectedSlope, actual, .001);
	}
	
	@Test
	public void SlopeBetweenHorizontalPointsReturnsZero(){
		int x0 = 1;
		int y0 = 1;
		
		int x1 = 2;
		int y1 = 1;
		
		Point p0 = new Point(x0, y0);
		Point p1 = new Point(x1, y1);
		double actual = p0.slopeTo(p1);
		double expectedSlope = 0;
		
		assertEquals(expectedSlope, actual, .001);
	}
	
	@Test
	public void SlopeBetweenVerticalPointsReturnsPositiveInfinity(){
		int x0 = 1;
		int y0 = 0;
		
		int x1 = 1;
		int y1 = 1;
		
		Point p0 = new Point(x0, y0);
		Point p1 = new Point(x1, y1);
		double actual = p0.slopeTo(p1);
		double expectedSlope = Double.POSITIVE_INFINITY;
		
		assertEquals(expectedSlope, actual, .001);
	}
	
	@Test
	public void SlopeBetweenDegeneratePoints_Return_Infinity(){
		int x0 = 1;
		int y0 = 1;
		
		int x1 = 1;
		int y1 = 1;
		
		Point p0 = new Point(x0, y0);
		Point p1 = new Point(x1, y1);
		double actual = p0.slopeTo(p1);
		double expectedSlope = Double.NEGATIVE_INFINITY;
		
		assertEquals(expectedSlope, actual, .001);
	}
	
	@Test
	public void SlopeBetweenTwoPointsHasZeroDen_Return_Infinity(){
		int x0 = 0;
		int y0 = 1;
		
		int x1 = 0;
		int y1 = 2;
		
		Point p0 = new Point(x0, y0);
		Point p1 = new Point(x1, y1);
		double actual = p0.slopeTo(p1);
		double expectedSlope = Double.POSITIVE_INFINITY;
		
		assertEquals(expectedSlope, actual, .001);
	}
	
	@Test
	public void test1(){
		int x0 = 0;
		int y0 = 0;
		
		int x1 = 2;
		int y1 = 2;
		
		int x2 = 2;
		int y2 = 1;
		
		Point p0 = new Point(x0, y0);
		Point p1 = new Point(x1, y1);
		Point p2 = new Point(x2, y2);
		
		Comparator<Point> comp = p0.slopeOrder();
		int actual = comp.compare(p1, p2);
		int expected = 1;
		assertEquals(expected, actual);
		
	}

}
