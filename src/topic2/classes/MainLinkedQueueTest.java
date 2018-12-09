package topic2.classes;

public class MainLinkedQueueTest {

	@SuppressWarnings("unchecked")
	public static <E> void main(String[] args) {

		LinkedQueue<E> queue1 = new LinkedQueue<E>();
		LinkedQueue<E> queue2 = new LinkedQueue<E>();

		E name1 = (E) "Alf";
		E name2 = (E) "Betti";
		E name3 = (E) "Chris";
		E name4 = (E) "Dave";

		queue1.enqueue(name1);
		queue1.enqueue(name2);
		queue1.enqueue(name3);
		queue1.enqueue(name4);

		queue2.enqueue(name4);
		queue2.enqueue(name3);
		queue2.enqueue(name2);
		queue2.enqueue(name1);

		System.out.println(queue1.front() + " / Expected: Alf");
		queue1.dequeue();
		System.out.println(queue1.front() + " / Expected: Betti");
		queue1.dequeue();
		System.out.println(queue1.front() + " / Expected: Chris");
		queue1.enqueue(name1);

		queue1.dequeue();
		System.out.println(queue1.front() + " / Expected: Dave");
		queue1.dequeue();
		System.out.println(queue1.front() + " / Expected: Alf");
		queue1.dequeue();
		System.out.println(queue1.size() + "  / Expected: 0 ");

		System.out.println(queue2.front() + " / Expected: Dave");
		queue2.dequeue();
		System.out.println(queue2.front() + " / Expected: Chris");
		queue2.dequeue();
		System.out.println(queue2.front() + " / Expected: Betti");
		queue2.dequeue();
		System.out.println(queue2.front() + " / Expected: Alf");
		queue2.dequeue();
		System.out.println(queue1.size() + "  / Expected: 0 ");
	}

}
