package prog3;
import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

	public BruteCollinearPoints(Point[] points) {

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

		segments = new ArrayList<LineSegment>();

		findSegments(copy);
	}

	private java.util.ArrayList<LineSegment> segments;

	private void findSegments(Point[] pointsCopy) {
		for (int p = 0; p < pointsCopy.length - 3; p++) {
            for (int q = p + 1; q < pointsCopy.length - 2; q++) {
                for (int r = q + 1; r < pointsCopy.length - 1; r++) {
                    for (int s = r + 1; s < pointsCopy.length; s++) {
                        if (pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[r]) &&
                                pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[s])) {
                        	segments.add(new LineSegment(pointsCopy[p], pointsCopy[s]));
                        }
                    }
                }
            }
        }
	}

	private boolean containsDuplicates(Point[] points) {
		for (int i = 1; i < points.length; i++) {
			if (points[i].compareTo(points[i - 1]) == 0)
				return true;
		}
		return false;
	}

	public int numberOfSegments() {
		return segments.size();
	}

	public LineSegment[] segments() {
		return segments.toArray(new LineSegment[segments.size()]);
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
		BruteCollinearPoints collinear = new BruteCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
	}

}
