package topic2.interfaces;

import topic2.exceptions.EmptyQueueException;

public interface Queue<E> extends BasicCollection<E> {

	public E front() throws EmptyQueueException;

	public void enqueue(E e);

	public E dequeue() throws EmptyQueueException;

}
