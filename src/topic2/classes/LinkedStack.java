package topic2.classes;

import topic2.exceptions.EmptyStackException;
import topic2.interfaces.Position;
import topic2.interfaces.Stack;

public class LinkedStack<E> implements Stack<E> {

	// Top Knoten

	private SingleNode<E> head;
	private SingleNode<E> prev;

	protected class SingleNode<E> implements Position<E> {

		// TODO Prüfen ob noch bessere Anpassung möglich

		private E element;
		private SingleNode<E> next;

		public SingleNode(E element) {
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
		SingleNode<E> position = head;
		while (position != null) {
			count++;
			position = position.next;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	// TODO Prüfen
	public E top() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException();
		return head.element;
	}

	@Override
	public void push(E e) {
		prev = new SingleNode<E>(e);
		prev.setNext(head);
		head = prev;
	}

	@Override
	public E pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		E temp = head.element();
		head = prev.getNext();
		return temp;
		// prev.setNext(head);
	}
}
