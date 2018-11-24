package topic2.classes;

import topic2.exceptions.EmptyListException;
import topic2.exceptions.InvalidPositionException;
import topic2.interfaces.List;
import topic2.interfaces.Position;

public class LinkedList<E> implements List {

	// TODO double node? momentan single
	protected class DoubleNode<E> implements Position<E> {

		public E element;
		public DoubleNode<E> previous = null;
		public DoubleNode<E> next = null;

		/**
		 * @param element
		 * @param next
		 * @param previous
		 */
		public DoubleNode(DoubleNode<E> previous, E element, DoubleNode<E> next) {
			this.previous = previous;
			this.element = element;
			this.next = next;
		}

		public DoubleNode() {
			element = null;
			next = null;
			previous = null;
		}

		// TODO lernen wieso DoubleNode<E> hier nicht funktioniert
		public DoubleNode(E element) {
			this.element = element;
		}

		@Override
		public E element() {
			return element;
		}

		public boolean compare(Object o) {
			return false;

		}

	}

	// *****INSTANZVARIABLEN***s**
	private DoubleNode<E> head;

	@Override
	public int size() {
		int count = 0;
		DoubleNode<E> position = head;
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
	public Position first() throws EmptyListException {
		if (head == null) {
			throw new EmptyListException();
		}
		return head;
	}

	@Override
	public Position last() throws EmptyListException {
		if (head == null) {
			throw new EmptyListException();
		}
		DoubleNode<E> node = head;
		while (node != null) {
			node = node.next;
		}
		return node;
	}

	@Override
	public Position before(Position p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position after(Position p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position insertFirst(Object e) {
		DoubleNode temp = new DoubleNode(null, e, head);
		head.previous = temp;
		head = temp;
		return head;
	}

	@Override
	public Position insertLast(Object e) {
		if (head == null) {
			return head = new DoubleNode(e);
		} else {
			DoubleNode last = (DoubleNode) last();
			DoubleNode newNode = new DoubleNode(last, e, null);
			last.next = newNode;
			return newNode;
		}
	}

	@Override
	public Position insertBefore(Position p, Object e) throws InvalidPositionException {
		DoubleNode<E> node = head;
		while (!node.equals(p)) {
			node = node.next;
			if (node == null) {
				throw new InvalidPositionException();
			}
		}
		DoubleNode<E> newNode = new DoubleNode(node.previous, e, node);
		node.previous.next = newNode;
		node.previous = newNode;
		return newNode;
	}

	@Override
	public Position insertAfter(Position p, Object e) throws InvalidPositionException {
		DoubleNode<E> node = head;
		while (!node.equals(p)) {
			node = node.next;
			if (node == null) {
				throw new InvalidPositionException();
			}
		}
		DoubleNode<E> newNode = new DoubleNode(node, e, node.next);
		node.next.previous = newNode;
		node.next = newNode;
		return newNode;
	}

	@Override
	public Object removeElement(Position p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object replaceElement(Position p, Object e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void swapElements(Position p, Position q) throws InvalidPositionException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isFirst(Position p) throws InvalidPositionException {
		return head == p;
	}

	@Override
	public boolean isLast(Position p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return false;
	}

	private void checkNull(Position p) {
		if (p == null) {
			throw new InvalidPositionException();
		}
	}

}
