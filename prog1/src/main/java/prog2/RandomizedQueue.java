package prog2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private int N;
	private Item[] items;

	public RandomizedQueue() {
		items = (Item[]) new Object[1];
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	private void raiseErrorForNullItem(Item item) {
		if (item == null) {
			throw new NullPointerException("item is null:" + item);
		}
	}

	private void raiseErrorForEmptyCollection() {
		if (isEmpty()) {
			throw new NoSuchElementException("No items in the list");
		}
	}

	public void enqueue(Item item) {
		raiseErrorForNullItem(item);
		if (N > 0 && N == items.length)
			resize(items.length * 2);
		items[N++] = item;
	}

	public Item dequeue() {
		raiseErrorForEmptyCollection();

		swapRandom();

		Item item = items[--N];
		items[N] = null;

		if (N > 0 && N == items.length / 4)
			resize(items.length / 2);
		return item;
	}

	public Item sample() {
		raiseErrorForEmptyCollection();

		swapRandom();

		Item item = items[N - 1];
		return item;
	}

	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			copy[i] = items[i];
		}
		this.items = copy;
	}

	private void swapRandom() {
		if (N > 1) {
			int rand = StdRandom.uniform(0, N - 1);
			swap(rand, N - 1);
		}
	}

	private void swap(int i, int j) {
		Item temp = items[j];
		items[j] = items[i];
		items[i] = temp;
	}

	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {

		private int index = 0;
		private int[] random;

		public ListIterator() {
			random = new int[N];
			for (int i = 0; i < N; i++) {
				random[i] = i;
			}
			StdRandom.shuffle(random);
		}

		public boolean hasNext() {
			return N > 0 && random.length > index;
		}

		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException("No more items in list");
			}
			int randomIndex = random[index];
			index++;
			return items[randomIndex];
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	public static void main(String[] args) {

	}

}
