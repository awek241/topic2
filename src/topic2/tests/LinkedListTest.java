package topic2.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import topic2.classes.LinkedList;
import topic2.exceptions.EmptyListException;
import topic2.exceptions.InvalidPositionException;
import topic2.interfaces.List;
import topic2.interfaces.Position;

public class LinkedListTest {

	@Test
	public void test1() {
		List<Integer> list = new LinkedList<>();

		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		// 1
		list.insertFirst(1);
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertEquals(1, (int) list.first().element());

		// 1 2
		list.insertLast(2);
		assertEquals(2, list.size());
		assertFalse(list.isEmpty());
		assertEquals(1, (int) list.first().element());
		assertEquals(2, (int) list.after(list.first()).element());

		// 1 2 3
		list.insertLast(3);
		assertEquals(3, list.size());
		assertFalse(list.isEmpty());
		assertEquals(1, (int) list.first().element());
		assertEquals(2, (int) list.after(list.first()).element());
		assertEquals(3, (int) list.last().element());

		// 4 1 2 3
		list.insertFirst(4);
		assertEquals(4, list.size());
		assertFalse(list.isEmpty());
		assertEquals(4, (int) list.first().element());
		assertEquals(1, (int) list.after(list.first()).element());
		assertEquals(2, (int) list.before(list.last()).element());
		assertEquals(3, (int) list.last().element());

		// 4 5 1 2 3
		list.insertAfter(list.first(), 5);
		assertEquals(5, list.size());
		assertFalse(list.isEmpty());
		assertEquals(4, (int) list.first().element());
		assertEquals(5, (int) list.after(list.first()).element());
		assertEquals(1, (int) list.after(list.after(list.first())).element());
		assertEquals(2, (int) list.before(list.last()).element());
		assertEquals(3, (int) list.last().element());

		// 4 5 6 1 2 3
		list.insertAfter(list.after(list.first()), 6);
		assertEquals(6, list.size());
		assertFalse(list.isEmpty());
		assertEquals(4, (int) list.first().element());
		assertEquals(5, (int) list.after(list.first()).element());
		assertEquals(6, (int) list.after(list.after(list.first())).element());
		assertEquals(1, (int) list.before(list.before(list.last())).element());
		assertEquals(2, (int) list.before(list.last()).element());
		assertEquals(3, (int) list.last().element());

		// 5 6 1 2 3
		list.removeElement(list.first());
		assertEquals(5, list.size());
		assertFalse(list.isEmpty());
		assertEquals(5, (int) list.first().element());
		assertEquals(6, (int) list.after(list.first()).element());
		assertEquals(1, (int) list.after(list.after(list.first())).element());
		assertEquals(2, (int) list.before(list.last()).element());
		assertEquals(3, (int) list.last().element());

		// 5 6 1 2
		list.removeElement(list.last());
		assertEquals(4, list.size());
		assertFalse(list.isEmpty());
		assertEquals(5, (int) list.first().element());
		assertEquals(6, (int) list.after(list.first()).element());
		assertEquals(1, (int) list.before(list.last()).element());
		assertEquals(2, (int) list.last().element());

		// 5 6 7 2
		list.replaceElement(list.after(list.after(list.first())), 7);
		assertEquals(4, list.size());
		assertFalse(list.isEmpty());
		assertEquals(5, (int) list.first().element());
		assertEquals(6, (int) list.after(list.first()).element());
		assertEquals(7, (int) list.before(list.last()).element());
		assertEquals(2, (int) list.last().element());

		// 2 6 7 5
		list.swapElements(list.first(), list.last());
		assertEquals(4, list.size());
		assertFalse(list.isEmpty());
		assertEquals(2, (int) list.first().element());
		assertEquals(6, (int) list.after(list.first()).element());
		assertEquals(7, (int) list.before(list.last()).element());
		assertEquals(5, (int) list.last().element());

		// 2 6 7
		list.removeElement(list.last());
		assertEquals(3, list.size());
		assertFalse(list.isEmpty());
		assertEquals(2, (int) list.first().element());
		assertEquals(6, (int) list.after(list.first()).element());
		assertEquals(7, (int) list.last().element());

		// 6 7
		list.removeElement(list.first());
		assertEquals(2, list.size());
		assertFalse(list.isEmpty());
		assertEquals(6, (int) list.first().element());
		assertEquals(7, (int) list.last().element());

		// 7
		list.removeElement(list.first());
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertEquals(7, (int) list.first().element());

		//
		list.removeElement(list.first());
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}

	@Test
	public void test2() {
		List<String> list = new LinkedList<>();
		for (int k = 1; k <= 2; k++) {
			for (int i = 1; i <= 100; i++) {
				list.insertFirst("" + i);
				assertEquals("" + i, list.first().element());
			}
			assertEquals(100, list.size());
			for (int i = 100; i >= 1; i--) {
				assertEquals("" + i, list.first().element());
				list.removeElement(list.first());
			}
			assertEquals(0, list.size());
		}
	}

	@Test
	public void test3() {
		assertThrows(EmptyListException.class, () -> {
			List<String> list = new LinkedList<>();
			list.first();
		});
	}

	@Test
	public void test4() {
		assertThrows(EmptyListException.class, () -> {
			List<String> list = new LinkedList<>();
			list.last();
		});
	}

	@Test
	public void test5() {
		assertThrows(InvalidPositionException.class, () -> {
			List<String> list = new LinkedList<>();
			list.before(null);
		});
	}

	@Test
	public void test6() {
		assertThrows(InvalidPositionException.class, () -> {
			List<String> list1 = new LinkedList<>();
			List<String> list2 = new LinkedList<>();
			Position<String> p = list2.insertFirst("123");
			list1.before(p);
		});
	}

	@Test
	public void test7() {
		assertThrows(InvalidPositionException.class, () -> {
			List<String> list = new LinkedList<>();
			list.after(null);
		});
	}

	@Test
	public void test8() {
		assertThrows(InvalidPositionException.class, () -> {
			List<String> list1 = new LinkedList<>();
			List<String> list2 = new LinkedList<>();
			Position<String> p = list2.insertFirst("123");
			list1.insertBefore(p, "456");
		});
	}

	@Test
	public void test9() {
		assertThrows(InvalidPositionException.class, () -> {
			List<String> list = new LinkedList<>();
			list.insertBefore(null, "456");
		});
	}

	@Test
	public void test10() {
		assertThrows(InvalidPositionException.class, () -> {
			List<String> list1 = new LinkedList<>();
			List<String> list2 = new LinkedList<>();
			Position<String> p = list2.insertFirst("123");
			list1.insertAfter(p, "456");
		});
	}

	@Test
	public void test11() {
		assertThrows(InvalidPositionException.class, () -> {
			List<String> list = new LinkedList<>();
			list.insertAfter(null, "456");
		});
	}

	@Test
	public void test12() {
		assertThrows(InvalidPositionException.class, () -> {
			List<String> list = new LinkedList<>();
			list.removeElement(null);
		});
	}

	@Test
	public void test13() {
		assertThrows(InvalidPositionException.class, () -> {
			List<String> list1 = new LinkedList<>();
			List<String> list2 = new LinkedList<>();
			Position<String> p = list2.insertFirst("123");
			list1.removeElement(p);
		});
	}

	@Test
	public void test14() {
		assertThrows(InvalidPositionException.class, () -> {
			List<String> list = new LinkedList<>();
			list.replaceElement(null, "123");
		});
	}

	@Test
	public void test15() {
		assertThrows(InvalidPositionException.class, () -> {
			List<String> list1 = new LinkedList<>();
			List<String> list2 = new LinkedList<>();
			Position<String> p = list2.insertFirst("123");
			list1.replaceElement(p, "456");
		});
	}

	@Test
	public void test16() {
		assertThrows(InvalidPositionException.class, () -> {
			List<String> list = new LinkedList<>();
			list.swapElements(null, null);
		});
	}

	@Test
	public void test17() {
		assertThrows(InvalidPositionException.class, () -> {
			List<String> list1 = new LinkedList<>();
			List<String> list2 = new LinkedList<>();
			Position<String> p = list2.insertFirst("123");
			Position<String> q = list1.insertFirst("123");
			list1.swapElements(p, q);
		});
	}

}
