package topic2.interfaces;

import topic2.exceptions.EmptyStackException;

public interface Stack<E> extends BasicCollection<E> {

	public E top() throws EmptyStackException;

	public void push(E e);

	public E pop() throws EmptyStackException;

}
