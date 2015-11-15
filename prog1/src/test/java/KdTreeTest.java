import static org.junit.Assert.*;

import org.junit.Test;

import edu.princeton.cs.algs4.Point2D;


public class KdTreeTest {

	@Test
	public void test() {
		KdTree tree = new KdTree();
		tree.insert(new Point2D(0.7, 0.2));
		tree.insert(new Point2D(0.5, 0.4));
		tree.insert(new Point2D(0.2, 0.3));
		tree.insert(new Point2D(0.4, 0.7));
		tree.insert(new Point2D(0.9, 0.6));
		assertEquals(tree.size(), 5);
		
	}
	
	@Test
	public void FindPointInSearchTest(){
		Point2D searchPoint1 = new Point2D(0.7, 0.2); 
		Point2D searchPoint2 = new Point2D(0.5, 0.4);
		Point2D searchPoint3 = new Point2D(0.2, 0.3);
		Point2D searchPoint4 = new Point2D(0.4, 0.7);
		Point2D searchPoint5 = new Point2D(0.9, 0.6);
		
		KdTree tree = new KdTree();
		tree.insert(searchPoint1);
		tree.insert(searchPoint2);
		tree.insert(searchPoint3);
		tree.insert(searchPoint4);
		tree.insert(searchPoint5);
		
		assertTrue(tree.contains(searchPoint1));
		assertTrue(tree.contains(searchPoint2));
		assertTrue(tree.contains(searchPoint3));
		assertTrue(tree.contains(searchPoint4));
		assertTrue(tree.contains(searchPoint5));
	}
	
	@Test
	public void UnableToFindPointInSearch_Test(){
		Point2D searchPoint1 = new Point2D(0.7, 0.2); 
		Point2D searchPoint2 = new Point2D(0.5, 0.4);
		Point2D searchPoint3 = new Point2D(0.2, 0.3);
		Point2D searchPoint4 = new Point2D(0.4, 0.7);
		Point2D searchPoint5 = new Point2D(0.9, 0.6);
		Point2D searchPoint6 = new Point2D(0.2, 0.2);
		
		KdTree tree = new KdTree();
		tree.insert(searchPoint1);
		tree.insert(searchPoint2);
		tree.insert(searchPoint3);
		tree.insert(searchPoint4);
		tree.insert(searchPoint5);
		
		boolean results = tree.contains(searchPoint6);
		
		assertFalse(results);
	}

}
