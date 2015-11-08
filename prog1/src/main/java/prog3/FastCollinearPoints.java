package prog3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

	private HashMap<Double, LineSegment> segments;
	
	private void tryAddSegment(double slope, LineSegment segment){
		LineSegment s = segments.get(slope);
		if ( s == null){
			segments.put(slope, segment);
		}
	}

	public FastCollinearPoints(Point[] points) {

		if (points == null)
			throw new NullPointerException();

		for (int i = 0; i < points.length; i++) {
			if (points[i] == null)
				throw new NullPointerException();
		}

		Point[] copy = Arrays.copyOf(points, points.length);
		
		Arrays.sort(copy);

		if (containsDuplicates(copy))
			throw new IllegalArgumentException();

		segments = new HashMap<Double, LineSegment>();
		
		findLineSegments(copy);

	}

	private boolean containsDuplicates(Point[] points) {
		for (int i = 1; i < points.length; i++) {
			if (points[i].compareTo(points[i - 1]) == 0)
				return true;
		}
		return false;
	}

	private void findLineSegments(Point[] a) {

		Point[] copy = Arrays.copyOf(a, a.length);

		for (int i = 0; i < a.length; i++) {

			Point p = a[i];
			Arrays.sort(copy, p.slopeOrder());
			ArrayList<Point> collinearPoints = new ArrayList<Point>();

			Double prevSlope = null;

			for (int j = 0; j < copy.length; j++) {

				Point current = copy[j];
				if (p.compareTo(current) == 0) {
					continue;
				}
					

				Double currentSlope = p.slopeTo(current);
				if (currentSlope.equals(prevSlope)) {
					collinearPoints.add(current);
				} else {

					if (collinearPoints.size() >= 3) {
						collinearPoints.add(p);
						Point[] colPointArray = collinearPoints
								.toArray(new Point[collinearPoints.size()]);
						Arrays.sort(colPointArray);
						Point min = colPointArray[0];
						Point max = colPointArray[colPointArray.length - 1];
						LineSegment s = new LineSegment(min, max);
						tryAddSegment(prevSlope, s);
					}
					collinearPoints.clear();
					prevSlope = currentSlope;
					collinearPoints.add(current);
				}
			}
			
			if (collinearPoints.size() >= 3) {
				collinearPoints.add(p);
				Point[] colPointArray = collinearPoints
						.toArray(new Point[collinearPoints.size()]);
				Arrays.sort(colPointArray);
				Point min = colPointArray[0];
				Point max = colPointArray[colPointArray.length - 1];
				LineSegment s = new LineSegment(min, max);
				tryAddSegment(prevSlope, s);
			}
			
		}
	}

	public int numberOfSegments() {
		return segments.size();
	}

	public LineSegment[] segments() {
		LineSegment[] arr = new LineSegment[segments.size()];
		int i = 0;
		for (LineSegment lineSegment : segments.values()) {
			arr[i++] = lineSegment;
		}
		return arr;
	}
	
	public static void main(String[] args) {

	    // read the N points from a file
	    In in = new In(args[0]);
	    int N = in.readInt();
	    Point[] points = new Point[N];
	    for (int i = 0; i < N; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.show(0);
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	}
}
