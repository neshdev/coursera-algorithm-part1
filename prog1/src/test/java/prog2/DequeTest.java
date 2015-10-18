package prog2;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.princeton.cs.algs4.StdOut;

public class DequeTest {

	@Test
	public void test() {
		Deque<String> d = new Deque<String>();
		d.addFirst("a");
		d.addFirst("b");
		d.addFirst("c");
		d.addLast("d");
		d.addLast("e");
		d.addLast("f");
		
		assertEquals("f", d.removeLast());
		assertEquals("c", d.removeFirst());
		assertEquals("e", d.removeLast());
		assertEquals("b", d.removeFirst());
		assertEquals("d", d.removeLast());
		assertEquals("a", d.removeFirst());
	}
	
	@Test
	public void test2(){
		Deque<String> d = new Deque<String>();
		d.addFirst("a");
		d.addFirst("b");
		d.addFirst("c");
		d.addLast("d");
		d.addLast("e");
		d.addLast("f");
		
		String[] items = new String[6];
		items[0] = "c";
		items[1] = "b";
		items[2] = "a";
		items[3] = "d";
		items[4] = "e";
		items[5] = "f";
		
		int count = 0;
		
		for (String item : d) {
			StdOut.println(item);
			assertEquals(items[count], item);
			count++;
		}
	}

}
