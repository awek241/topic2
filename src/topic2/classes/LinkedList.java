package topic2.classes;

import topic2.exceptions.EmptyListException;
import topic2.exceptions.InvalidPositionException;
import topic2.interfaces.List;
import topic2.interfaces.Position;

public class LinkedList<E> implements List {

	protected class DoubleNode<E> implements Position<E> {

		private E element;
		private DoubleNode<E> next;

		public DoubleNode(E element) {
			this.element = element;
		}

		public void setNext(DoubleNode<E> next) {
			this.next = next;
		}

		public DoubleNode<E> getNext() {
			return next;
		}

		@Override
		public E element() {
			return element;
		}

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Position first() throws EmptyListException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position last() throws EmptyListException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position insertLast(Object e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position insertBefore(Position p, Object e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position insertAfter(Position p, Object e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLast(Position p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return false;
	}

}
