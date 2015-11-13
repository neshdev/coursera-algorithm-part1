import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class KdTree {

	private Node root;
	private int N;
	
	private static class Node
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
		
		root = put(root, p, Orientation.HORIZONTAL);
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
	
	private Node put(Node x, Point2D p, Orientation orientation){
		if ( x == null) {
			Node n = new Node();
			n.p = p;
			n.rect = new RectHV(0, 0, 1, 1);
			N++;
			n.orientation = orientation;
			return n; 
		}
		
		if ( orientation == Orientation.HORIZONTAL){
			int cmpX = compareX(x.p, p);
			if ( cmpX > 0) {
				x.leftbottom = put(x.leftbottom, p, oppositeOrienation(orientation));
				x.leftbottom.rect = boundaryLeft(x.rect, x.p);
			}
			if ( cmpX < 0){
				x.righttop = put(x.righttop, p, oppositeOrienation(orientation));
				x.righttop.rect = boundaryRight(x.rect, x.p);
			}
			return x;
		} else {
			int cmpY = compareY(x.p, p);
			if ( cmpY > 0) {
				x.leftbottom = put(x.leftbottom, p, oppositeOrienation(orientation));
				x.leftbottom.rect = boundaryBottom(x.rect, x.p);
			}
			if ( cmpY < 0) {
				x.righttop = put(x.righttop, p, oppositeOrienation(orientation));
				x.righttop.rect = boundaryTop(x.rect, x.p);
			}
			return x;
		}
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
			else                return x.p;
		} else {
			int cmpY = compareY(x.p, p);
			if      ( cmpY > 0) return get(x.leftbottom, p, oppositeOrienation(orienation));
			else if ( cmpY < 0) return get(x.righttop, p, oppositeOrienation(orienation));
			else                return x.p;
		}
	}
	
	
	private enum Orientation{
		HORIZONTAL,
		VERTICAL
	}
	
	// does the set contain point p?
	public boolean contains(Point2D p) {
		NotNullValidation(p);
		return get(p) != null;
	}

	// draw all points to standard draw
	public void draw() {
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            
            StdOut.println("Point:" + x.p + "\tRect:" + x.rect);
            drawLine(x.rect, x.p, x.orientation);
            StdDraw.setPenColor(StdDraw.BLACK);
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
		StdDraw.setPenColor(StdDraw.RED);
		double x0 = p.x();
		double y0 = rect.ymin();
		double x1 = p.x();
		double y1 = rect.ymax();
		StdDraw.line(x0, y0, x1, y1);
	}
	
	private void drawHorizontal(RectHV rect, Point2D p){
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
		return null;
	}

	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		NotNullValidation(p);

		if (this.isEmpty())
			return null;
		
		
		nearest(root, p, Orientation.HORIZONTAL);
		return null;
	}
	
	private Node nearest(Node x, Point2D p, Orientation orienation){
		Point2D closestPoint = null;
		return null;
	}

	// unit testing of the methods (optional)
	public static void main(String[] args) {

	}
}
