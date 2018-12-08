package topic2.classes;

import topic2.exceptions.EmptyStackException;
import topic2.interfaces.Position;
import topic2.interfaces.Stack;

/** The LinkedStack will consist of SingleNode Objects */

public class LinkedStack<E> implements Stack<E> {

	protected class SingleNode implements Position<E> {

		public E element;
		public SingleNode next = null;

		public SingleNode(SingleNode next, E element) {
			this.next = next;
			this.element = element;
		}

		public void setNext(SingleNode next) {
			this.next = next;
		}

		public SingleNode getNext() {
			return next;
		}

		@Override
		public E element() {
			return element;
		}
	}

	/** Class variables */
	private SingleNode top;
	private int size;

	/** Returns the current size. */
	@Override
	public int size() {
		return size;
	}

	/** Checks if the LinkedStack is empty. */
	@Override
	public boolean isEmpty() {
		return top == null;
	}

	/** Returns the object from the last node added without removing it. */
	@Override
	public E top() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException();
		return top.element();
	}

	/**
	 * Adds a new node with an element and returns the reference from the previous
	 * top node to Next node and new node gets the top reference and increases the
	 * size by one.
	 */
	@Override
	public void push(E e) {
		SingleNode next;
		next = new SingleNode(top, e);
		next.setNext(top);
		top = next;
		size++;
	}

	/**
	 * Returns the element of the last node added and passes the reference as head
	 * node to the next node and reduces the size by one.
	 */
	@Override
	public E pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		E temp = top.element();
		top = top.next;
		size--;
		return temp;
	}
}
