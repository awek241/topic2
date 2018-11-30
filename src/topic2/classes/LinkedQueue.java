package topic2.classes;

import topic2.exceptions.EmptyQueueException;
import topic2.interfaces.Position;
import topic2.interfaces.Queue;

public class LinkedQueue<E> implements Queue<E> {

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

	private SingleNode<E> front;
	private SingleNode<E> rear;
	private int size = 0;

	@Override
	/**
	 * gibt die Grösse n des LinkedQueue's zurück
	 */
	public int size() {
		return size;
	}

	@Override
	/**
	 * prüft ob der LinkedQueue leer ist.
	 */
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	@Override
	/**
	 * gibt das Objekt in dem als erstes hinzugefügten Knoten zurück ohne dieses zu
	 * löschen
	 */
	public E front() throws EmptyQueueException {
		if (size == 0)
			throw new EmptyQueueException();
		return front.element();
	}

	@Override
	/**
	 * fügt einen neuen Knoten am Ende der Queue hinzu und vergrössert die Grösse um
	 * eins.
	 */
	public void enqueue(E e) {
		SingleNode<E> newNode = new SingleNode<E>(front, e);
		if (size == 0) {
			front = newNode;
			rear = newNode;

		} else {
			rear.next = newNode;
			rear = newNode;
		}
		size++;
	}

	@Override
	/**
	 * gibt das Element vom ersthinzugefügten Knoten wieder und übergibt dem Next
	 * Knoten neu die Referenz als Front Knoten.
	 */
	// TODO Grösse n muss beim pop noch um eins reduziert werden.

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
