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
		
		tree.draw();
	}

}
