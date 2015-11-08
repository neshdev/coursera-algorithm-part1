import static org.junit.Assert.*;

import org.junit.Test;

import edu.princeton.cs.algs4.StdOut;


public class SolverTest {

	@Test
	public void test() {
		int[][] blocks = new int[3][];
		blocks[0] = new int[] { 8, 1, 3 };
		blocks[1] = new int[] { 4, 0, 2 };
		blocks[2] = new int[] { 7, 6, 5 };
		Board initial = new Board(blocks);
		Solver solver = new Solver(initial);
		
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}
	}

}
