import java.util.ArrayList;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class PointSET {

	private SET<Point2D> points;
	
	private void NotNullValidation(Object obj){
		if ( obj == null) throw new NullPointerException("obj cannot be null");
	}
	
	public PointSET() // construct an empty set of points
	{
		points = new SET<Point2D>();
	}

	// is the set empty?
	public boolean isEmpty() {
		return points.isEmpty();
	}

	// number of points in the set
	public int size() {
		return points.size();
	}

	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		NotNullValidation(p);
		points.add(p);
	}

	// does the set contain point p?
	public boolean contains(Point2D p) {
		NotNullValidation(p);
		return points.contains(p);
	}

	// draw all points to standard draw
	public void draw() {
		for (Point2D p : points) {
			p.draw();
		}
	}

	// all points that are inside the rectangle
	public Iterable<Point2D> range(RectHV rect) {
		NotNullValidation(rect);
		
		ArrayList<Point2D> pointsInRect = new ArrayList<Point2D>();
		
		for (Point2D p : points) {
			if (rect.contains(p)){
				pointsInRect.add(p);
			}
		}
		
		return pointsInRect;
	}

	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		NotNullValidation(p);
		
		if (this.isEmpty())
			return null;
		
		double prevMinDistance = Double.MAX_VALUE;
		Point2D champ = null;
		for (Point2D otherPoint : points) {
			 double distance = p.distanceSquaredTo(otherPoint);
			 if (distance < prevMinDistance){
				 champ = otherPoint;
				 prevMinDistance = distance;
			 }
		}
		return champ;
	}

	// unit testing of the methods (optional)
	public static void main(String[] args) {

	}
}