package topic2.classes;

public class MainTest {

	public static <E> void main(String[] args) {

		LinkedStack<E> stack = new LinkedStack<E>();

		E name = (E) "Alf";
		E name1 = (E) "Betti";
		E name2 = (E) "Chris";
		E name3 = (E) "Dave";

		stack.push(name);
		stack.push(name1);
		stack.push(name2);
		stack.push(name3);

		System.out.println(stack.top());
		// Expectet: Dave
		System.out.println(stack.pop());
		// Expected: Dave

		System.out.println(stack.pop());
		// Expected: Chris

		System.out.println(stack.top());
		// Expected: Betti

		System.out.println(stack.size());
		// Expected: 2

	}

}
