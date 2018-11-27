package topic2.classes;

import topic2.exceptions.EmptyStackException;
import topic2.interfaces.Position;
import topic2.interfaces.Stack;

public class LinkedStack<E> implements Stack<E> {

	private SingleNode<E> top; // Referenz zum letzthinzugefügten Knoten
	private SingleNode<E> next;

	protected class SingleNode<E> implements Position<E> {

		// TODO Prüfen ob noch bessere Anpassung möglich

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
	public int size() {
		int count = 0;
		SingleNode<E> position = top;
		while (position != null) {
			count++;
			position = position.next;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		return top == null;
	}

	@Override
	// TODO Prüfen
	public E top() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException();
		return top.element;
	}

	@Override
	public void push(E e) {
		next = new SingleNode<E>(top, e);
		next.setNext(top);
		top = next;
	}

	@Override
	public E pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		E temp = top.element();
		top = top.next;
		return temp;
	}
}
