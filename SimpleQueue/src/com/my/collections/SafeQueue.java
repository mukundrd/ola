package com.my.collections;

import java.util.Iterator;

/**
 * An implementation of simple queue which supports operations like push, pop,
 * peek and iterator. The queue operations support only Integer, Long, Double
 * and String as data. Also supports concurrent operations.
 * 
 * @author Mukund
 */
public class SafeQueue<T extends Object> implements Queue<T> {

	private Object lock = new Object();

	private Node start;
	private Node end;

	// private List<QueueIterator> iterators = new ArrayList<QueueIterator>();

	public SafeQueue() {
		Class<?> gClass = getClass().getEnclosingClass();
		assert (gClass == Integer.class || gClass == Long.class || gClass == Double.class || gClass == String.class);
	}

	private class Node {
		T data;
		Node prev;
		Node next;
	}

	private class QueueIterator implements Iterator<T> {

		private Node current;

		QueueIterator(Node root) {
			this.current = root;
		}

		@Override
		public boolean hasNext() {
			synchronized (lock) {
				return current != null;
			}
		}

		@Override
		public T next() {
			synchronized (lock) {
				Node node = current;
				current = current.next;
				return node.data;
			}
		}

	}

	/**
	 * Method to return the queue iterator
	 * 
	 * @return Queue Iterator
	 */
	public Iterator<T> getIterator() {
		SafeQueue<T>.QueueIterator iterator;
		synchronized (lock) {
			iterator = new QueueIterator(start);
			// iterators.add(iterator);
		}
		return iterator;
	}

	/**
	 * Method to peek the queue face
	 * 
	 * @return front data
	 */
	public T peek() {
		synchronized (lock) {
			return start.data;
		}
	}

	/**
	 * Method to pop from front of the queue
	 * 
	 * @return front data
	 */
	public T pop() {

		synchronized (lock) {
			if (start == null) {
				return null;
			}

			Node pop = start;
			Node t = start.next;
			start.prev = null;
			start.next = null;
			start = t;

			if (start == null) {
				end = null;
			}
			T data = pop.data;

			System.out.println(String.format("Popping %1$d from %2$s", data, Thread.currentThread().getName()));

			return data;
		}

	}

	/**
	 * Push method for inserting Integer data at end of the queue
	 * 
	 * @param data
	 */
	public void push(T data) {
		if (data == null) {
			throw new NullPointerException("Data cannot be null");
		}
		if (!(data instanceof Integer || data instanceof Long || data instanceof Double || data instanceof String)) {
			throw new IllegalArgumentException("Invalud data type " + data.getClass()
					+ ", allowed data types are Integer, Long, Double, String");
		}
		pushInternal(data);
	}

	/**
	 * Push method for inserting Long data at end of the queue
	 * 
	 * @param data
	 */
	/*
	 * public void push(Long data) { pushInternal(data); }
	 */

	/**
	 * Push method for inserting Double data at end of the queue
	 * 
	 * @param data
	 */
	/*
	 * public void push(Double data) { pushInternal(data); }
	 */

	/**
	 * Push method for inserting String data at end of the queue
	 * 
	 * @param data
	 */
	/*
	 * public void push(String data) { pushInternal(data); }
	 */

	private void pushInternal(T data) {
		synchronized (lock) {
			Node node = new Node();
			node.data = data;

			if (start == null) {
				start = node;
				end = node;
				System.out.println(String.format("Pushing %1$d from %2$s", data, Thread.currentThread().getName()));
				return;
			}

			node.prev = end;
			end.next = node;
			end = node;
			System.out.println(String.format("Pushing %1$d from %2$s", data, Thread.currentThread().getName()));
		}

		return;
	}
}
