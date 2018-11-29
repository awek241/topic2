package topic2.classes;

import topic2.exceptions.EmptyStackException;
import topic2.interfaces.Position;
import topic2.interfaces.Stack;

public class LinkedStack<E> implements Stack<E> {

	private SingleNode<E> top;
	private SingleNode<E> next;
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
	 * gibt die Grösse n des LinkedStack's zurück
	 * 
	 */

	public int size() {
		return size;
	}

	@Override
	/**
	 * prüft ob der LinkedStack leer ist.
	 */
	public boolean isEmpty() {
		return top == null;
	}

	@Override
	/**
	 * gibt das Element vom letzthinzugefügten Knoten wieder ohne dies zu entfernen
	 */
	public E top() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException();
		return top.element();
	}

	@Override
	/**
	 * fügt einen neuen Knoten hinzu und gibt schneidet die Referenz vom bisherigen
	 * Top Knoten zu Next Knoten und neuer Knoten bekommt die Top Referenz.
	 */
	public void push(E e) {
		next = new SingleNode<E>(top, e);
		next.setNext(top);
		top = next;
		size++;
	}

	@Override
	/**
	 * gibt das Element vom letzthinzugefügten Knoten wieder und übergibt dem Next
	 * Knoten neu die Referenz als Head Knoten.
	 */

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
