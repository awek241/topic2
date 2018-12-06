package topic2.classes;

public class MainLinkedListTest {

	public static void main(String[] args) {

		LinkedList<Integer> intList = new LinkedList<>();

		intList.insertFirst(1);
		intList.insertLast(2);
		intList.insertLast(3);
		System.out.println(intList.getPrintVersion());

		intList.swapElements(intList.first(), intList.last());
		System.out.println(intList.getPrintVersion());

		try {
			System.out.println(intList.before(intList.first()).element());
		} catch (Exception e) {
			System.out.println(e);
		}

		LinkedList<String> stringList = new LinkedList<>();
		System.out.println(stringList.size());

		stringList.insertLast("testString");
		System.out.println(stringList.getPrintVersion());

	}

}
