import java.util.ArrayList;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class KdTree {

	private Node root;
	private int N;
	
	private class Node
	{
		private Point2D p;
		private RectHV rect;
		private Node leftbottom;
		private Node righttop;
		private Orientation orientation;
	}
	
	private void NotNullValidation(Object obj) {
		if (obj == null)
			throw new NullPointerException("obj cannot be null");
	}

	public KdTree() // construct an empty set of points
	{
		N = 0;
		root = null;
	}

	// is the set empty?
	public boolean isEmpty() {
		return root == null;
	}

	// number of points in the set
	public int size() {
		return N;
	}

	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		NotNullValidation(p);
		root = put(root, null, p, Orientation.HORIZONTAL);
	}
	
	private Orientation oppositeOrienation(Orientation orienation){
		if (orienation == Orientation.HORIZONTAL)
			return Orientation.VERTICAL;
		else
			return Orientation.HORIZONTAL;
	}
	
	private RectHV boundaryLeft(RectHV rect, Point2D p){
		double xmin = rect.xmin();
		double xmax = p.x();
		double ymin = rect.ymin();
		double ymax = rect.ymax();
		return new RectHV(xmin, ymin, xmax, ymax);
	}
	
	private RectHV boundaryRight(RectHV rect, Point2D p){
		double xmin = p.x();
		double xmax = rect.xmax();
		double ymin = rect.ymin();
		double ymax = rect.ymax();
		return new RectHV(xmin, ymin, xmax, ymax);
	}
	
	private RectHV boundaryBottom(RectHV rect, Point2D p){
		double xmin = rect.xmin();
		double xmax = rect.xmax();
		double ymin = rect.ymin();
		double ymax = p.y();
		return new RectHV(xmin, ymin, xmax, ymax);
	}
	
	private RectHV boundaryTop(RectHV rect, Point2D p){
		double xmin = rect.xmin();
		double xmax = rect.xmax();
		double ymin = p.y();
		double ymax = rect.ymax();
		return new RectHV(xmin, ymin, xmax, ymax);
	}
	
	private RectHV boundary(Node x, Point2D p){
		RectHV rect = null;
		
		int compareX = compareX(p, x.p);
		int compareY = compareY(p, x.p);
		
		if ( x.orientation == Orientation.HORIZONTAL){
			if ( compareX > 0) rect = boundaryRight(x.rect, x.p);
			else if ( compareX < 0) rect = boundaryLeft(x.rect, x.p);
			else if ( compareY > 0) rect = boundaryTop(x.rect, x.p);
			else if ( compareX < 0) rect = boundaryBottom(x.rect, x.p);
		}
		
		if ( x.orientation == Orientation.VERTICAL){
			if ( compareY > 0) rect = boundaryTop(x.rect, x.p);
			else if ( compareY < 0) rect = boundaryBottom(x.rect, x.p);
			else if ( compareX > 0) rect = boundaryRight(x.rect, x.p);
			else if ( compareX < 0) rect = boundaryLeft(x.rect, x.p);
			
		}
		
		return rect;
	}
	
	
	private Node put(Node x, Node parent, Point2D p, Orientation orientation){
		if ( x == null){
			x = new Node();
			x.orientation = orientation;
			if ( parent == null)	x.rect = new RectHV(0, 0, 1, 1);
			else x.rect = boundary(parent, p);
			//StdOut.println(x.rect);
			
			x.p = p;
			N++;
			return x;
		}
		
		int comp = x.p.compareTo(p);
		if ( comp == 0)
			return x;
		
		int compareX = compareX(p, x.p);
		int compareY = compareY(p, x.p);
		
		
		if (orientation == Orientation.HORIZONTAL){
			if ( compareX > 0){
				x.righttop = put(x.righttop, x, p, oppositeOrienation(orientation));
			}
			else if (compareX < 0){
				x.leftbottom = put(x.leftbottom, x, p, oppositeOrienation(orientation));
			}
			else if (compareY > 0){
				x.righttop = put(x.righttop, x, p, oppositeOrienation(orientation));
			}
			else if (compareY < 0){
				x.leftbottom = put(x.leftbottom, x, p, oppositeOrienation(orientation));
			}
		}
		
		if (orientation == Orientation.VERTICAL){
			if ( compareY > 0){
				x.righttop = put(x.righttop, x, p, oppositeOrienation(orientation));
			}
			else if ( compareY < 0) {
				x.leftbottom = put(x.leftbottom, x, p, oppositeOrienation(orientation));
			}
			else if (  compareX > 0){
				x.righttop = put(x.righttop, x, p, oppositeOrienation(orientation));
			}
			else if ( compareX < 0) {
				x.leftbottom = put(x.leftbottom, x, p, oppositeOrienation(orientation));
			}
		}
		
		return x;
			
	}
	
	private int compareX(Point2D v, Point2D w){
		if (v.x() > w.x()) return 1;
		else if (v.x() < w.x()) return -1;
		else return 0;
	}
	
	private int compareY(Point2D v, Point2D w){
		if (v.y() > w.y()) return 1;
		else if (v.y() < w.y()) return -1;
		else return 0;
	}
	
	private Point2D get(Point2D p){
		return get(root, p, Orientation.HORIZONTAL);
	}
	
	private Point2D get(Node x, Point2D p, Orientation orienation){
		if ( x== null) return null;
		if ( orienation == Orientation.HORIZONTAL){
			int cmpX = compareX(x.p, p);
			if      ( cmpX > 0) return get(x.leftbottom, p, oppositeOrienation(orienation));
			else if ( cmpX < 0) return get(x.righttop, p, oppositeOrienation(orienation));
			else if ( x.p.compareTo(p) == 0 ) return x.p;
			else return null;
		} else {
			int cmpY = compareY(x.p, p);
			if      ( cmpY > 0) return get(x.leftbottom, p, oppositeOrienation(orienation));
			else if ( cmpY < 0) return get(x.righttop, p, oppositeOrienation(orienation));
			else if ( x.p.compareTo(p) == 0 ) return x.p;
			else return null;
		}
	}
	
	
	private enum Orientation{
		HORIZONTAL,
		VERTICAL
	}
	
	// does the set contain point p?
	public boolean contains(Point2D p) {
		NotNullValidation(p);
		Point2D results = get(p); 
		return results != null;
	}

	// draw all points to standard draw
	public void draw() {
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            
            //StdOut.println("Point:" + x.p + "\tRect:" + x.rect);
            drawLine(x.rect, x.p, x.orientation);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.01);
            x.p.draw();
            
            queue.enqueue(x.leftbottom);
            queue.enqueue(x.righttop);
        }
	}
	
	private void drawLine(RectHV rect, Point2D p, Orientation orientation){
		if ( orientation == Orientation.HORIZONTAL){
			drawVertialLine(rect, p);
		}
		
		if ( orientation == Orientation.VERTICAL){
			drawHorizontal(rect, p);
		} 
	}
	
	private void drawVertialLine(RectHV rect, Point2D p){
		StdDraw.setPenRadius();
		StdDraw.setPenColor(StdDraw.RED);
		double x0 = p.x();
		double y0 = rect.ymin();
		double x1 = p.x();
		double y1 = rect.ymax();
		StdDraw.line(x0, y0, x1, y1);
	}
	
	private void drawHorizontal(RectHV rect, Point2D p){
		StdDraw.setPenRadius();
		StdDraw.setPenColor(StdDraw.BLUE);
		double x0 = rect.xmin();
		double y0 = p.y();
		double x1 = rect.xmax();
		double y1 = p.y();
		StdDraw.line(x0, y0, x1, y1);
	}

	// all points that are inside the rectangle
	public Iterable<Point2D> range(RectHV rect) {
		NotNullValidation(rect);
		
		ArrayList<Point2D> points = new ArrayList<Point2D>();
		if (N == 0)
			return points;
		
		range(root, rect, Orientation.HORIZONTAL, points);
		
		return points;
	}
	
	private Node range(Node x, RectHV rect, Orientation orientation, ArrayList<Point2D> points){
		
		if (x == null) return x;
		
		if (rect.contains(x.p)){
			points.add(x.p);
		}
		
		if (orientation == Orientation.HORIZONTAL ){
			if (isBetweenLeftAndRight(rect, x.p)){
				range(x.leftbottom, rect, oppositeOrienation(orientation), points);
				range(x.righttop, rect, oppositeOrienation(orientation), points);
			} 
			else if (isLeft(rect, x.p)) range(x.leftbottom, rect, oppositeOrienation(orientation), points);
			else if (isRight(rect, x.p)) range(x.righttop, rect, oppositeOrienation(orientation), points);
			
			return x;
		} else {
			if (isBetweenTopAndBottom(rect, x.p)){
				range(x.leftbottom, rect, oppositeOrienation(orientation), points);
				range(x.righttop, rect, oppositeOrienation(orientation), points);
			}
			else if (isBottom(rect, x.p)) range(x.leftbottom, rect, oppositeOrienation(orientation), points);
			else if (isTop(rect, x.p)) range(x.righttop, rect, oppositeOrienation(orientation), points);
			
			return x;
		}
	}
	
	private boolean isBottom(RectHV rect, Point2D p){
		return p.y() > rect.ymax();
	}
	
	private boolean isTop(RectHV rect, Point2D p){
		return p.y() < rect.ymin();
	}
	
	private boolean isBetweenTopAndBottom(RectHV rect, Point2D p){
		return (p.y() >= rect.ymin()) && (rect.ymax() > p.y());
	}
	
	private boolean isLeft(RectHV rect, Point2D p){
		return p.x() > rect.xmax();
	}
	
	private boolean isRight(RectHV rect, Point2D p){
		return p.x() < rect.xmin();
	}
	
	private boolean isBetweenLeftAndRight(RectHV rect, Point2D p){
		return (p.x() >= rect.xmin() ) && (rect.xmax() >= p.x());
	}
	

	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		NotNullValidation(p);

		if (this.isEmpty())
			return null;
		
		return null;
	}
	

	// unit testing of the methods (optional)
	public static void main(String[] args) {

	}
}
