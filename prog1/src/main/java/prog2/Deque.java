package prog2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private class Node {
		private Node prev;
		private Node next;
		private Item item;
	}

	private Node first;
	private Node last;
	private int size;

	public Deque() {
		size = 0;
		first = null;
		last = null;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	private void raiseInvalidItemError(Item item) {
		if (item == null) {
			throw new java.lang.NullPointerException("Item cannot be null :"
					+ item);
		}
	}

	private void raiseEmptyError() {
		if (size() == 0) {
			throw new java.util.NoSuchElementException("Size of queue:"
					+ size());
		}
	}

	public void addFirst(Item item) {
		raiseInvalidItemError(item);

		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		if (oldFirst != null)
			oldFirst.prev = first;
		size++;

		if (last == null) {
			last = first;
		}
	}

	public void addLast(Item item) {
		raiseInvalidItemError(item);

		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.prev = oldLast;
		if (oldLast != null)
			oldLast.next = last;
		size++;

		if (first == null) {
			first = last;
		}
	}

	public Item removeFirst() {
		raiseEmptyError();

		Item item = first.item;
		first = first.next;

		if (first != null) {
			first.prev = null;
		}
		size--;

		return item;
	}

	public Item removeLast() {
		raiseEmptyError();

		Item item = last.item;
		last = last.prev;

		if (last != null) {
			last.next = null;
		}
		size--;

		return item;
	}

	public Iterator<Item> iterator() {
		return new ListIterator(first);
	}

	private class ListIterator implements Iterator<Item> {

		private ListIterator(Node node) {
			this.current = node;
		}

		private Node current;

		public boolean hasNext() {
			return current != null;
		}

		public Item next() {
			if (current == null) {
				throw new NoSuchElementException();
			}

			Item item = current.item;
			current = current.next;
			return item;
		}

		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}

	}

	public static void main(String[] args) {

	}

}
