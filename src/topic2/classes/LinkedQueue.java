package topic2.classes;

import topic2.exceptions.EmptyQueueException;
import topic2.interfaces.Position;
import topic2.interfaces.Queue;

/** The LinkedQueue will consist of SingleNode Objects */
public class LinkedQueue<E> implements Queue<E> {

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
	private SingleNode front;
	private SingleNode rear;
	private int size;

	/** Returns the current size. */
	@Override
	public int size() {
		return size;
	}

	/** Checks if the LinkedStack is empty. */
	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	/** Returns the object in the first node added without deleting it. */
	@Override
	public E front() throws EmptyQueueException {
		if (size == 0)
			throw new EmptyQueueException();
		return front.element();
	}

	/** Adds a new node to the end of the queue and increases the size by one. */
	@Override
	public void enqueue(E e) {
		SingleNode newNode = new SingleNode(front, e);
		if (size == 0) {
			front = newNode;
			rear = newNode;

		} else {
			rear.next = newNode;
			rear = newNode;
		}
		size++;
	}

	/**
	 * Returns the element of the first node added and passes the reference as front
	 * node to the next node and reduces the size by one.
	 */
	@Override
	public E dequeue() throws EmptyQueueException {
		if (size == 0) {
			throw new EmptyQueueException();
		}
		E temp = front.element();
		front = front.next;
		size--;
		return temp;
	}

}
