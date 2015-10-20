package prog2;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueueTest {

	@Test
	public void AddNullItemThrowsException() {
		try {
			RandomizedQueue<String> q = new RandomizedQueue<String>();
			q.enqueue(null);
			fail("Did not throw exception");
		} catch (NullPointerException ex) {
			assertNotNull(ex);
		}
	}
	
	@Test
	public void DequeueZeroItemsThrowsException(){
		try{
			RandomizedQueue<String> q = new RandomizedQueue<String>();
			q.dequeue();
			fail("Did not throw exception");
		}
		catch (NoSuchElementException ex){
			
		}
	}
	
	@Test
	public void SampleZeroItemsThrowsException(){
		try{
			RandomizedQueue<String> q = new RandomizedQueue<String>();
			q.sample();
			fail("Did not throw exception");
		}
		catch (NoSuchElementException ex){
			
		}
	}
	
	@Test
	public void RemoveOperationOnIteratorThrowsException(){
		try{
			new RandomizedQueue<String>().iterator().remove();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException ex){
			
		}
	}
	
	@Test
	public void NextOnIteratorWithEmptyCollectionThrowsException(){
		try{
			new RandomizedQueue<String>().iterator().next();
			fail("Did not throw exception");
		} catch (NoSuchElementException ex){
			
		}
	}
	
	@Test
	public void EmptyCollectionReturnTrueForIsEmpty(){
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		assertEquals(true, q.isEmpty());
	}
	
	@Test
	public void AddSingleItemToCollection(){
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		q.enqueue("item");
		assertEquals(1, q.size());
	}
	
	@Test
	public void RetrieveSingleItemFromSingleItemCollection(){
		String item = "item";
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		q.enqueue(item);
		assertEquals(item, q.dequeue());
		assertEquals(EMPTY_COUNT, q.size());
	}
	
	
	@Test
	public void SampleItemFromSingleItemCollection(){
		String item = "item";
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		q.enqueue(item);
		assertEquals(item, q.sample());
		assertEquals(SIZE_1_COUNT, q.size());
	}
		
	@Test
	public void ResizeCollectionUpUsingPowersOfTwo(){
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		q.enqueue("1");
		q.enqueue("2");
		assertEquals(SIZE_2_COUNT, q.size());
	}
	
	@Test
	public void ResizeCollectionUpUsingPowersOfTwo_2(){
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		q.enqueue("1");
		q.enqueue("2");
		q.enqueue("3");
		q.enqueue("4");
		q.enqueue("5");
		assertEquals(SIZE_5_COUNT, q.size());
	}
	
	@Test
	public void ResizeCollectionDownUsingOneForthRule(){
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		q.enqueue("1");
		q.enqueue("2");
		q.enqueue("3");
		q.enqueue("4");
		q.enqueue("5");
		
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		
		assertEquals(EMPTY_COUNT, q.size());
	}
	
	@Test
	public void RandomIteratorTest(){
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		q.enqueue("1");
		q.enqueue("2");
		q.enqueue("3");
		q.enqueue("4");
		q.enqueue("5");
		
		for (String s : q) {
			StdOut.println(s);
		}
	}
	
	private static int EMPTY_COUNT = 0;
	private static int SIZE_1_COUNT = 1;
	private static int SIZE_2_COUNT = 2;
	private static int SIZE_5_COUNT = 5;
}
