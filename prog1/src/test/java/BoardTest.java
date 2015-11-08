import static org.junit.Assert.*;
import org.junit.Test;

public class BoardTest {

	@Test
	public void ConstructorThrowsNullExceptionForNullArgument() {
		try {
			new Board(null);
			fail("cannot reach here");
		} catch (Exception ex) {
			assertNotNull(ex);
		}
	}

	@Test
	public void Test_3x3_Array_Returns_3_As_Dimension() {
		int[][] blocks = new int[3][3];
		Board b = new Board(blocks);
		assertEquals(b.dimension(), 3);
	}

	@Test
	public void HammingTest() {
		int expectedHammingCount = 5;
		int[][] blocks = new int[3][];
		blocks[0] = new int[] { 8, 1, 3 };
		blocks[1] = new int[] { 4, 0, 2 };
		blocks[2] = new int[] { 7, 6, 5 };
		Board b = new Board(blocks);
		assertEquals(expectedHammingCount, b.hamming());
	}
	
	@Test
	public void ManhattanTest() {
		int expectedManhattanCount = 10;
		int[][] blocks = new int[3][];
		blocks[0] = new int[] { 8, 1, 3 };
		blocks[1] = new int[] { 4, 0, 2 };
		blocks[2] = new int[] { 7, 6, 5 };
		Board b = new Board(blocks);
		assertEquals(expectedManhattanCount, b.manhattan());
	}

	@Test
	public void FormatTest() {
		String expected = "3\r\n" + " 8  1  3\r\n" + " 4  0  2\r\n"
				+ " 7  6  5\r\n";
		int[][] blocks = new int[3][];
		blocks[0] = new int[] { 8, 1, 3 };
		blocks[1] = new int[] { 4, 0, 2 };
		blocks[2] = new int[] { 7, 6, 5 };

		Board b = new Board(blocks);
		assertEquals(expected, b.toString());

	}

}
