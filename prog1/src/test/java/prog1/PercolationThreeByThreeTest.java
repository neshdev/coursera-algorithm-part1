package prog1;

import static org.junit.Assert.*;

import org.junit.Test;

//3x3 test
public class PercolationThreeByThreeTest {

	private static int GRID_SIZE_3x3 = 3;
	private Percolation p;
	
	public PercolationThreeByThreeTest() {
		
		
	}
	
	@Test
	public void ConnectingTopLeftShouldNotPercolate() {
	    //should connect to right and bottom
		p = new Percolation(GRID_SIZE_3x3);
		p.open(1, 1);		
		assertFalse(p.percolates());
	}
	
	@Test
	public void ConnectingTopMiddleShouldNotPercolate() {
		//should connect to left, right, bottom
		
		p = new Percolation(GRID_SIZE_3x3);
		p.open(1, 2);		
		assertFalse(p.percolates());
	}
	
	@Test
	public void ConnectingTopRightShouldNotPercolate() {
		//should connect to left, bottom
		
		p = new Percolation(GRID_SIZE_3x3);
		p.open(1, 3);		
		assertFalse(p.percolates());
	}
	
	
	@Test
	public void ConnectingMiddleLeftShouldPercolate() {
		//should connect to left, bottom
		
		p = new Percolation(GRID_SIZE_3x3);
		p.open(2, 1);		
		assertTrue(p.percolates());
	}
	
	@Test
	public void ConnectingCenterShouldPercolate() {
    //should connect to left, right , top, bottom 
		p = new Percolation(GRID_SIZE_3x3);
		p.open(2, 2);		
		assertTrue(p.percolates());
	}
	
	@Test
	public void ConnectingMiddleRightShouldPercolate() {
		//should connect to left, top, bottom
		
		p = new Percolation(GRID_SIZE_3x3);
		p.open(2, 3);		
		assertTrue(p.percolates());
	}
	
	
	@Test
	public void ConnectingBottomLeftShouldNotPercolate() {
		//should connect to right, top
		
		p = new Percolation(GRID_SIZE_3x3);
		p.open(3, 1);		
		assertFalse(p.percolates());
	}
	
	@Test
	public void ConnectingBottomMiddleShouldNotPercolate() {
		//should connect to left, right, top
		
		p = new Percolation(GRID_SIZE_3x3);
		p.open(3, 2);		
		assertFalse(p.percolates());
	}
	
	@Test
	public void ConnectingBottomRightShouldNotPercolate() {
		//should connect to left, top
		
		p = new Percolation(GRID_SIZE_3x3);
		p.open(3, 3);		
		assertFalse(p.percolates());
	}

}
