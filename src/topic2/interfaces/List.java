package topic2.interfaces;

import topic2.exceptions.EmptyListException;
import topic2.exceptions.InvalidPositionException;

public interface List<E> extends BasicCollection<E> {

	public Position<E> first() throws EmptyListException;

	public Position<E> last() throws EmptyListException;

	public Position<E> before(Position<E> p) throws InvalidPositionException;

	public Position<E> after(Position<E> p) throws InvalidPositionException;

	public Position<E> insertFirst(E e);

	public Position<E> insertLast(E e);

	public Position<E> insertBefore(Position<E> p, E e) throws InvalidPositionException;

	public Position<E> insertAfter(Position<E> p, E e) throws InvalidPositionException;

	public E removeElement(Position<E> p) throws InvalidPositionException;

	public E replaceElement(Position<E> p, E e) throws InvalidPositionException;

	public void swapElements(Position<E> p, Position<E> q) throws InvalidPositionException;

	public boolean isFirst(Position<E> p) throws InvalidPositionException;

	public boolean isLast(Position<E> p) throws InvalidPositionException;

}
