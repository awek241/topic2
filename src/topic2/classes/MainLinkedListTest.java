package topic2.classes;

public class MainLinkedListTest {

	public static void main(String[] args) {

		LinkedList<Integer> list = new LinkedList<>();

		// 1
		list.insertFirst(1);
		System.out.println("1*");
		System.out.println(list.print());

		// 1 2
		list.insertLast(2);
		System.out.println("12*");
		System.out.println(list.print());

		// 1 2 3
		list.insertLast(3);
		System.out.println("123*");
		System.out.println(list.print());

		// 4 1 2 3
		list.insertFirst(4);
		System.out.println("4123*");
		System.out.println(list.print());

		// 4 5 1 2 3
		list.insertAfter(list.first(), 5);
		System.out.println("45123*");
		System.out.println(list.print());

		// 4 5 6 1 2 3
		list.insertAfter(list.after(list.first()), 6);
		System.out.println("456123*");
		System.out.println(list.print());

		// 5 6 1 2 3
		list.removeElement(list.first());
		System.out.println("56123*");
		System.out.println(list.print());

		// 5 6 1 2
		list.removeElement(list.last());
		System.out.println("5612*");
		System.out.println(list.print());

		// 5 6 7 2
		list.replaceElement(list.after(list.after(list.first())), 7);
		System.out.println("5672*");
		System.out.println(list.print());

		// 2 6 7 5
		System.out.println("2675*");
		System.out.println("FIRST, LAST");
		list.swapElements(list.first(), list.last());
		System.out.println(list.print());
		System.out.println("5672*");
		System.out.println("LAST, FIRST");
		list.swapElements(list.last(), list.first());
		System.out.println(list.print());

	}

}
