import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Test;

import edu.princeton.cs.algs4.StdOut;


public class SolverTest {

//	@Test
//	public void testGeneric() {
//		int[][] blocks = new int[3][];
//		blocks[0] = new int[] { 0, 1, 3 };
//		blocks[1] = new int[] { 4, 2, 5 };
//		blocks[2] = new int[] { 7, 8, 6 };
//		Board initial = new Board(blocks);
//		Solver solver = new Solver(initial);
//		
//		if (!solver.isSolvable())
//			StdOut.println("No solution possible");
//		else {
//			StdOut.println("Minimum number of moves = " + solver.moves());
//			for (Board board : solver.solution())
//				StdOut.println(board);
//		}
//	}
	
	
	private void TestFile(String fileName){
		
		String dir = "C:/coursera/Algorithms, Part I/assignments/8puzzle/";
		FileReader fr;
		try {
			fr = new FileReader(dir + fileName);
			BufferedReader br = new BufferedReader(fr);
			int dim = Integer.parseInt( br.readLine());
			int[][] arr = new int[dim][dim];
			for (int i = 0; i < arr.length; i++) {
				String[] numbers = br.readLine().trim().replace("  ", " ").split(" ");
				for (int j = 0; j < arr.length; j++) {
					int number = Integer.parseInt( numbers[j]); 
					arr[i][j] = number;
				}
			}
			
			Board initial = new Board(arr);
			Solver solver = new Solver(initial);
			
			if (!solver.isSolvable())
				StdOut.println("No solution possible");
			else {
				StdOut.println(fileName + "\r\nMinimum number of moves = " + solver.moves());
//				for (Board board : solver.solution())
//					StdOut.println(board);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFileName(){
		
		for (int i = 0; i <= 50; i++) {
			String formatted = String.format("%02d", i);
			String fileName = "puzzle" + formatted + ".txt";
			TestFile(fileName);
		}
		
		
		
	}

}
