package topic2.classes;

public class MainLinkedQueueTest {

	public static <E> void main(String[] args) {

		LinkedQueue<E> queue1 = new LinkedQueue<E>();

		E name = (E) "Alf";
		E name1 = (E) "Betti";
		E name2 = (E) "Chris";
		E name3 = (E) "Dave";

		queue1.enqueue(name);
		queue1.enqueue(name1);
		queue1.enqueue(name2);
		// queue1.enqueue(name3);

		System.out.println(queue1.front() + " / Expected: Alf");
		queue1.dequeue();
		System.out.println(queue1.front() + " / Expected: Betti");
		queue1.dequeue();
		System.out.println(queue1.front() + " / Expected: Chris");

	}

}
