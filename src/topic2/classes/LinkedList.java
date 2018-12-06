package topic2.classes;

import topic2.exceptions.EmptyListException;
import topic2.exceptions.InvalidPositionException;
import topic2.interfaces.List;
import topic2.interfaces.Position;

public class LinkedList<E> implements List<E> {

	/** The LinkedList will consist of DoubleNode objects */
	protected class DoubleNode implements Position<E> {

		public E element;
		public DoubleNode previous;
		public DoubleNode next;

		public DoubleNode(DoubleNode previous, E element, DoubleNode next) {
			this.previous = previous;
			this.element = element;
			this.next = next;
		}

		public DoubleNode(E element) {
			previous = null;
			this.element = element;
			next = null;
		}

		@Override
		public E element() {
			return element;
		}

	}

	/** Klassenvariablen */
	private DoubleNode head = null;

	/** @return Amount of items in the list */
	@Override
	public int size() {

		int count = 0;
		DoubleNode position = head;

		while (position != null) {
			count++;
			position = position.next;
		}

		return count;
	}

	/** @return true if list is empty */
	@Override
	public boolean isEmpty() {

		return head == null;

	}

	/** @return true if <code>p</code> is the first item in the list */
	@Override
	public boolean isFirst(Position p) throws InvalidPositionException {
		DoubleNode node = find(p);
		return first().equals(node);
	}

	/** @return true if <code>p</code> is the last item in the list */
	@Override
	public boolean isLast(Position p) throws InvalidPositionException {
		DoubleNode node = find(p);
		return node.next == null;
	}

	/**
	 * @return <code>head</code>
	 * @throws if head is <code>null</code>
	 */
	@Override
	public Position first() throws EmptyListException {
		if (head == null) {
			throw new EmptyListException();
		}
		return head;
	}

	/**
	 * @return last item in list where <code>node.next = null</code>
	 * @throws if head == <code>null</code>
	 * 
	 */
	@Override
	public Position last() throws EmptyListException {

		if (head == null) {
			throw new EmptyListException();
		}

		DoubleNode node = head;

		while (node.next != null) {
			node = node.next;
		}

		return node;
	}

	/**
	 * @return the node before <code>p</code>
	 * @throws if <code>p</code> is the first element
	 */
	@Override
	public Position before(Position p) throws InvalidPositionException {

		DoubleNode node = find(p);

		if (p.equals(first())) {
//			No test case specified. 
			throw new InvalidPositionException();
		}

		return node.previous;
	}

	/**
	 * @return the node after <code>p</code>
	 * @throws if <code>p</code> is the last element
	 */
	@Override
	public Position after(Position p) throws InvalidPositionException {

		DoubleNode node = find(p);

		if (node.equals(last())) {
//			No test case specified. 
			throw new InvalidPositionException();
		}

		return node.next;
	}

	/**
	 * Will work when list is empty
	 * 
	 * @return the newly created node
	 */
	@Override
	public Position insertFirst(Object e) {

		DoubleNode newFirst = new DoubleNode((E) e);

		if (!isEmpty()) {
//			If there are other items in list adjust pointers
			newFirst.next = head;
			head.previous = newFirst;
		}

//		In both cases we need to adjust the head
		return head = newFirst;
	}

	/**
	 * Will work when list is empty
	 * 
	 * @return the newly created node
	 */
	@Override
	public Position insertLast(Object e) {

		DoubleNode newLast = new DoubleNode((E) e);

		if (!isEmpty()) {
//			If there are other items in list adjust pointers
			DoubleNode currentLast = find(last());
			currentLast.next = newLast;
			newLast.previous = currentLast;
		} else {
//			If there are no other items we adjust the head
			head = newLast;
		}

		return newLast;
	}

	/**
	 * Will work if inserted before first item
	 * 
	 * @return the newly created node
	 * 
	 */
	@Override
	public Position insertBefore(Position p, Object e) throws InvalidPositionException {

		DoubleNode oldNode = find(p);
		DoubleNode newNode = new DoubleNode((E) e);

		if (isFirst(oldNode)) {
//			If we insert before the first item we adjust the head
			newNode.previous = null;
			head = newNode;
		} else {
//			If not we only adjust the pointers
			newNode.previous = oldNode.previous;
			oldNode.previous.next = newNode;
		}

//		Original oldNode.previous is needed in if else, newNode.next is the same in both cases
		newNode.next = oldNode;
		oldNode.previous = newNode;

		return newNode;
	}

	/**
	 * Will work if inserted after last item
	 * 
	 * @return the newly created node
	 */
	@Override
	public Position insertAfter(Position p, Object e) throws InvalidPositionException {

		DoubleNode oldNode = find(p);
		DoubleNode newNode = new DoubleNode((E) e);

		if (isLast(oldNode)) {
//			If we insert after the last item 
			newNode.next = null;
		} else {
			newNode.next = oldNode.next;
			oldNode.next.previous = newNode;
		}

//		Original oldNode.next is needed in if else, newNode.previous is the same in both cases
		oldNode.next = newNode;
		newNode.previous = oldNode;

		return newNode;
	}

	/**
	 * @return the node that was removed
	 */
	@Override
	public Object removeElement(Position p) throws InvalidPositionException {

		DoubleNode nodeToRemove = find(p);

		if (isFirst(nodeToRemove)) {
			if (size() == 1) {
//				Exactly one item in the list
				head = null;
			} else {
//				More than one item in list -> move head back one node
				head = head.next;
				head.previous = null;
			}
		} else if (isLast(nodeToRemove)) {
//			Last item in list -> only one pointer to adjust
			nodeToRemove.previous.next = null;
		} else {
//			nodeToRemove is somewhere in the middle of the list
			DoubleNode tempNode = new DoubleNode(nodeToRemove.previous, null, nodeToRemove.next);
			nodeToRemove.next.previous = tempNode.previous;
			nodeToRemove.previous.next = tempNode.next;
		}

		return nodeToRemove;
	}

	/**
	 * @return the newly created node
	 */
	@Override
	public Object replaceElement(Position p, Object e) throws InvalidPositionException {

		DoubleNode nodeToReplace = find(p);
		DoubleNode newNode = new DoubleNode(nodeToReplace.previous, (E) e, nodeToReplace.next);

		if (isFirst(nodeToReplace)) {
//			Only adjust pointer of node after replacement and head
			nodeToReplace.next.previous = newNode;
			head = newNode;
		} else if (isLast(nodeToReplace)) {
//			Adjust pointer of node before replacement
			nodeToReplace.previous.next = newNode;
		} else {
//			Adjust pointer of node before and after replacement
			nodeToReplace.next.previous = newNode;
			nodeToReplace.previous.next = newNode;
		}

		return newNode;
	}

	/**
	 * Swaps the position of two elements in the list.
	 * 
	 * @param p Must be first() element of list
	 * @param q Must be last() element of list
	 */
	@Override
	public void swapElements(Position p, Position q) throws InvalidPositionException {

		DoubleNode nodeP = find(p);
		DoubleNode nodeQ = find(q);

		if (nodeP.equals(nodeQ)) {
//			No need to swap anything
			return;
		}

		if (isFirst(nodeP)) {
			if (isLast(nodeQ)) {

				DoubleNode futureP = new DoubleNode(nodeQ.previous, nodeQ.element, nodeQ.next);
				DoubleNode futureQ = new DoubleNode(nodeP.previous, nodeP.element, nodeP.next);

				nodeQ.previous.next = nodeP;
				nodeP.next.previous = nodeQ;

				nodeP.next = futureP.next;
				nodeP.previous = futureP.previous;
				nodeQ.next = futureQ.next;
				nodeQ.previous = futureQ.previous;

				head = nodeQ;
				return;
			}
		}

//		TODO define other cases:
//		p = first | q = second
//		p = first | q = not second, not last
//		p = not first | q = not last
//		p = not first | q = not last ***p and q are neighbours***
//		p = not first | q = last
//		p = second to last | q = last
//		
//		Already defined cases:
//		p = first | q = last
//		p = q
//		p OR q == null

	}

	/**
	 * This method checks if <code>p</code> is in our list. If it is we return the
	 * same object of the type DoubleNode. Quite a bit of validation happening here.
	 * 
	 * @param p The node that we are looking to find
	 * @return node
	 * @throws InvalidPositionException is thrown if<br>
	 *                                  <code>p</code> == null OR <br>
	 *                                  <code>head</code> == null OR <br>
	 *                                  <code>p</code> is not found in list
	 */
	private DoubleNode find(Position p) throws InvalidPositionException {

		if (p == null) {
			throw new InvalidPositionException();
		}

		if (head == null) {
//			No head = no list = can't search list
			throw new InvalidPositionException();
//			Tests demand InvalidPositionException. Also possible:
//			throw new EmptyListException();
		}

		DoubleNode node = head;
		while (!node.equals(p)) {
			if (node.next == null) {
				throw new InvalidPositionException();
			}
			node = node.next;
		}

		return node;
	}

	/**
	 * A method to get all the contents of the list.
	 * 
	 * @return String with contents of list separated by a "|" <br>
	 *         If empty returns "List is empty"
	 */
	public String getPrintVersion() {
		boolean continueLoop = true;
		String toBePrinted = "";

		if (head == null) {
			return toBePrinted = "List is empty";
		}

		DoubleNode node = head;

		while (continueLoop) {
			toBePrinted += "|" + node.element;
			if (node.next == null) {
				continueLoop = false;
			}
			node = node.next;
		}
//		Just so it looks nice
		return toBePrinted += "|";
	}

}
