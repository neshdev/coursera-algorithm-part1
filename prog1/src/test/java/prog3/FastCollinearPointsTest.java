package prog3;
import static org.junit.Assert.*;

import org.junit.Test;

import prog3.FastCollinearPoints;
import prog3.Point;


public class FastCollinearPointsTest {

	@Test
	public void Four_Collinear_Point_Test() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(1, 1);
		Point p3 = new Point(2, 2);
		Point p4 = new Point(3, 3);

		Point[] points = new Point[] { p1, p2, p3, p4 };
		FastCollinearPoints fcp = new FastCollinearPoints(points);
		assertEquals(1, fcp.numberOfSegments());
		assertEquals(p1.toString() + " -> " + p4.toString(),
				fcp.segments()[0].toString());
	}

	@Test
	public void Five_Collinear_Point_Test() {
		Point p0 = new Point(-1, -1);
		Point p1 = new Point(0, 0);
		Point p2 = new Point(1, 1);
		Point p3 = new Point(2, 2);
		Point p4 = new Point(3, 3);

		Point[] points = new Point[] { p0, p1, p2, p3, p4 };
		FastCollinearPoints fcp = new FastCollinearPoints(points);
		assertEquals(1, fcp.numberOfSegments());
		assertEquals(p0.toString() + " -> " + p4.toString(),
				fcp.segments()[0].toString());
	}

	@Test
	public void Five_Collinear_Point_With_1_OutLier_Test() {
		Point p0 = new Point(-1, -2);
		Point p1 = new Point(0, 0);
		Point p2 = new Point(1, 1);
		Point p3 = new Point(2, 2);
		Point p4 = new Point(3, 3);

		Point[] points = new Point[] { p0, p1, p2, p3, p4 };
		FastCollinearPoints fcp = new FastCollinearPoints(points);
		assertEquals(1, fcp.numberOfSegments());
		assertEquals(p1.toString() + " -> " + p4.toString(),
				fcp.segments()[0].toString());
	}

	@Test
	public void Five_Collinear_Point_With_2_OutLier_Test() {
		Point p0 = new Point(-1, -2);
		Point p1 = new Point(0, 0);
		Point p2 = new Point(1, 1);
		Point p3 = new Point(2, 2);
		Point p4 = new Point(3, 3);
		Point p5 = new Point(6, 5);

		Point[] points = new Point[] { p0, p1, p2, p3, p4, p5 };
		FastCollinearPoints fcp = new FastCollinearPoints(points);
		assertEquals(1, fcp.numberOfSegments());
		assertEquals(p1.toString() + " -> " + p4.toString(),
				fcp.segments()[0].toString());
	}

	@Test
	public void Two_Collinear_LineSegmentsTest() {

		Point p1 = new Point(0, 0);
		Point p2 = new Point(1, 2);
		Point p3 = new Point(2, 4);
		Point p4 = new Point(3, 6);

		Point p5 = new Point(2, 1);
		Point p6 = new Point(4, 2);
		Point p7 = new Point(6, 3);

		Point[] points = new Point[] { p1, p2, p3, p4, p5, p6, p7 };
		FastCollinearPoints fcp = new FastCollinearPoints(points);
		assertEquals(2, fcp.numberOfSegments());
		assertEquals(p1.toString() + " -> " + p7.toString(),
				fcp.segments()[0].toString());
		assertEquals(p1.toString() + " -> " + p4.toString(),
				fcp.segments()[1].toString());
	}

	@Test
	public void Two_Collinear_LineSegmentsWith_1_Outlier_Test() {

		Point p1 = new Point(0, 0);
		Point p2 = new Point(1, 2);
		Point p3 = new Point(2, 4);
		Point p4 = new Point(3, 6);

		Point p5 = new Point(2, 1);
		Point p6 = new Point(4, 2);
		Point p7 = new Point(6, 3);

		Point p8 = new Point(20, 20);

		Point[] points = new Point[] { p1, p2, p3, p4, p5, p6, p7, p8 };
		FastCollinearPoints fcp = new FastCollinearPoints(points);
		assertEquals(2, fcp.numberOfSegments());
		assertEquals(p1.toString() + " -> " + p7.toString(),
				fcp.segments()[0].toString());
		assertEquals(p1.toString() + " -> " + p4.toString(),
				fcp.segments()[1].toString());
	}
	
	@Test
	public void test() {
		
		Point p1 = new Point(19000,10000);
		Point p2 = new Point(18000,10000);
		Point p3 = new Point(32000,10000);
		Point p4 = new Point(21000,10000);
		Point p5 = new Point(1234,5678);
		Point p6 = new Point(14000,10000);
		
		Point[] points = new Point[] { p1, p2, p3, p4, p5, p6};
		FastCollinearPoints fcp = new FastCollinearPoints(points);
		assertEquals(1, fcp.numberOfSegments());
		
		assertEquals(p6.toString() + " -> " + p3.toString(), fcp.segments()[0].toString());
	}

}
