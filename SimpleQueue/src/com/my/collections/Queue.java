package com.my.collections;

import java.util.Iterator;

/**
 * An interface of simple queue which supports operations like push, pop, peek
 * and iterator. The queue operations support only Integer, Long, Double and
 * String as data.
 * 
 * @author Mukund
 */
public interface Queue<T extends Object> {

	/**
	 * Method to return the queue iterator
	 * 
	 * @return Queue Iterator
	 */
	public Iterator<T> getIterator();

	/**
	 * Method to peek the queue face
	 * 
	 * @return front data
	 */
	public T peek();

	/**
	 * Method to pop from front of the queue
	 * 
	 * @return front data
	 */
	public T pop();

	/**
	 * Push method for inserting Integer data at end of the queue
	 * 
	 * @param data
	 */
	public void push(T data);

	/**
	 * Push method for inserting Long data at end of the queue
	 * 
	 * @param data
	 */
	// public void push(Long data);

	/**
	 * Push method for inserting Double data at end of the queue
	 * 
	 * @param data
	 */
	// public void push(Double data);

	/**
	 * Push method for inserting String data at end of the queue
	 * 
	 * @param data
	 */
	// public void push(String data);
}
