package prog1;

import static org.junit.Assert.*;

import org.junit.Test;

public class PercTest2 {

	@Test
	public void test() {
		Percolation p = new Percolation(5);
		p.open(3, 3);
		p.open(1, 3);
		p.open(3, 2);
		p.open(4, 1);
		p.open(3, 5);
		assertEquals(true,p.percolates());
	}

}
