package topic2.classes;

import topic2.exceptions.EmptyListException;
import topic2.exceptions.InvalidPositionException;
import topic2.interfaces.List;
import topic2.interfaces.Position;

public class LinkedList<E> implements List<E> {

	/**
	 * @author Roman
	 */
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

	// *****INSTANZVARIABLEN*****
	private DoubleNode head = null;

	/**
	 * @return Amount of items in the list <br>
	 */
	@Override
	public int size() {
		if (head == null) {
			return 0;
		}
		int count = 0;
		DoubleNode position = head;
		while (position != null) {
			count++;
			position = position.next;
		}
		return count;
	}

	/**
	 * @return true idf
	 */
	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public boolean isFirst(Position p) throws InvalidPositionException {
		return first().equals(p);
	}

	@Override
	public boolean isLast(Position p) throws InvalidPositionException {
		DoubleNode node = find(p);
		return node.next == null;
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
		DoubleNode node = head;
		while (node.next != null) {
			node = node.next;
		}
		return node;
	}

	@Override
	public Position before(Position p) throws InvalidPositionException {

		if (p == null) {
			throw new InvalidPositionException();
		} else if (head == null) {
//			No head = no list = can't search list
			throw new InvalidPositionException();
//			Tests demand InvalidPositionException. Also possible:
//			throw new EmptyListException();
		} else if (p.equals(first())) {
//			No test case specified. 
			return null;
		}

		DoubleNode node = find(p);
		return node.previous;
	}

	@Override
	public Position after(Position p) throws InvalidPositionException {

		if (p == null) {
			throw new InvalidPositionException();
		} else if (head == null) {
//			No head = no list = can't search list
			throw new InvalidPositionException();
//			Tests demand InvalidPositionException. Also possible:
//			throw new EmptyListException();
		} else if (p.equals(last())) {
//			No test case specified. 
			return null;
		}

		DoubleNode node = find(p);
		return node.next;
	}

	@Override
	public Position insertFirst(Object e) {

		DoubleNode newFirst = new DoubleNode((E) e);

//		If there are other items in list adjust pointers
		if (!isEmpty()) {
			newFirst.next = head;
			head.previous = newFirst;
		}

//		In both cases we need to adjust head
		return head = newFirst;
	}

	@Override
	public Position insertLast(Object e) {

		DoubleNode newLast = new DoubleNode((E) e);

//		If there are other items in list adjust pointers
		if (!isEmpty()) {
			DoubleNode currentLast = find(last());
			currentLast.next = newLast;
			newLast.previous = currentLast;
		}

		return newLast;
	}

	@Override
	public Position insertBefore(Position p, Object e) throws InvalidPositionException {

		if (p == null) {
			throw new InvalidPositionException();
		}

		DoubleNode oldNode = find(p);
		DoubleNode newNode = new DoubleNode((E) e);

//		If we insert before the first item we adjust the head
		if (isFirst(oldNode)) {
			newNode.previous = null;
			head = newNode;
		} else {
			newNode.previous = oldNode.previous;
			oldNode.previous.next = newNode;
		}

//		Original oldNode.previous is needed in if else, newNode.next is the same in both cases
		newNode.next = oldNode;
		oldNode.previous = newNode;

		return newNode;

	}

	@Override
	public Position insertAfter(Position p, Object e) throws InvalidPositionException {

		if (p == null) {
			throw new InvalidPositionException();
		}

		DoubleNode oldNode = find(p);
		DoubleNode newNode = new DoubleNode((E) e);

//		If we insert after the last item 
		if (isLast(p)) {
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

	@Override
	public Object removeElement(Position p) throws InvalidPositionException {

		if (p == null) {
			throw new InvalidPositionException();
		}

		DoubleNode nodeToRemove = find(p);

		if (isFirst(nodeToRemove)) {
			if (size() == 1) {
//				Exactly one item in list
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

//		TODO was returnen? wieso überhaupt?
		return nodeToRemove;
	}

	@Override
	public Object replaceElement(Position p, Object e) throws InvalidPositionException {

		if (p == null) {
			throw new InvalidPositionException();
		}

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

	@Override
	public void swapElements(Position p, Position q) throws InvalidPositionException {

		if (p == null || q == null) {
			throw new InvalidPositionException();
		}

		DoubleNode nodeP = find(p);
//		DoubleNode preP = find(before(nodeP));
//		DoubleNode postP;

		DoubleNode nodeQ = find(q);
//		DoubleNode preQ = find(before(nodeQ));
//		DoubleNode postQ = find(after(nodeQ));

		DoubleNode tempNodeP = new DoubleNode(nodeP.previous, null, nodeP.next);

		nodeP.next.previous = nodeQ;

		nodeQ.previous.next = nodeP;

		nodeP.next = null;

		nodeP.previous = nodeQ.previous;

		nodeQ.previous = null;

		nodeQ.next = tempNodeP.next;

		// TODO head/tail umbiegen
//		if (isFirst(nodeP)) {
//			DoubleNode postP = find(after(nodeP));
//			DoubleNode preQ = find(before(nodeQ));
//			if (isLast(nodeQ)) {
//				postP.previous = nodeQ;
//				preQ.next = nodeP;
//			} else {
//				DoubleNode postQ = find(after(nodeQ));
//				postP.previous = nodeQ;
//				preQ.next = nodeP;
//				postQ.previous = nodeP;
//			}
//		} else  {
//
//		}
//
//		DoubleNode tempNodeP = nodeP;
//
//		// postP prev -> Q
//		if (!isLast(nodeP)) {
//			postP = find(after(nodeP));
//			nodeP.next.previous = nodeQ;
//		}
//
//		// postQ prev -> P
//		if (!isLast(nodeQ)) {
//			nodeQ.next.previous = nodeP;
//		}
//
//		// preP next -> Q
//		if (!isFirst(nodeP)) {
//			nodeP.previous.next = nodeQ;
//		}
//
//		// preQ next -> P
//		if (!isFirst(nodeQ)) {
//			nodeQ.previous.next = nodeP;
//		}
//
//		// P next -> Q next
//		nodeP.next = nodeQ.next;
//		// P prev -> Q prev
//		nodeP.previous = nodeQ.previous;
//
//		// Q next -> P next
//		nodeQ.next = tempNodeP.next;
//		// Q prev -> P prev
//		nodeQ.previous = tempNodeP.previous;

	}

	private DoubleNode find(Position p) throws InvalidPositionException {

		if (head == null) {
//			TODO einige methoden checken schon selber auf head == null
//			TODO entscheiden ob alles hier oder in den methoden
//			TODO entscheiden wegen cast von position oder 
//			Tests demand InvalidPositionException. Also possible:
//			throw new EmptyListException();
			throw new InvalidPositionException();
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

	public String print() {
		boolean continueLoop = true;
		String toBePrinted = "";

		if (head == null) {
			return toBePrinted = "List is empty";
		}

		DoubleNode node = head;

		while (continueLoop) {
			toBePrinted += node.element;
			if (node.next == null) {
				continueLoop = false;
			}
			node = node.next;
		}

		return toBePrinted;
	}

}
