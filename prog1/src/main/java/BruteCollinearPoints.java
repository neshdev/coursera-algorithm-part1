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

		Arrays.sort(points);

		if (containsDuplicates(points))
			throw new IllegalArgumentException();

		segments = new ArrayList<LineSegment>();

		findSegments(points);
	}

	private java.util.ArrayList<LineSegment> segments;

	private void findSegments(Point[] points) {
		for (int i = 0; i < points.length; i++) {
			for (int j = 1; j < points.length; j++) {
				for (int k = 2; k < points.length; k++) {
					for (int l = 3; l < points.length; l++) {
						Point p = points[i];
						Point q = points[i];
						Point r = points[i];
						Point s = points[i];
						double pq = p.slopeTo(q);
						double pr = p.slopeTo(r);
						double ps = p.slopeTo(s);
						if (pq == pr || pq == ps || pr == ps) {
							Point a = min(p, q, r, s);
							Point b = max(p, q, r, s);
							LineSegment segment = new LineSegment(a, b);
							segments.add(segment);
						}
					}
				}
			}
		}
	}

	private Point min(Point a, Point b) {
		int results = a.compareTo(b);
		if (results == -1)
			return a;
		else
			return b;
	}

	private Point min(Point a, Point b, Point c) {
		Point x = min(a, b);
		return min(c, x);
	}

	private Point min(Point a, Point b, Point c, Point d) {
		Point x = min(a, b, c);
		return min(x, d);
	}

	private Point max(Point a, Point b) {
		int results = a.compareTo(b);
		if (results == -1)
			return b;
		else
			return a;
	}

	private Point max(Point a, Point b, Point c) {
		Point x = max(a, b);
		return max(c, x);
	}

	private Point max(Point a, Point b, Point c, Point d) {
		Point x = max(a, b, c);
		return max(x, d);
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
		return (LineSegment[]) segments.toArray();
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
