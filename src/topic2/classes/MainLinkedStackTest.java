package topic2.classes;

public class MainLinkedStackTest {

	@SuppressWarnings("unchecked")
	public static <E> void main(String[] args) {

		LinkedStack<E> stack1 = new LinkedStack<E>();
		LinkedStack<E> stack2 = new LinkedStack<E>();

		E name = (E) "Alf";
		E name1 = (E) "Betti";
		E name2 = (E) "Chris";
		E name3 = (E) "Dave";

		stack1.push(name);
		stack1.push(name1);
		stack1.push(name2);
		stack1.push(name3);

		stack2.push(name3);
		stack2.push(name2);
		stack2.push(name1);
		stack2.push(name);

		System.out.println(stack1.top() + " / Expectet: Dave");

		System.out.println(stack1.pop() + " / Expectet: Dave");

		System.out.println(stack1.size() + " / Expectet: 3");

		System.out.println(stack1.pop() + " / Expectet: Chris");

		System.out.println(stack1.top() + " / Expectet: Betti");

		System.out.println(stack1.size() + " / Expectet: 2");

		System.out.println(stack1.pop() + " / Expectet: Alf");

		System.out.println(stack2.top() + " / Expectet: Alf");

		System.out.println(stack2.pop() + " / Expectet: Alf");

		System.out.println(stack2.size() + " / Expectet: 3");

		System.out.println(stack2.pop() + " / Expectet: Betti");

		System.out.println(stack2.top() + " / Expectet: Chris");

		System.out.println(stack2.size() + " / Expectet: 2");

	}

}
