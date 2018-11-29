package topic2.classes;

import topic2.exceptions.EmptyQueueException;
import topic2.interfaces.Position;
import topic2.interfaces.Queue;

public class LinkedQueue<E> implements Queue<E> {

	private SingleNode<E> front;
	private SingleNode<E> rear;
	private int size;

	@SuppressWarnings("hiding")
	protected class SingleNode<E> implements Position<E> {

		public E element;
		public SingleNode<E> next = null;

		public SingleNode(SingleNode<E> next, E element) {
			this.next = next;
			this.element = element;
		}

		public void setNext(SingleNode<E> next) {
			this.next = next;
		}

		public SingleNode<E> getNext() {
			return next;
		}

		@Override
		public E element() {
			return element;
		}

	}

	@Override
	/**
	 * gibt die Gr�sse n des LinkedQueue's zur�ck
	 */
	public int size() {
		return size;
	}

	@Override
	/**
	 * pr�ft ob der LinkedQueue leer ist.
	 */
	public boolean isEmpty() {
		return front == null;
	}

	@Override
	/**
	 * gibt das Objekt in dem als erstes hinzugef�gten Knoten zur�ck ohne dieses zu
	 * l�schen
	 */
	public E front() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException();
		return front.element();
	}

	@Override
	/**
	 * f�gt einen neuen Knoten am Ende der Queue hinzu und vergr�ssert die Gr�sse um
	 * eins.
	 */
	public void enqueue(E e) {
		if (isEmpty()) {
			front = new SingleNode<E>(front, e);
			front.next = front;
			size++;
		}
		front.next = rear;
		rear = new SingleNode<E>(rear, e);
		rear.setNext(front);
		size++;
	}

	@Override
	/**
	 * gibt das Element vom ersthinzugef�gten Knoten wieder und �bergibt dem Next
	 * Knoten neu die Referenz als Front Knoten.
	 */
	// TODO Gr�sse n muss beim pop noch um eins reduziert werden.

	public E dequeue() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		E temp = front.element();
		front = front.next;
		size--;
		return temp;
	}

}
